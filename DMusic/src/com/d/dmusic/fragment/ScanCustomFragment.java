package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.activity.ScanMusicActivity;
import com.d.dmusic.adapter.ScanDirListAdapter;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.dialog.MyProgressBarWithBackgroundBlur;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 自定义扫描
 * 
 * */
public class ScanCustomFragment extends Fragment implements OnClickListener {

	private FragmentManager fragmentManager;
	private View viewRoot;

	private LinearLayout llReturnParentDir;
	private LinearLayout llScanNow;
	private TextView tvCurrentDir;
	private ListView lvScanList;

	// private static FileUtils fileUtils;
	private List<FileInfo> currentDirLists;
	private String rootPath;
	// private List<String> pathQueue;
	public static ScanDirListAdapter scanDirListAdapter;

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

		rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		viewRoot = inflater.inflate(R.layout.activity_scan_dir, container, false);

		ScanActivity.currentFragment = 2;

		// LitePalApplication.initialize(getActivity().getApplicationContext());
		// SQLiteDatabase db = Connector.getDatabase();
		llReturnParentDir = (LinearLayout) viewRoot.findViewById(R.id.ll_return_parent_dir);
		llScanNow = (LinearLayout) viewRoot.findViewById(R.id.ll_scan_now);
		tvCurrentDir = (TextView) viewRoot.findViewById(R.id.tv_current_dir);
		lvScanList = (ListView) viewRoot.findViewById(R.id.lv_scan_list);

		llReturnParentDir.setOnClickListener(this);
		llScanNow.setOnClickListener(this);
		// currentDirLists = new ArrayList<FileInfo>();
		// pathQueue = new ArrayList<String>();

		// pathQueue.add(rootPath);

		scanDirListAdapter = new ScanDirListAdapter(getActivity(), rootPath, tvCurrentDir);
		// Log.v("rootPath", "----:" + rootPath);
		// new FileUtils(rootPath).getCurrentDirLists(rootPath);

		// fileUtils = new FileUtils(currentPath);
		// currentDirLists = fileUtils.getCurrentDirLists(currentPath);
		// Log.v("内存卡路径：",
		// Environment.getExternalStorageDirectory().getAbsolutePath());

		lvScanList.setAdapter(scanDirListAdapter);

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
		case R.id.ll_return_parent_dir:
			if (scanDirListAdapter.ReturnParentDir()) {
			}
			break;
		case R.id.ll_scan_now:

			myProgressBarWithBackgroundBlur = new MyProgressBarWithBackgroundBlur(getActivity());
			myProgressBarWithBackgroundBlur.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					List<String> stringList = new ArrayList<String>();
					currentDirLists = scanDirListAdapter.getCurrentDirLists();
					for (FileInfo fileInfo : currentDirLists) {
						if (fileInfo.isSelected())
							stringList.add(fileInfo.getFileAbsolutePath());

					}
					if (stringList.size() > 0) {
						DataBaseUtils.insertMusicListIntoDataBase(getActivity(), ScanActivity.index, stringList);
						getActivity().finish();
					}
					handler.sendEmptyMessage(1);
				}
			}).start();

			break;
		}

	}

}
