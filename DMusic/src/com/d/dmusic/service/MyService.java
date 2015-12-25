package com.d.dmusic.service;

import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.fileutils.MediaUtil;
import com.d.dmusic.global.Contains;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Service����-Ӧ������ʱ�������־û����ֿ���ʵ��������Ӧ�㲥����
 * 
 * @author D
 *
 */
public class MyService extends Service {

	public static boolean isStart;// �Ƿ�����
	private static ControlBroadcast mConrolBroadcast;
	private static MusicControlUtils instanceMusicControlUtils;
	private static boolean progressLock = false;
	private static MyBinder mBinder;
	private static NotificationManager manager;
	private static RemoteViews rv;
	int notification_ID;
	int currentTime;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return mBinder;
	}

	public static boolean isProgressLock() {
		return progressLock;
	}

	public static void setProgressLock(boolean progressLock) {
		MyService.progressLock = progressLock;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		isStart = true;
		instanceMusicControlUtils = MusicControlUtils.getInstance(getBaseContext());// �õ�����
		mBinder = new MyBinder();
		mConrolBroadcast = new ControlBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Contains.PLAYER_RELOAD);
		filter.addAction(Contains.MUSIC_UPDTE_LIST);
		filter.addAction(Contains.PLAYER_CONTROL_PLAY_PAUSE);
		filter.addAction(Contains.PLAYER_CONTROL_PREV);
		filter.addAction(Contains.PLAYER_CONTROL_NEXT);
		filter.addAction(Contains.MUSIC_SEEK_TO_TIME);
		registerReceiver(mConrolBroadcast, filter);

		notification_ID = 1;
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// ��ʼִ�к�̨����
				List<MusicListModel> musicListModels = DataSupport.findAll(MusicListModel.class);
				if (musicListModels.size() > 0) {
					instanceMusicControlUtils.initList(musicListModels, 0);
				}

			}
		}).start();

	}

	public static MusicControlUtils getInstanceMusicControlUtils() {
		return instanceMusicControlUtils;

	}

	/**
	 * handler����������Ϣ�������͹㲥���²���ʱ��
	 */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				if (instanceMusicControlUtils.isLoaded()) {
					int currentTime = instanceMusicControlUtils.getMediaPlayer().getCurrentPosition(); // ��ȡ��ǰ���ֲ��ŵ�λ��
					int durationTime = instanceMusicControlUtils.getMediaPlayer().getDuration(); // ��ȡ��ǰ���ֲ�����ʱ��
					Intent intent = new Intent();
					intent.setAction(Contains.MUSIC_CURRENT);
					intent.putExtra("currentTime", currentTime);
					intent.putExtra("durationTime", durationTime);
					if (!progressLock) {
						sendBroadcast(intent);
					} // ��PlayerActivity���͹㲥
				}
				handler.sendEmptyMessageDelayed(1, 1000);
			}
		};
	};

	private void updateNotification(Bitmap bitmap, String title, String name) {

		Intent intent = new Intent(this, PlayActivity.class);
		PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
		Builder builder = new Notification.Builder(this);
		// builder.setPriority(Notification.PRIORITY_HIGH);
		builder.setSmallIcon(R.drawable.ic_launcher);// ����ͼ��
		builder.setTicker("");// �ֻ�״̬������ʾ��
		builder.setContentIntent(pintent);// ��������ͼ
		RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notification);
		if (bitmap != null) {
			rv.setImageViewBitmap(R.id.image, bitmap);
		} else {
			rv.setImageViewResource(R.id.image, R.drawable.notification_icon);
		}
		rv.setTextViewText(R.id.title, title);
		rv.setTextViewText(R.id.text, name);

		// �˴�action������һ���� ���һ���� ���ܵ�flag����ֻ�ǵ�һ�����õ�ֵ
		Intent pauseIntent = new Intent(Contains.PLAYER_CONTROL_PLAY_PAUSE);
		pauseIntent.putExtra("flag", Contains.PLAY_PAUSE_FLAG);
		PendingIntent pausePIntent = PendingIntent.getBroadcast(this, 0, pauseIntent, 0);
		rv.setOnClickPendingIntent(R.id.iv_pause, pausePIntent);

		Intent nextIntent = new Intent(Contains.PLAYER_CONTROL_NEXT);
		nextIntent.putExtra("flag", Contains.NEXT_FLAG);
		PendingIntent nextPIntent = PendingIntent.getBroadcast(this, 0, nextIntent, 0);
		rv.setOnClickPendingIntent(R.id.iv_next, nextPIntent);

		Intent preIntent = new Intent(Contains.PLAYER_CONTROL_PREV);
		preIntent.putExtra("flag", Contains.PRE_FLAG);
		PendingIntent prePIntent = PendingIntent.getBroadcast(this, 0, preIntent, 0);
		rv.setOnClickPendingIntent(R.id.iv_previous, prePIntent);

		builder.setContent(rv);
		Notification notification = new Notification();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			notification = builder.build();// 4.1����
		} else {
			notification = builder.getNotification();
		}
		manager.notify(notification_ID, notification);
		startForeground(notification_ID, notification);// �Զ����notification_ID����Ϊ0

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub'

		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// // ��ʼִ�к�̨����
		// List<MusicListModel> musicListModels =
		// DataSupport.findAll(MusicListModel.class);
		// if (musicListModels.size() > 0) {
		// instanceMusicControlUtils.initList(musicListModels, 0);
		// }
		//
		// }
		// }).start();
		handler.sendEmptyMessageDelayed(1, 1000);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public class MyBinder extends Binder {
		// ִ������
		public void updateNotificationTask(Bitmap bitmap, String title, String name) {
			updateNotification(bitmap, title, name);
		}

		public MusicControlUtils getinstanceMusicControlUtils() {
			return instanceMusicControlUtils;
		}

	}

	// �㲥������
	private class ControlBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Contains.MUSIC_SEEK_TO_TIME)) {
				int currentTime = intent.getIntExtra("progress", 0);
				if (currentTime / 1000 >= instanceMusicControlUtils.getMediaPlayer().getDuration() / 1000) {
					instanceMusicControlUtils.next();
					updateNotification(null,
							instanceMusicControlUtils.getMusicListModels()
									.get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
							instanceMusicControlUtils.getMusicListModels()
									.get(instanceMusicControlUtils.getCurrentLocation()).getSinger());// ����notification��view��ʾ
				} else {
					instanceMusicControlUtils.seekTo(currentTime);
				}

				progressLock = false;
				// handler.sendEmptyMessageDelayed(1, 1000);
			}
			if (action.equals(Contains.PLAYER_CONTROL_PLAY_PAUSE) || action.equals(Contains.PLAYER_CONTROL_NEXT)
					|| action.equals(Contains.PLAYER_CONTROL_PREV)) {
				int flag = intent.getIntExtra("flag", -1);
				Log.v("flags", flag + "");
				// Intent intentUpdateList = new Intent();
				// intentUpdateList.setAction(Contains.MUSIC_UPDTE_LIST);

				switch (flag) {

				case Contains.PLAY_PAUSE_FLAG:
					instanceMusicControlUtils.playOrPause();
					// updateNotification(null,
					// instanceMusicControlUtils.getMusicListModels()
					// .get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
					// instanceMusicControlUtils.getMusicListModels()
					// .get(instanceMusicControlUtils.getCurrentLocation()).getSinger());//
					// ����notification��view��ʾ
					// sendBroadcast(intentUpdateList);// ֪ͨ���²���ҳ�������Ϣ
					break;
				case Contains.NEXT_FLAG:
					instanceMusicControlUtils.next();
					// updateNotification(null,
					// instanceMusicControlUtils.getMusicListModels()
					// .get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
					// instanceMusicControlUtils.getMusicListModels()
					// .get(instanceMusicControlUtils.getCurrentLocation()).getSinger());//
					// ����notification��view��ʾ
					// sendBroadcast(intentUpdateList);// ֪ͨ���²���ҳ�������Ϣ
					break;
				case Contains.PRE_FLAG:
					instanceMusicControlUtils.prev();
					// updateNotification(null,
					// instanceMusicControlUtils.getMusicListModels()
					// .get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
					// instanceMusicControlUtils.getMusicListModels()
					// .get(instanceMusicControlUtils.getCurrentLocation()).getSinger());//
					// ����notification��view��ʾ
					// sendBroadcast(intentUpdateList);// ֪ͨ���²���ҳ�������Ϣ
					break;

				}
			}
			if (action.equals(Contains.PLAYER_RELOAD)) {
				updateNotification(null,
						instanceMusicControlUtils.getMusicListModels()
								.get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
						instanceMusicControlUtils.getMusicListModels()
								.get(instanceMusicControlUtils.getCurrentLocation()).getSinger());// ����notification��view��ʾ
				// Intent intentUpdateList = new Intent();
				// intentUpdateList.setAction(Contains.MUSIC_UPDTE_LIST);
				// sendBroadcast(intentUpdateList);// ֪ͨ���²���ҳ�������Ϣ
			}
			if (action.equals(Contains.MUSIC_UPDTE_LIST)) {
				updateNotification(null,
						instanceMusicControlUtils.getMusicListModels()
								.get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
						instanceMusicControlUtils.getMusicListModels()
								.get(instanceMusicControlUtils.getCurrentLocation()).getSinger());// ����notification��view��ʾ
			}

		}
	}

}
