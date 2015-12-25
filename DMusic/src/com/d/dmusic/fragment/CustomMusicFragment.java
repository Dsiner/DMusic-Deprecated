package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.activity.ListHandleActivity;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.activity.ScanMusicActivity;
import com.d.dmusic.adapter.SongListAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.adapter.models.SingerListModel;
import com.d.dmusic.database.DataBaseQuery;
import com.d.dmusic.database.DataBaseUpdate;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.database.models.MyMusicListModel0;
import com.d.dmusic.database.models.MyMusicListModel1;
import com.d.dmusic.dialog.MyDialogTitleTop;
import com.d.dmusic.dialog.MyProgressBar;
import com.d.dmusic.fileutils.MusicFilterByPath;
import com.d.dmusic.global.Contains;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
 * �Զ���������ҳ
 * 
 * */
public class CustomMusicFragment extends Fragment implements OnClickListener {

	private View viewRoot;
	private TextView tvTitleTitle;
	private ImageView ivTitleBack;
	private ImageView ivTitleSearch;
	private ImageView ivTitleMore;
	// listͷ��
	private View header;
	private TextView tvListSongSubHeaderCounts;
	private ImageView ivListSongSubHeaderHandle;

	private LinearLayout llTitleMoreBlank;
	private View vTitleMoreBlank;
	private LinearLayout llTitleMoreSortByName;// ����������
	private LinearLayout llTitleMoreSortByTime;// ��ʱ������
	private LinearLayout llTitleMoreSortByCustom;// �Զ�������
	private LinearLayout llTitleMoreScan;

	private MyDialogTitleTop dialog;
	private View dialogView;
	private int id;// �����б�Table����
	private int index;// �б��ʶ
	private String title;// ����
	public static int sortBy;// ����ʽ
	private int counts;// ��������
	// �б�
	private ListView lvCustomMusicList;
	private List<MusicListModel> musicListModels;
	private List<MusicListModel> musicListModelstemp;
	private SongListAdapter songListAdapter;
	public static boolean isNeedReload;// �Ƿ���Ҫ���¼�������
	// progress
	private MyProgressBar myProgressBar;

	public CustomMusicFragment(int id, int index, String title, int sortBy) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.index = index;
		this.title = title;
		this.sortBy = sortBy;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				tvListSongSubHeaderCounts.setText(counts + "��");
				musicListModels.addAll(musicListModelstemp);
				songListAdapter.notifyDataSetChanged();
				DataBaseUpdate.updateCountsById(id, counts);// �������ݿ��Զ����б��������
				myProgressBar.dismiss();

			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isNeedReload = true;// ��ʼ����ҳ��ʱ��ǿ�Ƽ���(isNeedReloadΪ��̬����)

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		viewRoot = inflater.inflate(R.layout.fragment_custom, container, false);

		initView(viewRoot);

		lvCustomMusicList.addHeaderView(header);
		musicListModelstemp = new ArrayList<MusicListModel>();
		musicListModels = new ArrayList<MusicListModel>();
		songListAdapter = new SongListAdapter(getActivity(), index, musicListModels);
		lvCustomMusicList.setAdapter(songListAdapter);
		myProgressBar = new MyProgressBar(getActivity());
		myProgressBar.show();

		isNeedReload = false;// �ѿ�ʼ��������

