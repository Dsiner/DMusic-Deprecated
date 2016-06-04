package com.d.dmusic.mvp.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.CircleDialog;
import com.d.dmusic.dialog.TopMenuDialog;
import com.d.dmusic.models.AlbumListModel;
import com.d.dmusic.models.FolderListModel;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.models.SingerListModel;
import com.d.dmusic.mvp.adapter.AlbumListAdapter;
import com.d.dmusic.mvp.adapter.FolderListAdapter;
import com.d.dmusic.mvp.adapter.SingerListAdapter;
import com.d.dmusic.mvp.adapter.SongListAdapter;
import com.d.dmusic.utils.ULog;
import com.d.dmusic.view.ViewPagerIndicator;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 本地音乐首页
 * 
 * */
public class LocalMusicFragment extends Fragment implements OnClickListener, OnPageChangeListener {
    @Bind(R.id.indicator)
    ViewPagerIndicator mIndicator;
    @Bind(R.id.tv_title_title)
    TextView tvTitleTitle;
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @Bind(R.id.iv_title_more)
    ImageView ivTitleMore;
    @Bind(R.id.vp_local)
    ViewPager viewPager;

    private View viewRoot;

    private LinearLayout llTitleMoreBlank;
    private View vTitleMoreBlank;
    private LinearLayout llTitleMoreSortByName;
    private LinearLayout llTitleMoreSortByTime;
    private LinearLayout llTitleMoreSortByCustom;
    private LinearLayout llTitleMoreScan;

    private LMBySongFragment LMBySongFragment;
    private LMBySingerFragment LMBySingerFragment;
    private LMByAlbumFragment LMByAlbumFragment;
    private LMByFolderFragment LMByFolderFragment;
    private List<String> mDatas = Arrays.asList("歌曲", "歌手", "专辑", "文件夹");
    // progress
    private CircleDialog circleDialog;
    // page[0-3]是否加载
    private boolean[] isLoad = new boolean[4];
    // 歌曲
    private List<MusicListModel> musicListModels;
    private List<MusicListModel> musicListModelsTemp;// 扫描后，二次更新
    private SongListAdapter songListAdapter;
    // 歌手
    private List<SingerListModel> singerListModels;
    private List<SingerListModel> singerListModelsTemp;// 扫描后，二次更新
    private SingerListAdapter singerListAdapter;
    // 专辑
    private List<AlbumListModel> albumListModels;
    private List<AlbumListModel> albumListModelsTemp;// 扫描后，二次更新
    private AlbumListAdapter albumListAdapter;
    // 文件夹
    private List<FolderListModel> folderListModels;
    private List<FolderListModel> folderListModelsTemp;// 扫描后，二次更新
    private FolderListAdapter folderListAdapter;

    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragments;
    private TopMenuDialog dialog;
    private View dialogView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ULog.v("生命周期:LocalMusicFragment:onCreat");

        musicListModels = new ArrayList<MusicListModel>();
        songListAdapter = new SongListAdapter(getActivity(), R.layout.list_song, musicListModels, 20);// index:20本地音乐

        singerListModels = new ArrayList<SingerListModel>();

        singerListAdapter = new SingerListAdapter(getActivity(), R.layout.list_singer, singerListModels);

        albumListModels = new ArrayList<AlbumListModel>();
        albumListAdapter = new AlbumListAdapter(getActivity(), R.layout.list_album, albumListModels);

        folderListModels = new ArrayList<FolderListModel>();
        folderListAdapter = new FolderListAdapter(getActivity(), R.layout.list_folder, folderListModels);

        new LocalMusicSongAsyncTask().execute();
        isLoad[0] = true;// page[0]已开始加载

