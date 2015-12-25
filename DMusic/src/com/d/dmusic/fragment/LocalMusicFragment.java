package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.activity.ScanMusicActivity;
import com.d.dmusic.adapter.AlbumListAdapter;
import com.d.dmusic.adapter.FolderListAdapter;
import com.d.dmusic.adapter.SingerListAdapter;
import com.d.dmusic.adapter.SongListAdapter;
import com.d.dmusic.adapter.models.AlbumListModel;
import com.d.dmusic.adapter.models.FolderListModel;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.adapter.models.SingerListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.MyDialogTitleTop;
import com.d.dmusic.dialog.MyProgressBar;

import android.R.bool;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * ����������ҳ--�첽�߳��и���listview,��Ȼû����
 * 
 * */
public class LocalMusicFragment extends Fragment implements OnClickListener, OnPageChangeListener {

	private View viewRoot;
	private TextView tvTitleTitle;
	private ImageView ivTitleBack;
	private ImageView ivTitleSearch;
	private ImageView ivTitleMore;

	private TextView tvLocalSong;
	private TextView tvLocalSinger;
	private TextView tvLocalAlbum;
	private TextView tvLocalFolder;
	private View vLocalBlock;

	private ViewPager viewPager;

	private LinearLayout llTitleMoreBlank;
	private View vTitleMoreBlank;
	private LinearLayout llTitleMoreSortByName;
	private LinearLayout llTitleMoreSortByTime;
	private LinearLayout llTitleMoreScan;

	private LocalMusicFragmentBySong localMusicFragmentBySong;
	private LocalMusicFragmentBySinger localMusicFragmentBySinger;
	private LocalMusicFragmentByAlbum localMusicFragmentByAlbum;
	private LocalMusicFragmentByFolder localMusicFragmentByFolder;

	// progress
	private MyProgressBar myProgressBar;
	// page[0-3]�Ƿ����
	private boolean[] isLoad = new boolean[4];
	// ����
	private List<MusicListModel> musicListModels;
	private List<MusicListModel> musicListModelsTemp;// ɨ��󣬶��θ���
	private SongListAdapter songListAdapter;
	// ����
	private List<SingerListModel> singerListModels;
	private List<SingerListModel> singerListModelsTemp;// ɨ��󣬶��θ���
	private SingerListAdapter singerListAdapter;
	// ר��
	private List<AlbumListModel> albumListModels;
	private List<AlbumListModel> albumListModelsTemp;// ɨ��󣬶��θ���
	private AlbumListAdapter albumListAdapter;
	// �ļ���
	private List<FolderListModel> folderListModels;
	private List<FolderListModel> folderListModelsTemp;// ɨ��󣬶��θ���
	private FolderListAdapter folderListAdapter;

	private FragmentPagerAdapter fragmentPagerAdapter;
	private OnPageChangeListener onPageChangeListener;
	private List<Fragment> fragments;
	private int mScreen1_4;
	private int mCurrentPageIndex;
	private RelativeLayout.LayoutParams lp;
	private MyDialogTitleTop dialog;
	private View dialogView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("��������:", "LocalMusicFragment:onCreat");

		musicListModels = new ArrayList<MusicListModel>();
		songListAdapter = new SongListAdapter(getActivity(), 20, musicListModels);// index:20��������

		singerListModels = new ArrayList<SingerListModel>();
		singerListAdapter = new SingerListAdapter(getActivity(), singerListModels);

		albumListModels = new ArrayList<AlbumListModel>();
		albumListAdapter = new AlbumListAdapter(getActivity(), albumListModels);

		folderListModels = new ArrayList<FolderListModel>();
		folderListAdapter = new FolderListAdapter(getActivity(), folderListModels);

		new LocalMusicSongAsyncTask().execute();
		isLoad[0] = true;// page[0]�ѿ�ʼ����

