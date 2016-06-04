package com.d.dmusic.mvp.fragments;

import android.annotation.SuppressLint;
import android.widget.ListView;

import com.d.dmusic.R;
import com.d.dmusic.mvp.adapter.SingerListAdapter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;

import butterknife.Bind;

/*
 * 本地音乐-歌手
 * 
 * */
@SuppressLint("ValidFragment")
public class LMBySingerFragment extends BaseFragment<MvpBasePresenter> implements MvpView {
    @Bind(R.id.lv_local_singer_list)
    ListView lvLocalMusicList;
    private SingerListAdapter singerListAdapter;

    public LMBySingerFragment(SingerListAdapter singerListAdapter) {
        this.singerListAdapter = singerListAdapter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_local_singer;
    }

    @Override
    public MvpBasePresenter getPresenter() {
        return new MvpBasePresenter(this.getActivity().getApplicationContext());
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void init() {
        lvLocalMusicList.setAdapter(singerListAdapter);
    }

}
