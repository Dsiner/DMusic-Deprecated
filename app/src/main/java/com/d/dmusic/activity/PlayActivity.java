package com.d.dmusic.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import com.d.dmusic.R;
import com.d.dmusic.database.DataBaseInsert;
import com.d.dmusic.dialog.PopFromButtomDialog;
import com.d.dmusic.fileutils.MediaUtil;
import com.d.dmusic.global.Contains;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvp.adapter.PlayQueueAdapter;
import com.d.dmusic.mvp.events.PlayOrPauseEvent;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;
import com.d.dmusic.utils.StatusBarCompat;
import com.d.dmusic.utils.ULog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlayActivity extends BaseFinalActivity implements OnSeekBarChangeListener, OnClickListener {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_song_name)
    TextView tvSongName;
    @Bind(R.id.iv_album)
    ImageView ivAlbum;
    @Bind(R.id.tv_time_start)
    TextView tvTimeStart;
    @Bind(R.id.tv_time_end)
    TextView tvTimeEnd;
    @Bind(R.id.sb_progress)
    SeekBar seekBar;
    @Bind(R.id.ib_play_collect)
    ImageButton ibPlayColect;
    @Bind(R.id.ib_play_prev)
    ImageButton ibPlayPrev;
    @Bind(R.id.ib_play_play_pause)
    ImageButton ibPlayPlayPause;
    @Bind(R.id.ib_play_next)
    ImageButton ibPlayNext;
    @Bind(R.id.ib_play_queue)
    ImageButton ibPlayQueue;

    private ImageView ivPlayQueuePlayMode;
    private TextView tvPlayQueuePlayMode;
    private TextView tvPlayQueueCounts;
    private TextView ivPlayQueueDeleteAll;
    private TextView ivPlayQueueExit;
    private ListView lvPlayQueueList;

    private MusicControlUtils musicControlUtils;
    private MediaPlayer mediaPlayer;
    private PlayerReceiver playerReceiver;
    private ObjectAnimator animator;
    private View dialogView;
    private PopFromButtomDialog dialog;
    // adapter
    private PlayQueueAdapter playQueueAdapter;

    private List<MusicListModel> musicListModels;// 当前播放列表
    private boolean isRegisterReceiver;// 是否注册了广播监听器

    private int currentPlayMode;
    private static final String playModeName[] = {"列表循环", "顺序播放", "随机播放", "单曲循环"};
    private int playModeDrawable[] = {R.drawable.play_mode_all_repeat, R.drawable.play_mode_order,
            R.drawable.play_mode_shuffle, R.drawable.play_mode_single_cycle};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);
        ButterKnife.bind(this);
        EventBus.getDefault().register(PlayActivity.this);
        StatusBarCompat.compat(PlayActivity.this, 0xff000000);//沉浸式状态栏

        musicControlUtils = MyService.getInstanceMusicControlUtils();
        musicListModels = MyService.getInstanceMusicControlUtils().getMusicListModels();
        currentPlayMode = musicControlUtils.getPlayMode();// 获得播放模式
        if (musicControlUtils.isLoaded()) {
            initView();
        }
        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        ibPlayQueue.setImageResource(playModeDrawable[currentPlayMode]);
        ibPlayColect.setOnClickListener(this);
        ibPlayPrev.setOnClickListener(this);
        ibPlayPlayPause.setOnClickListener(this);
        ibPlayNext.setOnClickListener(this);
        ibPlayQueue.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        // PlayActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//
        // 物理音量键改变

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
    protected void onStart() {
        if (musicControlUtils.isLoaded()) {
            isRegisterReceiver = true;
            registerReceiver();
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicControlUtils.isLoaded()) {
            tvSongName.setText(musicControlUtils.getMusicListModels().get(musicControlUtils.getCurrentLocation()).getSongName());
            mediaPlayer = musicControlUtils.getMediaPlayer();
            int Max = mediaPlayer.getDuration();
            int Current = mediaPlayer.getCurrentPosition();
            tvTimeEnd.setText(MediaUtil.formatTime(Max));
            seekBar.setMax(Max / 1000);
            seekBar.setProgress(Current / 1000);
            int playStatus = musicControlUtils.getPlayStatus();
            if (playStatus == 1) {
                //正在播放
                ibPlayPlayPause.setImageResource(R.drawable.play_pause);
                rotationAnimator();
            } else {
                //无列表播放/暂停
                ibPlayPlayPause.setImageResource(R.drawable.play_play);
                if (animator != null && animator.isRunning()) {
                    animator.cancel();
                }
            }
        }
    }

    private void registerReceiver() {
        // 定义和注册广播接收器
        playerReceiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contains.MUSIC_CURRENT);
        filter.addAction(Contains.MUSIC_UPDTE_LIST);
        registerReceiver(playerReceiver, filter);
    }

    private void rotationAnimator() {
        animator = ObjectAnimator.ofFloat(ivAlbum, "rotation", 0f, 360f);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(10000);// 重复次数
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

    // 数值改变
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // TODO Auto-generated method stub
        int currentTime = progress;
        int curminute = currentTime / 60;
        int cursecond = currentTime % 60;
        String curTimeString = String.format("%02d:%02d", curminute, cursecond);
        tvTimeStart.setText(curTimeString);
    }

    // 开始拖动
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        MyService.setProgressLock(true);
    }

    // 停止拖动
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        progressChanged(seekBar.getProgress() * 1000);
        ULog.v("跳转到1：" + seekBar.getProgress());
    }

    /**
     * 播放进度改变
     */
    public void progressChanged(int progress) {
        Intent intent = new Intent();
        intent.setAction(Contains.MUSIC_SEEK_TO_TIME);
        ULog.v("跳转到2：" + progress);
        intent.putExtra("progress", progress);
        ULog.v("跳转到3：" + progress);
        sendBroadcast(intent);
    }

    /**
     * 用来接收从service传回来的广播的内部类
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
        }
    }

    @Subscribe
    public void onEventMainThread(PlayOrPauseEvent event) {
        if (event.isPlay) {
            ibPlayPlayPause.setImageResource(R.drawable.play_pause);
            rotationAnimator();
        } else {
            ibPlayPlayPause.setImageResource(R.drawable.play_play);
            if (animator != null && animator.isRunning()) {
                animator.cancel();
            }
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.ib_play_collect:
                if (musicControlUtils != null && musicControlUtils.getCurrentMusicListModel() != null) {
                    List<MusicListModel> mlm = new ArrayList<MusicListModel>();
                    mlm.add(musicControlUtils.getCurrentMusicListModel());
                    if (DataBaseInsert.insertColletinoMusicListIntoDataBase(mlm)) {
                        Toast.makeText(PlayActivity.this, "已收藏！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PlayActivity.this, "已收藏过了！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ib_play_prev:
                Intent intentPlayPrev = new Intent(Contains.PLAYER_CONTROL_PREV);
                intentPlayPrev.putExtra("flag", Contains.PRE_FLAG);
                sendBroadcast(intentPlayPrev);
                break;
            case R.id.ib_play_play_pause:
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
                initDialog();
                dialog.show();
                break;
            case R.id.iv_play_queue_play_mode:
                changePlayMode();
                break;
            case R.id.tv_play_queue_delete_all:
                MyService.getInstanceMusicControlUtils().DelelteAll();
                playQueueAdapter.notifyDataSetChanged();
                tvPlayQueueCounts.setText("(" + 0 + "首)");
                break;
            case R.id.tv_play_queue_exit:
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
        }
    }

    private void changePlayMode() {
        currentPlayMode++;
        if (currentPlayMode > 3) {
            currentPlayMode = 0;
        }
        musicControlUtils.changePlayMode(currentPlayMode);
        ivPlayQueuePlayMode.setBackgroundResource(playModeDrawable[currentPlayMode]);
        tvPlayQueuePlayMode.setText(playModeName[currentPlayMode]);
        ibPlayQueue.setImageResource(playModeDrawable[currentPlayMode]);
    }

    private void initDialog() {
        dialog = new PopFromButtomDialog(this);
        initDialogView();
        dialog.setContentView(dialogView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int windowsWidth = dm.widthPixels;
        int windowsHeight = dm.heightPixels;

        p.width = (int) (windowsWidth * 1); // 宽度设置为屏幕的1
        p.height = (int) (windowsHeight * 0.618); // 高度设置为屏幕的0.618
        dialogWindow.setAttributes(p);
    }

    private void initDialogView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        dialogView = inflater.inflate(R.layout.play_queue, null);
        ivPlayQueuePlayMode = (ImageView) dialogView.findViewById(R.id.iv_play_queue_play_mode);
        tvPlayQueuePlayMode = (TextView) dialogView.findViewById(R.id.tv_play_queue_play_mode);
        tvPlayQueueCounts = (TextView) dialogView.findViewById(R.id.tv_play_queue_counts);
        ivPlayQueueDeleteAll = (TextView) dialogView.findViewById(R.id.tv_play_queue_delete_all);
        ivPlayQueueExit = (TextView) dialogView.findViewById(R.id.tv_play_queue_exit);
        lvPlayQueueList = (ListView) dialogView.findViewById(R.id.lv_play_queue_list);
        tvPlayQueueCounts.setText("(" + musicListModels.size() + "首)");
        ivPlayQueuePlayMode.setBackgroundResource(playModeDrawable[currentPlayMode]);
        tvPlayQueuePlayMode.setText(playModeName[currentPlayMode]);
        playQueueAdapter = new PlayQueueAdapter(this, R.layout.play_queue_sub, musicListModels, tvPlayQueueCounts);
        lvPlayQueueList.setAdapter(playQueueAdapter);
        ivPlayQueuePlayMode.setOnClickListener(this);
        ivPlayQueueDeleteAll.setOnClickListener(this);
        ivPlayQueueExit.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        if (isRegisterReceiver == true)
            unregisterReceiver(playerReceiver);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(PlayActivity.this);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;
    }
}
