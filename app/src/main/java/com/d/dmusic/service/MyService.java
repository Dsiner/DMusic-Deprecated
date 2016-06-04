package com.d.dmusic.service;

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
import android.widget.RemoteViews;

import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.global.Contains;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.utils.ULog;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Service服务-应用启动时开启，持久化音乐控制实例，及相应广播功能
 *
 * @author D
 */
public class MyService extends Service {

    public static boolean isStart;// 是否启动
    private static ControlBroadcast mConrolBroadcast;
    private static MusicControlUtils instanceMusicControlUtils;
    private static boolean progressLock = false;
    private static MyBinder mBinder;
    private static NotificationManager manager;
    private static RemoteViews rv;
    private int notification_ID;

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
        instanceMusicControlUtils = MusicControlUtils.getInstance(getBaseContext());// 拿到单例
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
                // 开始执行后台任务
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
     * handler用来接收消息，来发送广播更新播放时间
     */
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                if (instanceMusicControlUtils.isLoaded()) {
                    int currentTime = instanceMusicControlUtils.getMediaPlayer().getCurrentPosition(); // 获取当前音乐播放的位置
                    int durationTime = instanceMusicControlUtils.getMediaPlayer().getDuration(); // 获取当前音乐播放总时间
                    Intent intent = new Intent();
                    intent.setAction(Contains.MUSIC_CURRENT);
                    intent.putExtra("currentTime", currentTime);
                    intent.putExtra("durationTime", durationTime);
                    if (!progressLock) {
                        sendBroadcast(intent);
                    } // 给PlayerActivity发送广播
                }
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }

        ;
    };

    /**
     * 刷新通知栏
     *
     * @param bitmap:图标
     * @param title:歌曲名
     * @param name:歌手
     * @param playStatus:无/播放/暂停
     */
    private void updateNotification(Bitmap bitmap, String title, String name, int playStatus) {
        Intent intent = new Intent(this, PlayActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
        Builder builder = new Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(Notification.PRIORITY_HIGH);
        }
        builder.setSmallIcon(R.drawable.ic_launcher);// 设置图标
        builder.setTicker("");// 手机状态栏的提示；
        builder.setContentIntent(pintent);// 点击后的意图
        RemoteViews rv = new RemoteViews(getPackageName(), R.layout.notification);
        if (bitmap != null) {
            rv.setImageViewBitmap(R.id.image, bitmap);
        } else {
            rv.setImageViewResource(R.id.image, R.drawable.notification_icon);
        }
        if (playStatus == 1) {
            rv.setImageViewResource(R.id.iv_pause, R.drawable.notification_pause);
        } else if (playStatus == 2) {
            rv.setImageViewResource(R.id.iv_pause, R.drawable.notification_play);
        }
        rv.setTextViewText(R.id.title, title);
        rv.setTextViewText(R.id.text, name);

        // 此处action不能是一样的 如果一样的 接受的flag参数只是第一个设置的值
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
            notification = builder.build();// 4.1以上
        } else {
            notification = builder.getNotification();
        }
        manager.notify(notification_ID, notification);
        startForeground(notification_ID, notification);// 自定义的notification_ID不能为0
    }

    /**
     * 取消通知栏
     */
    public void cancleNotification() {
        //can not work beacauseof "startForeground"!
//        manager.cancel(notification_ID);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub'

        // new Thread(new Runnable() {
        // @Override
        // public void run() {
        // // 开始执行后台任务
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

    public class MyBinder extends Binder {
        // 执行任务
        public void updateNotificationTask(Bitmap bitmap, String title, String name, int playStatus) {
            updateNotification(bitmap, title, name, playStatus);
        }

        public MusicControlUtils getinstanceMusicControlUtils() {
            return instanceMusicControlUtils;
        }

    }

    // 广播接收器
    private class ControlBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Contains.MUSIC_SEEK_TO_TIME)) {
                int currentTime = intent.getIntExtra("progress", 0);
                if (currentTime / 1000 >= instanceMusicControlUtils.getMediaPlayer().getDuration() / 1000) {
                    instanceMusicControlUtils.next();
                } else {
                    instanceMusicControlUtils.seekTo(currentTime);
                }
                progressLock = false;
            } else if (action.equals(Contains.PLAYER_CONTROL_PLAY_PAUSE) || action.equals(Contains.PLAYER_CONTROL_NEXT)
                    || action.equals(Contains.PLAYER_CONTROL_PREV)) {
                int flag = intent.getIntExtra("flag", -1);
                ULog.v("flags" + flag + "");
                switch (flag) {
                    case Contains.PLAY_PAUSE_FLAG:
                        int playStatus = instanceMusicControlUtils.playOrPause();
                        updateNotif(playStatus);//正在播放
                        break;
                    case Contains.NEXT_FLAG:
                        instanceMusicControlUtils.next();
                        break;
                    case Contains.PRE_FLAG:
                        instanceMusicControlUtils.prev();
                        break;
                }
            } else if (action.equals(Contains.PLAYER_RELOAD)) {
                updateNotif(1);//正在播放
            } else if (action.equals(Contains.MUSIC_UPDTE_LIST)) {
                updateNotif(1);//正在播放
            }
        }
    }

    private void updateNotif(int playStatus) {
        switch (playStatus) {
            case 0:
                //取消通知栏
                cancleNotification();
                break;
            case 1:
                //正在播放
            case 2:
                //暂停
                updateNotification(null,
                        instanceMusicControlUtils.getMusicListModels()
                                .get(instanceMusicControlUtils.getCurrentLocation()).getSongName(),
                        instanceMusicControlUtils.getMusicListModels()
                                .get(instanceMusicControlUtils.getCurrentLocation()).getSinger(), playStatus);// 更新notification的view显示
                break;
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
