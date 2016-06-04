package com.d.dmusic.mvp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.dialog.CircleDialog;
import com.d.dmusic.dialog.TopMenuDialog;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvp.adapter.CollectionSongListAdapter;
import com.d.dmusic.mvp.presenters.CollectionMusicPresenter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.view.ListViewBelowScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/*
 * 我的收藏页面
 * 
 * */
public class CollectionMusicFragment extends BaseFragment<CollectionMusicPresenter> implements MvpView, OnClickListener {

    @Bind(R.id.tv_title_title)
    TextView tvTitleTitle;
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.iv_title_search)
    ImageView ivTitleSearch;
    @Bind(R.id.iv_title_more)
    ImageView ivTitleMore;
    @Bind(R.id.lv_collection_music_list)
    ListViewBelowScrollView lvCollectionMusicList; // 列表

    private LinearLayout llTitleMoreBlank;
    private View vTitleMoreBlank;
    private LinearLayout llTitleMoreSortByName;
    private LinearLayout llTitleMoreSortByTime;
    private LinearLayout llTitleMoreSortByCustom;
    private LinearLayout llTitleMoreScan;

    private TopMenuDialog dialog;
    private View dialogView;
    private List<MusicListModel> musicListModels;
    private CollectionSongListAdapter collectionSongListAdapter;
    private boolean isload;// 是否已加载数据

    // progress
    private CircleDialog circleDialog;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                collectionSongListAdapter.notifyDataSetChanged();
                circleDialog.dismiss();
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_collection;
    }

    @Override
    public CollectionMusicPresenter getPresenter() {
        return new CollectionMusicPresenter(this.getActivity().getApplicationContext());
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void init() {
        ivTitleMore.setVisibility(View.GONE);
        tvTitleTitle.setText("我的收藏");
        ivTitleBack.setOnClickListener(this);
        ivTitleSearch.setOnClickListener(this);
        ivTitleMore.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    private void refresh() {
        musicListModels = new ArrayList<MusicListModel>();
        collectionSongListAdapter = new CollectionSongListAdapter(getActivity(), R.layout.list_collection_song, musicListModels);
        lvCollectionMusicList.setAdapter(collectionSongListAdapter);
        mPresenter.refresh(musicListModels, handler);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (isload == false) {
            circleDialog = new CircleDialog(getActivity());
            circleDialog.show();
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
        llTitleMoreSortByName.setVisibility(View.GONE);
        llTitleMoreSortByTime.setVisibility(View.GONE);
        llTitleMoreSortByCustom.setVisibility(View.GONE);
        llTitleMoreScan = (LinearLayout) dialogView.findViewById(R.id.ll_title_more_scan);

        llTitleMoreBlank.setOnClickListener(this);
        vTitleMoreBlank.setOnClickListener(this);
        llTitleMoreSortByName.setOnClickListener(this);
        llTitleMoreSortByTime.setOnClickListener(this);
        llTitleMoreScan.setOnClickListener(this);

    }

}
