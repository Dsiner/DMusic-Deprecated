package com.d.dmusic.mvp.fragments;

import android.annotation.SuppressLint;
import android.widget.ListView;

import com.d.dmusic.R;
import com.d.dmusic.mvp.adapter.AlbumListAdapter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;

import butterknife.Bind;

/*
 * 本地音乐-专辑
 * 
 * */
@SuppressLint("ValidFragment")
public class LMByAlbumFragment extends BaseFragment<MvpBasePresenter> implements MvpView {
    @Bind(R.id.lv_local_album_list)
    ListView lvLocalMusicList;
    private AlbumListAdapter albumListAdapter;

    public LMByAlbumFragment(AlbumListAdapter albumListAdapter) {
        this.albumListAdapter = albumListAdapter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_local_album;
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
        lvLocalMusicList.setAdapter(albumListAdapter);
    }


}
