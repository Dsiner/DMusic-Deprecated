package com.d.dmusic.mvp.fragments;

import com.d.dmusic.R;
import com.d.dmusic.mvp.adapter.FolderListAdapter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;

import android.annotation.SuppressLint;
import android.widget.ListView;

import butterknife.Bind;

/*
 * 本地音乐-文件夹
 * 
 * */
@SuppressLint("ValidFragment")
public class LMByFolderFragment extends BaseFragment<MvpBasePresenter> implements MvpView {
    @Bind(R.id.lv_local_folder_list)
    ListView lvLocalMusicList;
    private FolderListAdapter folderListAdapter;

    public LMByFolderFragment(FolderListAdapter folderListAdapter) {
        this.folderListAdapter = folderListAdapter;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_local_folder;
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
        lvLocalMusicList.setAdapter(folderListAdapter);
    }


}