		refresh();// ��������
		return viewRoot;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isNeedReload || ScanActivity.isScanOver) {
			preBeforeRefresh();
			refresh();
			isNeedReload = false;
			ScanActivity.isScanOver = false;// ��λ
		}

	}

	/**
	 * ��Ϊlistview ����ͷ��header�����Ƴ�ͷ���������¼�������
	 */
	private void preBeforeRefresh() {
		lvCustomMusicList.removeHeaderView(header);
		lvCustomMusicList.addHeaderView(header);
		musicListModels = new ArrayList<MusicListModel>();
		songListAdapter = new SongListAdapter(getActivity(), index, musicListModels);
		lvCustomMusicList.setAdapter(songListAdapter);
		myProgressBar = new MyProgressBar(getActivity());
		myProgressBar.show();
	}

	private void refresh() {

		switch (sortBy) {
		// ��ʱ������-����id������ѯ
		case 2:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					counts = DataBaseQuery.getMusicListFromDataBaseByTime(index, musicListModelstemp);
					handler.sendEmptyMessage(1);
				}
			}).start();
			break;
		// �Զ�������
		case 3:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					counts = DataBaseQuery.getMusicListFromDataBaseBycustomSeq(index, musicListModelstemp);
					handler.sendEmptyMessage(1);
				}
			}).start();
			break;
		// ����������
		default:
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					counts = DataBaseQuery.getMusicListFromDataBaseByName(index, musicListModelstemp);
					handler.sendEmptyMessage(1);
				}
			}).start();

			break;
		}

	}

	/**
	 * ����������--sql����ѯ��ʽ;��δʹ��
	 */
	private void querySQL() {
		musicListModels.clear();
		Cursor cursor = DataSupport.findBySQL("select * from localallmusiclistmodel order by songname");
		if (cursor != null && cursor.moveToFirst()) {
			do {

				MusicListModel musicListModel = new MusicListModel();
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				String singer = cursor.getString(cursor.getColumnIndex("singer"));
				String songname = cursor.getString(cursor.getColumnIndex("songname"));
				String album = cursor.getString(cursor.getColumnIndex("album"));
				Long duration = cursor.getLong(cursor.getColumnIndex("duration"));
				Long size = cursor.getLong(cursor.getColumnIndex("size"));
				String filePostfix = cursor.getString(cursor.getColumnIndex("filepostfix"));
				String url = cursor.getString(cursor.getColumnIndex("url"));

				musicListModel.setId(id);
				musicListModel.setAlbum(album);
				musicListModel.setDuration(duration);
				musicListModel.setSize(size);
				musicListModel.setFilePostfix(filePostfix);
				musicListModel.setUrl(url);
				musicListModel.setSinger(singer);
				musicListModel.setSongName(songname);
				Log.v("Singer", "singer:" + singer + "songname:" + songname);
				musicListModels.add(musicListModel);
			} while (cursor.moveToNext());
		}

	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		tvTitleTitle = (TextView) view.findViewById(R.id.tv_title_title);
		ivTitleBack = (ImageView) view.findViewById(R.id.iv_title_back);
		ivTitleSearch = (ImageView) view.findViewById(R.id.iv_title_search);
		ivTitleMore = (ImageView) view.findViewById(R.id.iv_title_more);

		header = LayoutInflater.from(getActivity()).inflate(R.layout.list_song_sub_header, null);
		tvListSongSubHeaderCounts = (TextView) header.findViewById(R.id.tv_list_song_sub_header_counts);
		ivListSongSubHeaderHandle = (ImageView) header.findViewById(R.id.iv_list_song_sub_header_handle);

		lvCustomMusicList = (ListView) view.findViewById(R.id.lv_custom_music_list);
		tvTitleTitle.setText(title);

		ivTitleBack.setOnClickListener(this);
		ivTitleSearch.setOnClickListener(this);
		ivTitleMore.setOnClickListener(this);

		ivListSongSubHeaderHandle.setOnClickListener(this);

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
		// �б����
		case R.id.iv_list_song_sub_header_handle:
			Intent intentListHandleActivity = new Intent(getActivity(), ListHandleActivity.class);
			intentListHandleActivity.putExtra("id", id);
			intentListHandleActivity.putExtra("index", index);

			Contains.musicListModels = new ArrayList<MusicListModel>();
			Contains.musicListModels.addAll(musicListModels);// ����һ�ݣ���ListHandleActivity��ʹ��
			getActivity().startActivity(intentListHandleActivity);

			break;
		case R.id.ll_title_more_blank:
			dialog.dismiss();
			break;
		case R.id.v_title_more_blank:
			dialog.dismiss();
			break;
		// �����Ʋ�ѯ
		case R.id.ll_title_more_sort_by_name:
			sortBy = 1;
			ContentValues valuesName = new ContentValues();
			valuesName.put("sortby", sortBy);
			DataSupport.update(CustomMusicListModel.class, valuesName, id);// ���µ�ǰ�����б�����ʽ
			preBeforeRefresh();
			refresh();
			break;
		// ��ʱ���ѯ
		case R.id.ll_title_more_sort_by_time:
			sortBy = 2;
			ContentValues valuesTime = new ContentValues();
			valuesTime.put("sortby", sortBy);
			DataSupport.update(CustomMusicListModel.class, valuesTime, id);// ���µ�ǰ�����б�����ʽ
			preBeforeRefresh();
			refresh();
			break;
		// ��ʱ���ѯ
		case R.id.ll_title_more_sort_by_custom:
			sortBy = 3;
			ContentValues valuesCustom = new ContentValues();
			valuesCustom.put("sortby", sortBy);
			DataSupport.update(CustomMusicListModel.class, valuesCustom, id);// ���µ�ǰ�����б�����ʽ
			preBeforeRefresh();
			refresh();
			break;
		case R.id.ll_title_more_scan:
			Intent intenScanMusicActivity = new Intent(getActivity(), ScanActivity.class);
			intenScanMusicActivity.putExtra("index", index);
			getActivity().startActivity(intenScanMusicActivity);
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
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int windowsWidth = dm.widthPixels;
		int windowsHeight = dm.heightPixels;

		p.width = (int) (windowsWidth * 1); // �������Ϊ��Ļ��1
		p.height = (int) (windowsHeight * 1); // �߶�����Ϊ��Ļ��1
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
		llTitleMoreSortByCustom = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_sort_by_custom);
		llTitleMoreScan = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_scan);

		llTitleMoreBlank.setOnClickListener(this);
		vTitleMoreBlank.setOnClickListener(this);
		llTitleMoreSortByName.setOnClickListener(this);
		llTitleMoreSortByTime.setOnClickListener(this);
		llTitleMoreSortByCustom.setOnClickListener(this);
		llTitleMoreScan.setOnClickListener(this);

	}

}
