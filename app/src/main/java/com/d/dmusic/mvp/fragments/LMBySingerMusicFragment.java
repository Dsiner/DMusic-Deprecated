package com.d.dmusic.mvp.fragments;

import android.annotation.SuppressLint;
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
import com.d.dmusic.dialog.CircleDialog;
import com.d.dmusic.dialog.TopMenuDialog;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvp.adapter.SongListAdapter;
import com.d.dmusic.mvp.presenters.LMBySingerMusicPresenter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * 本地音乐-专辑-Sub
 * 
 * */
@SuppressLint("ValidFragment")
public class LMBySingerMusicFragment extends BaseFragment<LMBySingerMusicPresenter> implements MvpView, OnClickListener {
    @Bind(R.id.tv_title_title)
    TextView tvTitleTitle;
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @Bind(R.id.iv_title_more)
    ImageView ivTitleMore;
    @Bind(R.id.lv_custom_music_list)
    ListView lvCustomMusicList; // 列表

    private LinearLayout llTitleMoreBlank;
    private View vTitleMoreBlank;
    private LinearLayout llTitleMoreSortByName;
    private LinearLayout llTitleMoreSortByTime;
    private LinearLayout llTitleMoreScan;

    private TopMenuDialog dialog;
    private View dialogView;
    private List<MusicListModel> musicListModels;
    private SongListAdapter songListAdapter;
    private String singer;// 歌手
    private boolean isLoad;

    // progress
    private CircleDialog circleDialog;

    public LMBySingerMusicFragment(String singer) {
        // TODO Auto-generated constructor stub
        super();
        this.singer = singer;
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                songListAdapter.notifyDataSetChanged();
                circleDialog.dismiss();
            }
        }

        ;
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_custom;
    }

    @Override
    public LMBySingerMusicPresenter getPresenter() {
        return new LMBySingerMusicPresenter(this.getActivity().getApplicationContext());
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void init() {
        ivTitleMore.setVisibility(View.GONE);
        tvTitleTitle.setText("歌手");
        musicListModels = new ArrayList<MusicListModel>();
        songListAdapter = new SongListAdapter(getActivity(), R.layout.list_song, musicListModels, 20);// index:20本地音乐
        lvCustomMusicList.setAdapter(songListAdapter);
    }

    @OnClick({R.id.iv_title_back, R.id.iv_title_search, R.id.iv_title_more})
    public void OnClickLister(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.iv_title_search:
                break;
            case R.id.iv_title_more:
                initDialog();
                dialog.show();
                break;
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (isLoad == false) {
            circleDialog = new CircleDialog(getActivity());
            circleDialog.show();
            mPresenter.refresh(musicListModels, singer, handler);
            isLoad = true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        p.height = (int) (windowsHeight * 1); // 高度设置为屏幕的0.618
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