        fragments = new ArrayList<Fragment>();
        LMBySongFragment = new LMBySongFragment(songListAdapter);
        LMBySingerFragment = new LMBySingerFragment(singerListAdapter);
        LMByAlbumFragment = new LMByAlbumFragment(albumListAdapter);
        LMByFolderFragment = new LMByFolderFragment(folderListAdapter);
        fragments.add(LMBySongFragment);
        fragments.add(LMBySingerFragment);
        fragments.add(LMByAlbumFragment);
        fragments.add(LMByFolderFragment);

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        ULog.v("生命周期:LocalMusicFragment:onResume");
        if (ScanActivity.isScanOver) {
            ScanActivity.isScanOver = false;// 恢复原始值
            viewPager.setCurrentItem(0, false);// 跳转到第一页
            new LocalMusicSongAsyncTask().execute();
            isLoad[0] = true;// page[0]已开始加载
            isLoad[1] = false;
            isLoad[2] = false;
            isLoad[3] = false;
        }
    }

    // 歌曲异步线程
    private class LocalMusicSongAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            circleDialog = new CircleDialog(getActivity());
            circleDialog.show();
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
            circleDialog.dismiss();
        }

    }

    // 歌手异步线程
    private class LocalMusicSingerAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (circleDialog != null)
                circleDialog.dismiss();
            circleDialog = new CircleDialog(getActivity());
            circleDialog.show();
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
            circleDialog.dismiss();
        }

    }

    // 专辑异步线程
    private class LocalMusicAlbumAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (circleDialog != null)
                circleDialog.dismiss();
            circleDialog = new CircleDialog(getActivity());
            circleDialog.show();
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
            circleDialog.dismiss();
        }

    }

    // 文件夹异步线程
    private class LocalMusicFolderAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            if (circleDialog != null)
                circleDialog.dismiss();
            circleDialog = new CircleDialog(getActivity());
            circleDialog.show();
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
            circleDialog.dismiss();
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
    }

    private void getSingerListFromDataBase() {
        singerListModels.clear();
        Cursor cursor = DataSupport.findBySQL("select *,count(*) from localallmusiclistmodel group by singer");
        if (null != cursor && cursor.moveToFirst()) {
            do {
                SingerListModel singerListModel = new SingerListModel();
                String singer = cursor.getString(cursor.getColumnIndex("singer"));
                int count = cursor.getInt(cursor.getColumnIndex("count(*)"));
                singerListModel.setSinger(singer);
                singerListModel.setCount(count);
                ULog.v("Singer----" + "singer:" + singer + "count:" + count);
                singerListModels.add(singerListModel);
            } while (cursor.moveToNext());
        }

    }

    private void getAlbumListFromDataBase() {
        albumListModels.clear();
        Cursor cursor = DataSupport.findBySQL("select *,count(*) from localallmusiclistmodel group by album");
        if (null != cursor && cursor.moveToFirst()) {
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
        if (null != cursor && cursor.moveToFirst()) {
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

    private void initView() {
        // TODO Auto-generated method stub
        tvTitleTitle.setText("本地音乐");
        ivTitleBack.setOnClickListener(this);
        ivTitleSearch.setOnClickListener(this);
        ivTitleMore.setOnClickListener(this);
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
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(3);// 高内存占用，持久化页面数
        viewPager.addOnPageChangeListener(this);

        mIndicator.setTabItemTitles(mDatas);
        mIndicator.setViewPager(viewPager, 0);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Log.v("生命周期:", "LocalMusicFragment:onCreateView");
        viewRoot = inflater.inflate(R.layout.fragment_local, container, false);
        ButterKnife.bind(this, viewRoot);
        initView();
        return viewRoot;
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        ULog.v("生命周期:LocalMusicFragment:onDestroyView");
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.iv_title_search:
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
            case R.id.ll_title_more_sort_by_custom:
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
        dialog = new TopMenuDialog(getActivity());
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

        p.width = windowsWidth * 1; // 宽度设置为屏幕的1
        p.height = windowsHeight * 1; // 高度设置为屏幕的0.618
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
        llTitleMoreSortByName.setVisibility(View.GONE);
        llTitleMoreSortByTime.setVisibility(View.GONE);
        llTitleMoreSortByCustom.setVisibility(View.GONE);
        llTitleMoreScan = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_scan);

        llTitleMoreBlank.setOnClickListener(this);
        vTitleMoreBlank.setOnClickListener(this);
        llTitleMoreSortByName.setOnClickListener(this);
        llTitleMoreSortByTime.setOnClickListener(this);
        llTitleMoreSortByCustom.setOnClickListener(this);
        llTitleMoreScan.setOnClickListener(this);

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        reloadList(position);
    }

}
