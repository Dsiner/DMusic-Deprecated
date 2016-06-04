package com.d.dmusic.mvp.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.activity.ListHandleActivity;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.dialog.CircleDialog;
import com.d.dmusic.dialog.TopMenuDialog;
import com.d.dmusic.global.Contains;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvp.adapter.SongListAdapter;
import com.d.dmusic.mvp.presenters.CustomMusicPresenter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
 * 自定义音乐首页
 * 
 * */
@SuppressLint("ValidFragment")
public class CustomMusicFragment extends BaseFragment<CustomMusicPresenter> implements MvpView, OnClickListener {

    @Bind(R.id.tv_title_title)
    TextView tvTitleTitle;
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @Bind(R.id.iv_title_more)
    ImageView ivTitleMore;
    @Bind(R.id.lv_custom_music_list)
    ListView lvCustomMusicList;

    // list头部
    private View header;
    private LinearLayout llListSongSubHeader;
    private TextView tvListSongSubHeaderCounts;
    private ImageView ivListSongSubHeaderHandle;

    private LinearLayout llTitleMoreBlank;
    private View vTitleMoreBlank;
    private LinearLayout llTitleMoreSortByName;// 按名称排序
    private LinearLayout llTitleMoreSortByTime;// 按时间排序
    private LinearLayout llTitleMoreSortByCustom;// 自定义排序
    private LinearLayout llTitleMoreScan;

    private TopMenuDialog dialog;
    private View dialogView;
    private int id;// 歌曲列表Table主键
    private int index;// 列表标识
    private String title;// 标题
    public static int sortBy;// 排序方式
    private int counts;// 歌曲总数

    private List<MusicListModel> musicListModels;
    private List<MusicListModel> musicListModelstemp;
    private SongListAdapter songListAdapter;
    public static boolean isNeedReload;// 是否需要重新加载数据
    // progress
    private CircleDialog circleDialog;

    public CustomMusicFragment() {
    }

    public CustomMusicFragment(int id, int index, String title, int sortBy) {
        // TODO Auto-generated constructor stub
        super();
        this.id = id;
        this.index = index;
        this.title = title;
        this.sortBy = sortBy;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_custom;
    }

    @Override
    public CustomMusicPresenter getPresenter() {
        return new CustomMusicPresenter(this.getActivity().getApplicationContext());
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void init() {
        tvTitleTitle.setText(title);
        initView();
        musicListModels = new ArrayList<MusicListModel>();
        musicListModelstemp = new ArrayList<MusicListModel>();
        preBeforeRefresh();
        isNeedReload = false;// 已开始加载数据

        mPresenter.refresh(musicListModelstemp, sortBy, index, counts, id, handler);
        ;// 加载数据
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (tvListSongSubHeaderCounts == null) {
                    return;
                }
                counts = msg.arg1;
                tvListSongSubHeaderCounts.setText(counts + "首");
                lvCustomMusicList.setAdapter(null);
                if (lvCustomMusicList.getHeaderViewsCount() > 0) {
                    lvCustomMusicList.removeHeaderView(header);
                }
                if (counts > 0) {
                    lvCustomMusicList.addHeaderView(header);
                }
                musicListModels = new ArrayList<MusicListModel>();
                musicListModels.addAll(musicListModelstemp);
                songListAdapter = new SongListAdapter(getActivity(), R.layout.list_song, musicListModels, index);
                lvCustomMusicList.setAdapter(songListAdapter);
                circleDialog.dismiss();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        isNeedReload = true;// 初始化此页面时，强制加载(isNeedReload为静态变量)
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (isNeedReload || ScanActivity.isScanOver) {
            preBeforeRefresh();
            mPresenter.refresh(musicListModelstemp, sortBy, index, counts, id, handler);
            isNeedReload = false;
            ScanActivity.isScanOver = false;// 归位
        }

    }

    /**
     * 显示圆形进度提示
     */
    private void preBeforeRefresh() {
        circleDialog = new CircleDialog(getActivity());
        circleDialog.show();
    }

    private void initView() {
        // TODO Auto-generated method stub
        header = LayoutInflater.from(getActivity()).inflate(R.layout.list_song_sub_header, null);
        llListSongSubHeader = (LinearLayout) header.findViewById(R.id.ll_list_song_sub_header);
        tvListSongSubHeaderCounts = (TextView) header.findViewById(R.id.tv_list_song_sub_header_counts);
        ivListSongSubHeaderHandle = (ImageView) header.findViewById(R.id.iv_list_song_sub_header_handle);

        ivTitleBack.setOnClickListener(this);
        ivTitleSearch.setOnClickListener(this);
        ivTitleMore.setOnClickListener(this);
        llListSongSubHeader.setOnClickListener(this);
        ivListSongSubHeaderHandle.setOnClickListener(this);
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
            case R.id.ll_list_song_sub_header:
                //播放全部
                MusicControlUtils musicControlUtils = MyService.getInstanceMusicControlUtils();
                List<MusicListModel> mlms = new ArrayList<MusicListModel>();
                mlms.addAll(musicListModelstemp);
                musicControlUtils.initList(mlms, 0);//播放第一条
                DataSupport.deleteAll(MusicListModel.class);// 删除数据库MusicListModel表中的所有记录
                DataSupport.saveAll(mlms);// MusicListModel表,即当前播放列表更新
                break;
            // 列表管理
            case R.id.iv_list_song_sub_header_handle:
                Intent intentListHandleActivity = new Intent(getActivity(), ListHandleActivity.class);
                intentListHandleActivity.putExtra("id", id);
                intentListHandleActivity.putExtra("index", index);

                Contains.musicListModels = new ArrayList<MusicListModel>();
                Contains.musicListModels.addAll(musicListModels);// 拷贝一份，在ListHandleActivity中使用
                getActivity().startActivity(intentListHandleActivity);
                break;
            case R.id.ll_title_more_blank:
                dialog.dismiss();
                break;
            case R.id.v_title_more_blank:
                dialog.dismiss();
                break;
            // 按名称查询
            case R.id.ll_title_more_sort_by_name:
                sortBy = 1;
                mPresenter.sortBy(sortBy, id);
                preBeforeRefresh();
                mPresenter.refresh(musicListModelstemp, sortBy, index, counts, id, handler);
                break;
            // 按时间查询
            case R.id.ll_title_more_sort_by_time:
                sortBy = 2;
                mPresenter.sortBy(sortBy, id);
                preBeforeRefresh();
                mPresenter.refresh(musicListModelstemp, sortBy, index, counts, id, handler);
                break;
            // 按时间查询
            case R.id.ll_title_more_sort_by_custom:
                sortBy = 3;
                mPresenter.sortBy(sortBy, id);
                preBeforeRefresh();
                mPresenter.refresh(musicListModelstemp, sortBy, index, counts, id, handler);
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
