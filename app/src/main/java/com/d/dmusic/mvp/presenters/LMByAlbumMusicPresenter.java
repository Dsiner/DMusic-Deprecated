package com.d.dmusic.mvp.presenters;

import android.content.Context;
import android.os.Handler;

import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.utils.TaskManager;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by D on 2016/6/4.
 */
public class LMByAlbumMusicPresenter extends MvpBasePresenter<MvpView> {
    private List<MusicListModel> musicListModels;

    public LMByAlbumMusicPresenter(Context context) {
        super(context);
    }

    public void refresh(List<MusicListModel> mlms, final String album, final Handler handler) {
        this.musicListModels = mlms;
        TaskManager.getIns().executeTask(new Runnable() {
            @Override
            public void run() {
                List<LocalAllMusicListModel> localAllMusicListModels = DataSupport.where("album = ?", album)
                        .find(LocalAllMusicListModel.class);
                //TODO "!= null" is Right? && or else
                if (localAllMusicListModels != null) {
                    musicListModels.clear();
                    for (LocalAllMusicListModel localAllMusicListModel : localAllMusicListModels) {
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
                if (getView() != null) {
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }
}
