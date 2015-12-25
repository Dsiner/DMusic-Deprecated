package com.d.dmusic.activity;

import java.util.ArrayList;
import java.util.List;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import com.d.dmusic.R;
import com.d.dmusic.adapter.ScanDirListAdapter;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 暂未使用
 * 
 * @author D
 *
 */
public class ScanMusicActivity extends BaseFinalActivity implements OnClickListener {
	private LinearLayout llReturnParentDir;
	private LinearLayout llScanNow;
	private TextView tvCurrentDir;
	private ListView lvScanList;

	// private static FileUtils fileUtils;
	private List<FileInfo> currentDirLists;
	private String rootPath;
	// private List<String> pathQueue;
	private ScanDirListAdapter scanDirListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LitePalApplication.initialize(getApplicationContext());
		SQLiteDatabase db = Connector.getDatabase();
		setContentView(R.layout.activity_scan_dir);
		llReturnParentDir = (LinearLayout) findViewById(R.id.ll_return_parent_dir);
		llScanNow = (LinearLayout) findViewById(R.id.ll_scan_now);
		tvCurrentDir = (TextView) findViewById(R.id.tv_current_dir);
		lvScanList = (ListView) findViewById(R.id.lv_scan_list);

		llReturnParentDir.setOnClickListener(this);
		llScanNow.setOnClickListener(this);
		// currentDirLists = new ArrayList<FileInfo>();
		// pathQueue = new ArrayList<String>();
		rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		// pathQueue.add(rootPath);

		scanDirListAdapter = new ScanDirListAdapter(ScanMusicActivity.this, rootPath, tvCurrentDir);

		// fileUtils = new FileUtils(currentPath);
		// currentDirLists = fileUtils.getCurrentDirLists(currentPath);
		// Log.v("内存卡路径：",
		// Environment.getExternalStorageDirectory().getAbsolutePath());

		lvScanList.setAdapter(scanDirListAdapter);
	}

	// public static void reFresh() {
	// currentDirLists = fileUtils.getCurrentDirLists(currentPath);
	// }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!scanDirListAdapter.ReturnParentDir())
				finish();
		}
		return false;
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
			StringBuffer s = new StringBuffer("选中：" + "\n");
			currentDirLists = scanDirListAdapter.getCurrentDirLists();
			for (FileInfo fileInfo : currentDirLists) {
				if (fileInfo.isSelected())
					s.append("\n" + fileInfo.getFileAbsolutePath());

			}
			if (s != null)
				Toast.makeText(this, "" + s.toString(), 5000).show();
			break;
		}

	}
}
