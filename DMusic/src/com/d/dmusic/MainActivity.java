package com.d.dmusic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.litepal.crud.DataSupport;

import com.d.dmusic.activity.BaseFragmentActivity;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.activity.PlayActivity.PlayerReceiver;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.application.SysApplication;
import com.d.dmusic.fileutils.MediaUtil;
import com.d.dmusic.fragment.FragmentController;
import com.d.dmusic.fragment.FragmentModel;
import com.d.dmusic.fragment.LocalMusicFragment;
import com.d.dmusic.fragment.MainFragment;
import com.d.dmusic.global.Contains;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;
import com.nineoldandroids.view.ViewHelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseFragmentActivity implements OnClickListener {

	private TextView tvMainSongName;
	private TextView tvMainSinger;
	private LinearLayout llMenuQuit;// 退出
	private MusicInfoReceiver musicInfoReceiver;

	/** 菜单打开/关闭状态 */
	private boolean isDirection_right = false;
	private View showView;
	private ImageView mIvPlayPager;
	private DrawerLayout mDrawerLayout;
	private LinearLayout right_drawer;
	private LinearLayout loacl_music;

	private FragmentController fragmentController;
	private FragmentManager fragmentManager;

	// WindowManager
	public static WindowManager windowManager;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				tvMainSongName.setText(MyService.getInstanceMusicControlUtils().getCurrentSongName());
				tvMainSinger.setText(MyService.getInstanceMusicControlUtils().getCurrentSinger());
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		windowManager = getWindowManager();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
		right_drawer = (LinearLayout) findViewById(R.id.right_drawer);
		mIvPlayPager = (ImageView) findViewById(R.id.iv_playpage);
		tvMainSongName = (TextView) findViewById(R.id.tv_main_song_name);
		tvMainSinger = (TextView) findViewById(R.id.tv_main_singer);
		llMenuQuit = (LinearLayout) findViewById(R.id.ll_menu_quit);

		llMenuQuit.setOnClickListener(this);
		mIvPlayPager.setOnClickListener(this);
		// 设置自定义ActionBar

		// 设置抽屉打开时，主要内容区被自定义阴影覆盖

		mDrawerLayout.setDrawerListener(new DrawerListener() {

			@Override
			public void onDrawerStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

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
			public void onDrawerOpened(View arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDrawerClosed(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		fragmentController = FragmentController.getInstance(getApplicationContext());
		fragmentManager = getSupportFragmentManager();

		// if (savedInstanceState == null) {

		fragmentManager.beginTransaction().replace(R.id.main_framelayout, new MainFragment()).addToBackStack(null)
				.commit();
		// }
		// 开启service服务
		// Intent startIntent = new Intent(this, MyService.class);
		// startService(startIntent);
		// MainActivity.this.setVolumeControlStream(AudioManager.STREAM_MUSIC);//
		// 物理音量键改变
		registerReceiver();// 注册广播监听器
	}

	/**
	 * 根据onPostCreate回调的状态，还原对应的icon state
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// mMaterialMenuIcon.syncState(savedInstanceState);
	}

	/**
	 * 根据onSaveInstanceState回调的状态，保存当前icon state
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// mMaterialMenuIcon.onSaveInstanceState(outState);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_playpage:
			Intent intent = new Intent(this, PlayActivity.class);
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
		// Toast.makeText(this, "数量：" +
		// fragmentManager.getBackStackEntryCount(), Toast.LENGTH_SHORT).show();
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
	protected void onStart() {
		// TODO Auto-generated method stub

		super.onStart();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		tvMainSongName.setText(MyService.getInstanceMusicControlUtils().getCurrentSongName());
		tvMainSinger.setText(MyService.getInstanceMusicControlUtils().getCurrentSinger());
		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// // 开始执行后台任务
		// handler.sendEmptyMessageDelayed(1, 600);
		// }
		//
		// }).start();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
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
	 * 
	 * 
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
