package com.d.dmusic.activity;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.adapter.PlayQueueAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.dialog.MyDialog;
import com.d.dmusic.fileutils.MediaUtil;
import com.d.dmusic.global.Contains;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class PlayActivity extends BaseFinalActivity implements OnSeekBarChangeListener, OnClickListener {
	private TextView tvSongName;
	private ImageView ivAlbum;
	private SeekBar seekBar;
	private TextView tvTimeStart;
	private TextView tvTimeEnd;
	private ImageButton ibPlayPrev;
	private ImageButton ibPlayPlayPause;
	private ImageButton ibPlayNext;
	private ImageButton ibPlayQueue;

	private ImageView ivPlayQueuePlayMode;
	private TextView tvPlayQueuePlayMode;
	private TextView tvPlayQueueCounts;
	private TextView ivPlayQueueDeleteAll;
	private ListView lvPlayQueueList;

	private MyService.MyBinder myBinder;
	private MusicControlUtils musicControlUtils;
	private MediaPlayer mediaPlayer;
	private ServiceConnection connection;
	private PlayerReceiver playerReceiver;
	private int currentTime;
	private ObjectAnimator animator;
	private boolean isRotation;
	private View dialogView;
	private MyDialog dialog;
	// adapter
	private PlayQueueAdapter playQueueAdapter;

	private List<MusicListModel> musicListModels;// ��ǰ�����б�
	private boolean isRegisterReceiver;// �Ƿ�ע���˹㲥������

	private int currentPlayMode;
	private static final String playModeName[] = { "�б�ѭ��", "˳�򲥷�", "�������", "����ѭ��" };
	private int playModeDrawable[] = { R.drawable.play_mode_all_repeat, R.drawable.play_mode_order,
			R.drawable.play_mode_shuffle, R.drawable.play_mode_single_cycle };

	/**
	 * handler����������Ϣ�������͹㲥���²���ʱ��
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (MyService.getInstanceMusicControlUtils().getMediaPlayer() != null) {
					// int currentTime =
					// MusicControlUtils.getInstance().getMediaPlayer().getCurrentPosition();
					// // ��ȡ��ǰ���ֲ��ŵ�λ��
					// Intent intent = new Intent();
					// intent.setAction(MUSIC_SEEK_TO_TIME);
					// intent.putExtra("currentTime", currentTime);
					// sendBroadcast(intent); // ��PlayerActivity���͹㲥
					// handler.sendEmptyMessageDelayed(1, 1000);
				}
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);

		musicControlUtils = MyService.getInstanceMusicControlUtils();
		musicListModels = MyService.getInstanceMusicControlUtils().getMusicListModels();
		currentPlayMode = musicControlUtils.getPlayMode();// ��ò���ģʽ
		if (musicControlUtils.isLoaded()) {
			initView();
			// initDialogView();
			// Intent bindIntent = new Intent(this, MyService.class);
			// bindService(bindIntent, connection, BIND_AUTO_CREATE);//
			// ��service������û���õ�
		}

	}

	private void initDialogView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(this);
		dialogView = inflater.inflate(R.layout.play_queue, null);
		ivPlayQueuePlayMode = (ImageView) dialogView.findViewById(R.id.iv_play_queue_play_mode);
		tvPlayQueuePlayMode = (TextView) dialogView.findViewById(R.id.tv_play_queue_play_mode);
		tvPlayQueueCounts = (TextView) dialogView.findViewById(R.id.tv_play_queue_counts);
		ivPlayQueueDeleteAll = (TextView) dialogView.findViewById(R.id.tv_play_queue_delete_all);
		lvPlayQueueList = (ListView) dialogView.findViewById(R.id.lv_play_queue_list);
		tvPlayQueueCounts.setText(musicListModels.size() + "��");
		int playMode = MyService.getInstanceMusicControlUtils().getPlayMode();
		ivPlayQueuePlayMode.setBackgroundResource(playModeDrawable[playMode]);
		tvPlayQueuePlayMode.setText(playModeName[playMode]);
		playQueueAdapter = new PlayQueueAdapter(this, musicListModels, tvPlayQueueCounts);
		lvPlayQueueList.setAdapter(playQueueAdapter);

		ivPlayQueuePlayMode.setOnClickListener(this);
		ivPlayQueueDeleteAll.setOnClickListener(this);

	}

	private void initView() {
		tvSongName = (TextView) findViewById(R.id.tv_song_name);
		ivAlbum = (ImageView) findViewById(R.id.iv_album);
		tvTimeStart = (TextView) findViewById(R.id.tv_time_start);
		tvTimeEnd = (TextView) findViewById(R.id.tv_time_end);
		seekBar = (SeekBar) findViewById(R.id.sb_progress);

		ibPlayPrev = (ImageButton) findViewById(R.id.ib_play_prev);
		ibPlayPlayPause = (ImageButton) findViewById(R.id.ib_play_play_pause);
		ibPlayNext = (ImageButton) findViewById(R.id.ib_play_next);
		ibPlayQueue = (ImageButton) findViewById(R.id.ib_play_queue);

		ibPlayPrev.setOnClickListener(this);
		ibPlayPlayPause.setOnClickListener(this);
		ibPlayNext.setOnClickListener(this);
		ibPlayQueue.setOnClickListener(this);

		seekBar.setOnSeekBarChangeListener(this);
		// PlayActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//
		// �����������ı�

		// connection = new ServiceConnection() {
		//
		// @Override
		// public void onServiceConnected(ComponentName name, IBinder service) {
		// // TODO Auto-generated method stub
		// myBinder = (MyService.MyBinder) service;
		// }
		//
		// @Override
		// public void onServiceDisconnected(ComponentName name) {
		// // TODO Auto-generated method stub
		//
		// }
		// };
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		// unbindService(connection);
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		if (musicControlUtils.isLoaded()) {
			tvSongName.setText(
					musicControlUtils.getMusicListModels().get(musicControlUtils.getCurrentLocation()).getSongName());
			mediaPlayer = musicControlUtils.getMediaPlayer();
			int Max = mediaPlayer.getDuration();
			int Current = mediaPlayer.getCurrentPosition();

			tvTimeEnd.setText(MediaUtil.formatTime(Max));
			seekBar.setMax(Max / 1000);
			seekBar.setProgress(Current / 1000);

			isRotation = true;
			rotationAnimator();
		}

		super.onResume();
	}

	private void rotationAnimator() {
		animator = ObjectAnimator.ofFloat(ivAlbum, "rotation", 0f, 360f);
		animator.setInterpolator(new LinearInterpolator());
		animator.setRepeatCount(10000);// �ظ�����
		animator.setDuration(5000);
		animator.start();

		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				super.onAnimationCancel(animation);
				float f = animation.getDuration();
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// rotationAnimator();
			}
		});
	}

	// ��ֵ�ı�
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		int currentTime = progress;
		int curminute = currentTime / 60;
		int cursecond = currentTime % 60;
		String curTimeString = String.format("%02d:%02d", curminute, cursecond);
		tvTimeStart.setText(curTimeString);

		// myBinder.updateNotificationTask(null, "ii", "progress:" + progress);
	}

	// ��ʼ�϶�
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		MyService.setProgressLock(true);
	}

	// ֹͣ�϶�
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		progressChanged(seekBar.getProgress() * 1000);
		Log.v("��ת��1��", seekBar.getProgress() + "");
		// MyService.setProgressLock(false);
	}

	/**
	 * ���Ž��ȸı�
	 */
	public void progressChanged(int progress) {
		Intent intent = new Intent();
		intent.setAction(Contains.MUSIC_SEEK_TO_TIME);
		Log.v("��ת��2��", progress + "");
		intent.putExtra("progress", progress);
		Log.v("��ת��3��", progress + "");
		sendBroadcast(intent);
	}

	private void registerReceiver() {
		// �����ע��㲥������
		playerReceiver = new PlayerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Contains.MUSIC_CURRENT);
		filter.addAction(Contains.MUSIC_UPDTE_LIST);
		registerReceiver(playerReceiver, filter);
	}

	/**
	 * �������մ�service�������Ĺ㲥���ڲ���
	 * 
	 * 
	 */
	public class PlayerReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(Contains.MUSIC_CURRENT)) {
				int currentTime = intent.getIntExtra("currentTime", 0);
				int durationTime = intent.getIntExtra("durationTime", 0);
				tvTimeStart.setText(MediaUtil.formatTime(currentTime));
				tvTimeEnd.setText(MediaUtil.formatTime(durationTime));
				seekBar.setMax(durationTime / 1000);
				seekBar.setProgress(currentTime / 1000);

			}
			if (action.equals(Contains.MUSIC_UPDTE_LIST)) {
				tvSongName.setText(musicControlUtils.getMusicListModels().get(musicControlUtils.getCurrentLocation())
						.getSongName());
			}
			// if (action.equals(MUSIC_CURRENT_UI_UPDATE_SELF)) {
			//
			// int currentTime = intent.getIntExtra("progress", 0);
			// Log.v("�յ��㲥��", currentTime + "");
			// tvTimeStart.setText(MediaUtil.formatTime(currentTime));
			// }
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return false;
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub

		if (musicControlUtils.isLoaded()) {
			isRegisterReceiver = true;
			registerReceiver();
		}

		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		if (isRegisterReceiver == true)
			unregisterReceiver(playerReceiver);

		super.onStop();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ib_play_prev:
			Intent intentPlayPrev = new Intent(Contains.PLAYER_CONTROL_PREV);
			intentPlayPrev.putExtra("flag", Contains.PRE_FLAG);
			sendBroadcast(intentPlayPrev);
			break;
		case R.id.ib_play_play_pause:

			if (animator.isRunning()) {
				animator.cancel();
			} else {
				rotationAnimator();
			}

			Intent intentPlayPause = new Intent(Contains.PLAYER_CONTROL_PLAY_PAUSE);
			intentPlayPause.putExtra("flag", Contains.PLAY_PAUSE_FLAG);
			sendBroadcast(intentPlayPause);
			break;
		case R.id.ib_play_next:
			Intent intentNext = new Intent(Contains.PLAYER_CONTROL_NEXT);
			intentNext.putExtra("flag", Contains.NEXT_FLAG);
			sendBroadcast(intentNext);
			break;
		case R.id.ib_play_queue:
			/* ��һ�ַ�ʽ���¿�Activity */
			// Intent intentPopPlayQueue = new Intent(this,
			// PopPlayQueueActivity.class);
			// startActivity(intentPopPlayQueue);

			/* �ڶ��ַ�ʽ���ڵ�ǰActivity�� */
			// if (dialog == null) {
			// initDialog();
			// }
			initDialog();
			dialog.show();
			break;
		case R.id.iv_play_queue_play_mode:
			changePlayMode();

			break;
		case R.id.tv_play_queue_delete_all:
			MyService.getInstanceMusicControlUtils().DelelteAll();
			playQueueAdapter.notifyDataSetChanged();
			tvPlayQueueCounts.setText(0 + "��");
			break;
		}

	}

	private void changePlayMode() {
		currentPlayMode++;
		if (currentPlayMode > 3) {
			currentPlayMode = 0;
		}
		MyService.getInstanceMusicControlUtils().changePlayMode(currentPlayMode);
		ivPlayQueuePlayMode.setBackgroundResource(playModeDrawable[currentPlayMode]);
		tvPlayQueuePlayMode.setText(playModeName[currentPlayMode]);
	}

	private void initDialog() {
		dialog = new MyDialog(this);
		// if (dialogView == null) {
		// initDialogView();
		// }
		initDialogView();
		dialog.setContentView(dialogView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);

		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int windowsWidth = dm.widthPixels;
		int windowsHeight = dm.heightPixels;

		// deprecated����
		// WindowManager m = getWindowManager();
		// Display d = m.getDefaultDisplay(); // ��ȡ��Ļ������
		// int windowsWidth = d.getWidth();
		// int windowsHeight = d.getHeight();

		p.width = (int) (windowsWidth * 1); // �������Ϊ��Ļ��1
		p.height = (int) (windowsHeight * 0.618); // �߶�����Ϊ��Ļ��0.618
		dialogWindow.setAttributes(p);
	}

}