		fragments = new ArrayList<Fragment>();
		localMusicFragmentBySong = new LocalMusicFragmentBySong(songListAdapter);
		localMusicFragmentBySinger = new LocalMusicFragmentBySinger(singerListAdapter);
		localMusicFragmentByAlbum = new LocalMusicFragmentByAlbum(albumListAdapter);
		localMusicFragmentByFolder = new LocalMusicFragmentByFolder(folderListAdapter);
		fragments.add(localMusicFragmentBySong);
		fragments.add(localMusicFragmentBySinger);
		fragments.add(localMusicFragmentByAlbum);
		fragments.add(localMusicFragmentByFolder);

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("��������:", "LocalMusicFragment:onResume");
		if (ScanActivity.isScanOver) {
			ScanActivity.isScanOver = false;// �ָ�ԭʼֵ
			viewPager.setCurrentItem(0, false);// ��ת����һҳ
			new LocalMusicSongAsyncTask().execute();
			isLoad[0] = true;// page[0]�ѿ�ʼ����
			isLoad[1] = false;
			isLoad[2] = false;
			isLoad[3] = false;
			// ��������
			// MainFragment.fragmentManager.beginTransaction().replace(R.id.main_framelayout,
			// new LocalMusicFragment())
			// .commit();
			// ��������
			// MainFragment.fragmentManager.beginTransaction().replace(R.id.main_framelayout,
			// new LocalMusicFragment())
			// .addToBackStack(null).commit();
		} else {
			if (mCurrentPageIndex == 3) {
				// ��ʱ,������λ�����һҳ�Ļ���λ������
				lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4);
				vLocalBlock.setLayoutParams(lp);

			}
		}
	}

	// �����첽�߳�
	private class LocalMusicSongAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			myProgressBar = new MyProgressBar(getActivity());
			myProgressBar.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getMusicListFromDataBase();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			songListAdapter.notifyDataSetChanged();
			myProgressBar.dismiss();
		}

	}

	// �����첽�߳�
	private class LocalMusicSingerAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (myProgressBar != null)
				myProgressBar.dismiss();
			myProgressBar = new MyProgressBar(getActivity());
			myProgressBar.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getSingerListFromDataBase();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			singerListAdapter.notifyDataSetChanged();
			myProgressBar.dismiss();
		}

	}

	// ר���첽�߳�
	private class LocalMusicAlbumAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (myProgressBar != null)
				myProgressBar.dismiss();
			myProgressBar = new MyProgressBar(getActivity());
			myProgressBar.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getAlbumListFromDataBase();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			albumListAdapter.notifyDataSetChanged();
			myProgressBar.dismiss();
		}

	}

	// �ļ����첽�߳�
	private class LocalMusicFolderAsyncTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if (myProgressBar != null)
				myProgressBar.dismiss();
			myProgressBar = new MyProgressBar(getActivity());
			myProgressBar.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			getFolderListFromDataBase();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			folderListAdapter.notifyDataSetChanged();
			myProgressBar.dismiss();
		}

	}

	private void getMusicListFromDataBase() {
		musicListModels.clear();
		List<LocalAllMusicListModel> LocalAllMusicListModels = DataSupport.findAll(LocalAllMusicListModel.class);
		if (LocalAllMusicListModels != null) {

			for (LocalAllMusicListModel localAllMusicListModel : LocalAllMusicListModels) {
				MusicListModel musicListModel = new MusicListModel();
				musicListModel.setSongName(localAllMusicListModel.getSongName());
				musicListModel.setSinger(localAllMusicListModel.getSinger());
				musicListModel.setAlbum(localAllMusicListModel.getAlbum());
				musicListModel.setDuration(localAllMusicListModel.getDuration());
				musicListModel.setSize(localAllMusicListModel.getSize());
				musicListModel.setFilePostfix(localAllMusicListModel.getFilePostfix());
				musicListModel.setUrl(localAllMusicListModel.getUrl());
				// musicListModel.setCollected(false);
				// musicListModel.setLrcName("");
				// musicListModel.setLrcUrl("");
				musicListModels.add(musicListModel);

			}
		}

		// musicListModels =
		// MusicFilterByPath.getMusicListsModelByPath(getActivity(),
		// Environment.getExternalStorageDirectory().getAbsolutePath());
	}

	private void getSingerListFromDataBase() {
		singerListModels.clear();
		Cursor cursor = DataSupport.findBySQL("select *,count(*) from localallmusiclistmodel group by singer");
		if (cursor != null && cursor.moveToFirst()) {
			do {
				SingerListModel singerListModel = new SingerListModel();
				String singer = cursor.getString(cursor.getColumnIndex("singer"));
				int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
				singerListModel.setSinger(singer);
				singerListModel.setCount(count);
				Log.v("Singer", "singer:" + singer + "count:" + count);
				singerListModels.add(singerListModel);
			} while (cursor.moveToNext());
		}

	}

	private void getAlbumListFromDataBase() {
		albumListModels.clear();
		Cursor cursor = DataSupport.findBySQL("select *,count(*) from localallmusiclistmodel group by album");
		if (cursor != null && cursor.moveToFirst()) {
			do {
				AlbumListModel albumListModel = new AlbumListModel();
				String album = cursor.getString(cursor.getColumnIndex("album"));
				int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
				albumListModel.setAlbum(album);
				albumListModel.setCount(count);
				albumListModels.add(albumListModel);
			} while (cursor.moveToNext());
		}

	}

	private void getFolderListFromDataBase() {
		folderListModels.clear();
		Cursor cursor = DataSupport.findBySQL("select *,count(*) from localallmusiclistmodel group by folder");
		if (cursor != null && cursor.moveToFirst()) {
			do {
				FolderListModel folderListModel = new FolderListModel();
				String folder = cursor.getString(cursor.getColumnIndex("folder"));
				int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
				folderListModel.setFolder(folder);
				folderListModel.setCount(count);
				folderListModels.add(folderListModel);
			} while (cursor.moveToNext());
		}

	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		vLocalBlock = view.findViewById(R.id.vLocalBlock);
		tvTitleTitle = (TextView) view.findViewById(R.id.tv_title_title);
		ivTitleBack = (ImageView) view.findViewById(R.id.iv_title_back);
		ivTitleSearch = (ImageView) view.findViewById(R.id.iv_title_search);
		ivTitleMore = (ImageView) view.findViewById(R.id.iv_title_more);
		tvLocalSong = (TextView) view.findViewById(R.id.tv_local_song);
		tvLocalSinger = (TextView) view.findViewById(R.id.tv_local_singer);
		tvLocalAlbum = (TextView) view.findViewById(R.id.tv_local_album);
		tvLocalFolder = (TextView) view.findViewById(R.id.tv_local_folder);
		viewPager = (ViewPager) view.findViewById(R.id.vp_local);
		tvTitleTitle.setText("��������");

		tvLocalSong.setOnClickListener(this);
		tvLocalSinger.setOnClickListener(this);
		tvLocalAlbum.setOnClickListener(this);
		tvLocalFolder.setOnClickListener(this);
		ivTitleBack.setOnClickListener(this);
		ivTitleSearch.setOnClickListener(this);
		ivTitleMore.setOnClickListener(this);

		Display display = getActivity().getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics = new DisplayMetrics();
		display.getMetrics(outMetrics);
		mScreen1_4 = outMetrics.widthPixels / 4;
		lp = (android.widget.RelativeLayout.LayoutParams) vLocalBlock.getLayoutParams();
		lp.width = mScreen1_4;
		vLocalBlock.setLayoutParams(lp);
		fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return fragments.get(arg0);
			}
		};
		// onPageChangeListener = new OnPageChangeListener() {
		//
		// @Override
		// public void onPageSelected(int position) {
		// // TODO Auto-generated method stub
		//
		// setTab(position);
		// mCurrentPageIndex = position;
		// reloadList(position);
		//
		// }
		//
		// @Override
		// public void onPageScrolled(int position, float positionOffset, int
		// positionOffsetPx) {
		// // TODO Auto-generated method stub
		// Log.e("TAG", mCurrentPageIndex + "," + position + " , " +
		// positionOffset + " , " + positionOffsetPx);
		//
		// RelativeLayout.LayoutParams lp =
		// (android.widget.RelativeLayout.LayoutParams) vLocalBlock
		// .getLayoutParams();
		//
		// if (mCurrentPageIndex == 0 && position == 0)// 0->1
		// {
		// lp.leftMargin = (int) (positionOffset * mScreen1_4 +
		// mCurrentPageIndex * mScreen1_4);
		// } else if (mCurrentPageIndex == 1 && position == 0)// 1->0
		// {
		// lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 +
		// (positionOffset - 1) * mScreen1_4);
		// } else if (mCurrentPageIndex == 1 && position == 1) // 1->2
		// {
		// lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 +
		// positionOffset * mScreen1_4);
		// } else if (mCurrentPageIndex == 2 && position == 1) // 2->1
		// {
		// lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 +
		// (positionOffset - 1) * mScreen1_4);
		// } else if (mCurrentPageIndex == 2 && position == 2) // 2->3
		// {
		// lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 +
		// positionOffset * mScreen1_4);
		// } else if (mCurrentPageIndex == 3 && position == 2) // 3->2
		// {
		// lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 +
		// (positionOffset - 1) * mScreen1_4);
		// }
		// vLocalBlock.setLayoutParams(lp);
		// }
		//
		// @Override
		// public void onPageScrollStateChanged(int arg0) {
		// // TODO Auto-generated method stub
		//
		// }
		// };
		viewPager.setAdapter(fragmentPagerAdapter);
		viewPager.setOffscreenPageLimit(3);// ���ڴ�ռ�ã�test
		viewPager.addOnPageChangeListener(this);

	}

	private void reloadList(int position) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			if (!isLoad[position]) {
				new LocalMusicSongAsyncTask().execute();
				isLoad[position] = true;
			}
			break;
		case 1:
			if (!isLoad[position]) {
				new LocalMusicSingerAsyncTask().execute();
				isLoad[position] = true;
			}
			break;
		case 2:
			if (!isLoad[position]) {
				new LocalMusicAlbumAsyncTask().execute();
				isLoad[position] = true;
			}
			break;
		case 3:
			if (!isLoad[position]) {
				new LocalMusicFolderAsyncTask().execute();
				isLoad[position] = true;
			}
			break;

		}

	}

	protected void setTab(int currentItem) {
		// TODO Auto-generated method stub

		tvLocalSong.setTextColor(Color.parseColor("#000000"));
		tvLocalSinger.setTextColor(Color.parseColor("#000000"));
		tvLocalAlbum.setTextColor(Color.parseColor("#000000"));
		tvLocalFolder.setTextColor(Color.parseColor("#000000"));
		switch (currentItem) {
		case 0:
			tvLocalSong.setTextColor(Color.parseColor("#45C01A"));
			break;
		case 1:
			tvLocalSinger.setTextColor(Color.parseColor("#45C01A"));
			break;
		case 2:
			tvLocalAlbum.setTextColor(Color.parseColor("#45C01A"));
			break;
		case 3:
			tvLocalFolder.setTextColor(Color.parseColor("#45C01A"));
			break;

		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("��������:", "LocalMusicFragment:onCreateView");

		viewRoot = inflater.inflate(R.layout.fragment_local, container, false);
		initView(viewRoot);

		return viewRoot;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.v("��������:", "LocalMusicFragment:onDestroyView");
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
		case R.id.tv_local_song:
			setTab(0);
			// mViewPager.setCurrentItem(0);
			viewPager.setCurrentItem(0, true);
			break;
		case R.id.tv_local_singer:
			setTab(1);
			// mViewPager.setCurrentItem(1);
			viewPager.setCurrentItem(1, true);
			break;
		case R.id.tv_local_album:
			setTab(2);
			// mViewPager.setCurrentItem(2);
			viewPager.setCurrentItem(2, true);
			break;
		case R.id.tv_local_folder:
			setTab(3);
			// mViewPager.setCurrentItem(3);
			viewPager.setCurrentItem(3, true);
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
			Intent intenScanMusicActivity = new Intent(getActivity(), ScanActivity.class);
			intenScanMusicActivity.putExtra("index", 20);
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
		p.height = (int) (windowsHeight * 1); // �߶�����Ϊ��Ļ��0.618
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

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
		// TODO Auto-generated method stub
		Log.e("TAG", mCurrentPageIndex + "," + position + " , " + positionOffset + " , " + positionOffsetPx);

		// lp = (android.widget.RelativeLayout.LayoutParams)
		// vLocalBlock.getLayoutParams();

		if (mCurrentPageIndex == 0 && position == 0)// 0->1
		{
			lp.leftMargin = (int) (positionOffset * mScreen1_4 + mCurrentPageIndex * mScreen1_4);
		} else if (mCurrentPageIndex == 1 && position == 0)// 1->0
		{
			lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + (positionOffset - 1) * mScreen1_4);
		} else if (mCurrentPageIndex == 1 && position == 1) // 1->2
		{
			lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + positionOffset * mScreen1_4);
		} else if (mCurrentPageIndex == 2 && position == 1) // 2->1
		{
			lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + (positionOffset - 1) * mScreen1_4);
		} else if (mCurrentPageIndex == 2 && position == 2) // 2->3
		{
			lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + positionOffset * mScreen1_4);
		} else if (mCurrentPageIndex == 3 && position == 2) // 3->2
		{
			lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_4 + (positionOffset - 1) * mScreen1_4);
		}
		vLocalBlock.setLayoutParams(lp);
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

		setTab(position);
		mCurrentPageIndex = position;
		reloadList(position);

	}

}
