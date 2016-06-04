package com.d.dmusic.mvp.fragments;

import com.d.dmusic.R;
import com.d.dmusic.mvp.adapter.SongListAdapter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;

import android.annotation.SuppressLint;
import android.widget.ListView;

import butterknife.Bind;

/*
 * 本地音乐-歌曲
 * 
 * */
@SuppressLint("ValidFragment")
public class LMBySongFragment extends BaseFragment<MvpBasePresenter> implements MvpView {
    @Bind(R.id.lv_local_music_list)
    ListView lvLocalMusicList;

    private SongListAdapter songListAdapter;

    public LMBySongFragment(SongListAdapter songListAdapter) {
        this.songListAdapter = songListAdapter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_local_song;
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
        lvLocalMusicList.setAdapter(songListAdapter);
    }

}
