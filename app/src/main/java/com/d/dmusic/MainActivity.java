package com.d.dmusic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.d.dmusic.activity.BaseFragmentActivity;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.application.SysApplication;
import com.d.dmusic.fragment.FragmentController;
import com.d.dmusic.global.Contains;
import com.d.dmusic.mvp.fragments.MainFragment;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;
import com.d.dmusic.utils.StatusBarCompat;
import com.nineoldandroids.view.ViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity implements OnClickListener {
    @Bind(R.id.tv_main_song_name)
    TextView tvMainSongName;
    @Bind(R.id.tv_main_singer)
    TextView tvMainSinger;
    @Bind(R.id.ll_menu_quit)
    LinearLayout llMenuQuit;// 退出
    @Bind(R.id.iv_playpage)
    ImageView mIvPlayPager;
    @Bind(R.id.main_drawerlayout)
    DrawerLayout mDrawerLayout;

    /**
     * 菜单打开/关闭状态
     */
    private boolean isDirection_right = false;
    private FragmentController fragmentController;
    private FragmentManager fragmentManager;
    // WindowManager
    public static WindowManager windowManager;
    private MusicInfoReceiver musicInfoReceiver;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                tvMainSongName.setText(MyService.getInstanceMusicControlUtils().getCurrentSongName());
                tvMainSinger.setText(MyService.getInstanceMusicControlUtils().getCurrentSinger());
            }
        }

        ;
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarCompat.compat(MainActivity.this, 0xffFD8D22);//沉浸式状态栏
        ButterKnife.bind(MainActivity.this);
        windowManager = getWindowManager();

        llMenuQuit.setOnClickListener(this);
        mIvPlayPager.setOnClickListener(this);

        // 设置抽屉打开时，主要内容区被自定义阴影覆盖
        mDrawerLayout.addDrawerListener(new DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;
                float leftScale = 1 - 0.3f * scale;

                ViewHelper.setScaleX(mMenu, leftScale);
                ViewHelper.setScaleY(mMenu, leftScale);
                ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));

                ViewHelper.setTranslationX(mContent, -mMenu.getMeasuredWidth() * slideOffset);
                ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                ViewHelper.setPivotY(mContent, mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        fragmentController = FragmentController.getInstance(getApplicationContext());
        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.main_framelayout, new MainFragment()).addToBackStack(null)
                .commit();
        // 开启service服务
        // Intent startIntent = new Intent(this, MyService.class);
        // startService(startIntent);
        // MainActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//
        // 物理音量键改变
        registerReceiver();// 注册广播监听器
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.iv_playpage:
                Intent intent = new Intent(this, PlayActivity.class);
                overridePendingTransition(R.anim.enter_up, R.anim.exit_down);
                startActivity(intent);
                break;
            // 退出
            case R.id.ll_menu_quit:
                SharedPreferences pref = getSharedPreferences("selfChecking", MODE_PRIVATE);
                Editor edit = pref.edit();
                edit.putInt("playMode", MusicControlUtils.playMode);// 保存播放模式
                edit.commit();
                MyService.getInstanceMusicControlUtils().stop();// 停止音乐播放

                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);// 停止服务
                SysApplication.getInstance().exit();// 退出
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (fragmentManager.getBackStackEntryCount() <= 1) {
                finish();
            } else {
                fragmentManager.popBackStack();
            }
        }
        return false;

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        tvMainSongName.setText(MyService.getInstanceMusicControlUtils().getCurrentSongName());
        tvMainSinger.setText(MyService.getInstanceMusicControlUtils().getCurrentSinger());
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        ButterKnife.unbind(this);
        unregisterReceiver(musicInfoReceiver);
        super.onDestroy();
    }

    private void registerReceiver() {
        // 定义和注册广播接收器
        musicInfoReceiver = new MusicInfoReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contains.MUSIC_UPDTE_LIST);
        registerReceiver(musicInfoReceiver, filter);
    }

    /**
     * 用来接收从service传回来的广播的内部类
     */
    public class MusicInfoReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (action.equals(Contains.MUSIC_UPDTE_LIST)) {
                String songName = intent.getStringExtra("songName");
                String singer = intent.getStringExtra("singer");
                if (tvMainSongName != null && tvMainSinger != null) {
                    tvMainSongName.setText(songName);
                    tvMainSinger.setText(singer);
                    Log.v("main:", "tvMainSongName:" + songName + "tvMainSinger:" + singer);
                }

            }
        }
    }

}
