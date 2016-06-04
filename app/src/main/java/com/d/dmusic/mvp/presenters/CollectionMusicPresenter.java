package com.d.dmusic.mvp.presenters;

import android.content.Context;
import android.os.Handler;

import com.d.dmusic.R;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvp.adapter.CollectionSongListAdapter;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.utils.TaskManager;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D on 2016/6/4.
 */
public class CollectionMusicPresenter extends MvpBasePresenter<MvpView> {
    private List<MusicListModel> musicListModels;

    public CollectionMusicPresenter(Context context) {
        super(context);
    }

    public void refresh(List<MusicListModel> mlms, final Handler handler) {
        musicListModels = mlms;
        TaskManager.getIns().executeTask(new Runnable() {
            @Override
            public void run() {
                DataBaseUtils.getCollectionMusicListFromDataBase(musicListModels);
                if (getView() != null) {
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }
}
