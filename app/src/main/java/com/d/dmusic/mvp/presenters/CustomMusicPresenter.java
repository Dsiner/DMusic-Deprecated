package com.d.dmusic.mvp.presenters;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.d.dmusic.database.DataBaseQuery;
import com.d.dmusic.database.DataBaseUpdate;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.utils.TaskManager;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by D on 2016/6/4.
 */
public class CustomMusicPresenter extends MvpBasePresenter<MvpView> {
    private List<MusicListModel> musicListModels;
    private int mCounts;

    public CustomMusicPresenter(Context context) {
        super(context);
    }

    public void refresh(List<MusicListModel> mlms, int sortBy, final int index, int counts, final int id, final Handler handler) {
        musicListModels = mlms;
        mCounts = counts;
        switch (sortBy) {
            case 2:
                // 按时间排序-即按id主键查询
                TaskManager.getIns().executeTask(new Runnable() {
                    @Override
                    public void run() {
                        mCounts = DataBaseQuery.getMusicListFromDataBaseByTime(index, musicListModels);
                        DataBaseUpdate.updateCountsById(id, mCounts);// 更新数据库自定义列表歌曲数量
                        if (getView() != null) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.arg1 = mCounts;
                            handler.sendMessage(msg);
                        }
                    }
                });
                break;
            case 3:
                // 自定义排序
                TaskManager.getIns().executeTask(new Runnable() {
                    @Override
                    public void run() {
                        mCounts = DataBaseQuery.getMusicListFromDataBaseBycustomSeq(index, musicListModels);
                        DataBaseUpdate.updateCountsById(id, mCounts);// 更新数据库自定义列表歌曲数量
                        if (getView() != null) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.arg1 = mCounts;
                            handler.sendMessage(msg);
                        }
                    }
                });
                break;
            default:
                // 按名称排序
                TaskManager.getIns().executeTask(new Runnable() {
                    @Override
                    public void run() {
                        mCounts = DataBaseQuery.getMusicListFromDataBaseByName(index, musicListModels);
                        DataBaseUpdate.updateCountsById(id, mCounts);// 更新数据库自定义列表歌曲数量
                        if (getView() != null) {
                            Message msg = new Message();
                            msg.what = 1;
                            msg.arg1 = mCounts;
                            handler.sendMessage(msg);
                        }
                    }
                });
                break;
        }
    }

    public void sortBy(final int sortBy, final int id) {
        ContentValues valuesTime = new ContentValues();
        valuesTime.put("sortby", sortBy);
        DataSupport.update(CustomMusicListModel.class, valuesTime, id);// 更新当前歌曲列表排序方式
    }
}
