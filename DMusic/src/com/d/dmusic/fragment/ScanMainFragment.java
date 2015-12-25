package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.activity.ScanActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.dialog.MyProgressBarWithBackgroundBlur;

/*
 * 扫描Fragment
 * 
 * */
public class ScanMainFragment extends Fragment implements OnClickListener {

	private FragmentManager fragmentManager;
	private Button btnScanMainFullScan;
	private Button btnScanMainCustomScan;
	private View viewRoot;
	// 进度提示dialog
	private MyProgressBarWithBackgroundBlur myProgressBarWithBackgroundBlur;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				ScanActivity.isScanOver = true;// 扫描过，返回时需要刷新数据
				myProgressBarWithBackgroundBlur.dismiss();
				getActivity().finish();
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		fragmentManager = getActivity().getSupportFragmentManager();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		ScanActivity.currentFragment = 1;

		viewRoot = inflater.inflate(R.layout.activity_scan_main, container, false);
		btnScanMainFullScan = (Button) viewRoot.findViewById(R.id.btn_scan_main_full_scan);
		btnScanMainCustomScan = (Button) viewRoot.findViewById(R.id.btn_scan_main_custom_scan);
		btnScanMainFullScan.setOnClickListener(this);
		btnScanMainCustomScan.setOnClickListener(this);
		return viewRoot;

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_scan_main_full_scan:
			myProgressBarWithBackgroundBlur = new MyProgressBarWithBackgroundBlur(getActivity());
			myProgressBarWithBackgroundBlur.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					List<String> stringList = new ArrayList<String>();
					stringList.add(Environment.getExternalStorageDirectory().getAbsolutePath());
					DataBaseUtils.insertMusicListIntoDataBase(getActivity(), ScanActivity.index, stringList);
					handler.sendEmptyMessage(1);
				}
			}).start();

			break;
		case R.id.btn_scan_main_custom_scan:
			fragmentManager.beginTransaction().replace(R.id.scan_framelayout, new ScanCustomFragment())
					.addToBackStack(null).commit();
			break;
		}

	}

}
