package com.d.dmusic.activity;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.application.SysApplication;
import com.d.dmusic.database.models.MyMusicListModel1;
import com.d.dmusic.fileutils.MusicFilterByPath;
import com.d.dmusic.fragment.FragmentController;
import com.d.dmusic.fragment.MainFragment;
import com.d.dmusic.fragment.ScanCustomFragment;
import com.d.dmusic.fragment.ScanMainFragment;
import com.d.dmusic.service.MyService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

/*
 * 扫描首页
 * 
 * */
public class ScanActivity extends BaseFragmentActivity implements OnClickListener {

	private ImageView ivTitleBack;
	private FragmentController fragmentController;
	private FragmentManager fragmentManager;
	public static int currentFragment;
	public static int index;// 传递过来的歌曲列表(本地，自定义)index
	public static boolean isScanOver;// ScanActivity是否对歌曲列表进行了扫描操作，以判断返回时是否需要在resume方法里刷新数据源

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		index = getIntent().getIntExtra("index", -1);
		isScanOver = false;
		Log.v("get_intent_index", index + "");
		setContentView(R.layout.activity_scan);
		ivTitleBack = (ImageView) findViewById(R.id.iv_title_back);
		fragmentController = FragmentController.getInstance(getApplicationContext());
		fragmentManager = getSupportFragmentManager();

		fragmentManager.beginTransaction().replace(R.id.scan_framelayout, new ScanMainFragment()).addToBackStack(null)
				.commit();
		ivTitleBack.setOnClickListener(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// 当前位于自定义扫描fragment,重写back键事件
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (currentFragment == 2) {
				if (ScanCustomFragment.scanDirListAdapter != null) {
					if (!ScanCustomFragment.scanDirListAdapter.ReturnParentDir()) {
						fragmentManager.popBackStack();
						currentFragment = 1;
						return true;
					}
				}
				return false;
			}
			if (fragmentManager.getBackStackEntryCount() <= 1) {
				finish();
			} else {
				fragmentManager.popBackStack();
			}
		}

		return false;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.iv_title_back) {
			finish();
		}

	}

}
