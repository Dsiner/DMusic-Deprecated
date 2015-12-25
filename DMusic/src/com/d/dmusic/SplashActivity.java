package com.d.dmusic;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.activity.BaseFinalActivity;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.application.SysApplication;
import com.d.dmusic.global.Contains;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

/**
 * Splash&欢迎界面
 * 
 * @author D
 *
 */
public class SplashActivity extends BaseFinalActivity {
	private Context context;
	private ViewPager vpWelcome;
	private ImageView ivWelcomePageDot1;
	private ImageView ivWelcomePageDot2;
	private ImageView ivWelcomePageDot3;
	private boolean isBackKeyDown;
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {
				Intent intentToMainActivity = new Intent(SplashActivity.this, MainActivity.class);
				if (!isBackKeyDown) {
					startService();// 开启service服务
					startActivity(intentToMainActivity);
					finish();
				}
			}
			if (msg.what == 2) {
				Intent intentToMainActivity = new Intent(SplashActivity.this, PlayActivity.class);
				if (!isBackKeyDown) {
					// startService();// 开启service服务
					startActivity(intentToMainActivity);
					finish();
				}
			}
		}

	};

	private void startService() {
		// 开启service服务
		Intent startIntent = new Intent(SplashActivity.this, MyService.class);
		startService(startIntent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		SharedPreferences pref = getSharedPreferences("selfChecking", MODE_PRIVATE);
		boolean isFirst = pref.getBoolean("isFirst", true);
		Contains.playerMode = pref.getInt("playerMode", 0);
		MusicControlUtils.playMode = pref.getInt("playMode", 0);

		if (!isFirst) {
			switch (Contains.playerMode) {
			case 0:
				if (MyService.isStart) {
					Intent intentToMainActivity = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(intentToMainActivity);
					finish();
				} else {
					initSplashView();
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							handler.sendEmptyMessageDelayed(1, 2500);
						}
					}).start();
				}
				break;
			case 1:
				if (MyService.isStart) {
					Intent intentToMainActivity = new Intent(SplashActivity.this, PlayActivity.class);
					startActivity(intentToMainActivity);
					finish();
				} else {
					initSplashView();
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							startService();// 开启service服务
							handler.sendEmptyMessageDelayed(2, 2500);
						}
					}).start();
				}

				break;
			}

		} else {
			initWelcomeView();
			Editor edit = pref.edit();
			edit.putBoolean("isFirst", false);
			edit.commit();
		}

	}

	private void initSplashView() {
		setContentView(R.layout.splash_activity);
	}

	private void initWelcomeView() {
		setContentView(R.layout.welcome);
		vpWelcome = (ViewPager) findViewById(R.id.vp_welcome);

		ivWelcomePageDot1 = (ImageView) findViewById(R.id.iv_welcome_page_dot1);
		ivWelcomePageDot2 = (ImageView) findViewById(R.id.iv_welcome_page_dot2);
		ivWelcomePageDot3 = (ImageView) findViewById(R.id.iv_welcome_page_dot3);

		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View view1 = layoutInflater.inflate(R.layout.welcome_page1, null);
		View view2 = layoutInflater.inflate(R.layout.welcome_page2, null);
		View view3 = layoutInflater.inflate(R.layout.welcome_page3, null);

		final List<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		PagerAdapter pagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				// TODO Auto-generated method stub
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				// TODO Auto-generated method stub
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};
		vpWelcome.setAdapter(pagerAdapter);

		vpWelcome.addOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int postition) {
				// TODO Auto-generated method stub
				refreshPageDotsState(postition);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void refreshPageDotsState(int position) {

		switch (position) {
		case 0:
			ivWelcomePageDot2.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot));
			ivWelcomePageDot1.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot_cover));
			break;
		case 1:
			ivWelcomePageDot1.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot));
			ivWelcomePageDot3.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot));
			ivWelcomePageDot2.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot_cover));
			break;
		case 2:
			ivWelcomePageDot2.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot));
			ivWelcomePageDot3.setImageDrawable(getResources().getDrawable(R.drawable.welcome_page_dot_cover));
			break;
		}

	}

	public void click(View v) {
		switch (v.getId()) {
		// 启动音乐主界面
		case R.id.btn_welcome_start:
			startService();// 开启service服务
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
			finish();
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			isBackKeyDown = true;
			finish();
		}
		return false;
	}
}
