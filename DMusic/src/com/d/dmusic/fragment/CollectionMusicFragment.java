package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.activity.ScanMusicActivity;
import com.d.dmusic.adapter.CollectionSongListAdapter;
import com.d.dmusic.adapter.SongListAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.database.models.MyMusicListModel0;
import com.d.dmusic.database.models.MyMusicListModel1;
import com.d.dmusic.dialog.MyDialogTitleTop;
import com.d.dmusic.dialog.MyProgressBar;
import com.d.dmusic.fileutils.MusicFilterByPath;
import com.d.dmusic.view.ListViewBelowScrollView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pGroup;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * 我的收藏页面
 * 
 * */
public class CollectionMusicFragment extends Fragment implements OnClickListener {

	private View viewRoot;
	private TextView tvTitleTitle;
	private ImageView ivTitleBack;
	private ImageView ivTitleSearch;
	private ImageView ivTitleMore;

	private LinearLayout llTitleMoreBlank;
	private View vTitleMoreBlank;
	private LinearLayout llTitleMoreSortByName;
	private LinearLayout llTitleMoreSortByTime;
	private LinearLayout llTitleMoreScan;

	private MyDialogTitleTop dialog;
	private View dialogView;
	// 列表
	private ListViewBelowScrollView lvCollectionMusicList;
	private List<MusicListModel> musicListModels;
	private CollectionSongListAdapter collectionSongListAdapter;
	private boolean isload;// 是否已加载数据

	// progress
	private MyProgressBar myProgressBar;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				collectionSongListAdapter.notifyDataSetChanged();
				myProgressBar.dismiss();

			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tvTitleTitle = (TextView) view.findViewById(R.id.tv_title_title);
		ivTitleBack = (ImageView) view.findViewById(R.id.iv_title_back);
		ivTitleSearch = (ImageView) view.findViewById(R.id.iv_title_search);
		ivTitleMore = (ImageView) view.findViewById(R.id.iv_title_more);
		lvCollectionMusicList = (ListViewBelowScrollView) view.findViewById(R.id.lv_collection_music_list);
		tvTitleTitle.setText("我的收藏");

		ivTitleBack.setOnClickListener(this);
		ivTitleSearch.setOnClickListener(this);
		ivTitleMore.setOnClickListener(this);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		viewRoot = inflater.inflate(R.layout.fragment_collection, container, false);

		initView(viewRoot);
		// musicListModels = new ArrayList<MusicListModel>();
		// collectionSongListAdapter = new
		// CollectionSongListAdapter(getActivity(), musicListModels);
		// lvCollectionMusicList.setAdapter(collectionSongListAdapter);
		// myProgressBar = new MyProgressBar(getActivity());
		// myProgressBar.show();
		return viewRoot;
	}

	private void refresh() {
		musicListModels = new ArrayList<MusicListModel>();
		collectionSongListAdapter = new CollectionSongListAdapter(getActivity(), musicListModels);
		lvCollectionMusicList.setAdapter(collectionSongListAdapter);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DataBaseUtils.getCollectionMusicListFromDataBase(musicListModels);
				handler.sendEmptyMessage(1);
			}
		}).start();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isload == false) {
			myProgressBar = new MyProgressBar(getActivity());
			myProgressBar.show();
			refresh();
			isload = true;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_title_back:
			getActivity().getSupportFragmentManager().popBackStack();
			break;
		case R.id.iv_title_search:
			// Intent intent = new Intent(getActivity(), MoreActivity.class);
			// getActivity().startActivity(intent);
			break;
		case R.id.iv_title_more:
			initDialog();
			dialog.show();
			break;
		case R.id.ll_title_more_blank:
			dialog.dismiss();
			break;
		case R.id.v_title_more_blank:
			dialog.dismiss();
			break;
		case R.id.ll_title_more_sort_by_name:
			break;
		case R.id.ll_title_more_sort_by_time:
			break;
		case R.id.ll_title_more_scan:
			// Intent intenScanMusicActivity = new Intent(getActivity(),
			// ScanActivity.class);
			// intenScanMusicActivity.putExtra("index", index);
			// getActivity().startActivity(intenScanMusicActivity);
			dialog.dismiss();
			break;
		}
	}

	private void initDialog() {
		dialog = new MyDialogTitleTop(getActivity());
		// if (dialogView == null) {
		// initDialogView();
		// }
		initDialogView();
		dialog.setContentView(dialogView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);

		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.TOP);
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int windowsWidth = dm.widthPixels;
		int windowsHeight = dm.heightPixels;

		p.width = (int) (windowsWidth * 1); // 宽度设置为屏幕的1
		p.height = (int) (windowsHeight * 1); // 高度设置为屏幕的1
		dialogWindow.setAttributes(p);
	}

	private void initDialogView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		dialogView = inflater.inflate(R.layout.title_more_dialog, null);
		llTitleMoreBlank = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_blank);
		vTitleMoreBlank = dialogView.findViewById(R.id.v_title_more_blank);
		llTitleMoreSortByName = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_sort_by_name);
		llTitleMoreSortByTime = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_sort_by_time);
		llTitleMoreScan = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_scan);

		llTitleMoreBlank.setOnClickListener(this);
		vTitleMoreBlank.setOnClickListener(this);
		llTitleMoreSortByName.setOnClickListener(this);
		llTitleMoreSortByTime.setOnClickListener(this);
		llTitleMoreScan.setOnClickListener(this);

	}

}
