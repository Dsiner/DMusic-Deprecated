package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;

import com.d.dmusic.R;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.database.models.CollectionMusicListModel;
import com.d.dmusic.dialog.SongInfoDialog;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的收藏-列表Adapter
 *
 * @author D
 */
public class CollectionSongListAdapter extends CommonAdapter<MusicListModel> {

    public CollectionSongListAdapter(Context context, int layoutId, List<MusicListModel> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, MusicListModel musicListModel, int position) {
        setItemData(holder, musicListModel, position);
    }

    private void setItemData(final ViewHolder holder, final MusicListModel data, final int position) {
        holder.setText(R.id.tv_collection_song_list_sub_name, data.getSongName());
        holder.setText(R.id.tv_collection_song_list_sub_title, data.getSinger() + data.getSongName());
        if (data.isSelected()) {
            holder.setChecked(R.id.ctv_collection_song_list_sub_more, true);
            holder.setVisible(R.id.ll_collection_song_list_sub_more_cover, View.VISIBLE);
        } else {
            holder.setChecked(R.id.ctv_collection_song_list_sub_more, false);
            holder.setVisible(R.id.ll_collection_song_list_sub_more_cover, View.GONE);
        }
        holder.setOnClickListener(R.id.ll_collection_song_list_sub, new OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicControlUtils musicControlUtils = MyService.getInstanceMusicControlUtils();
                List<MusicListModel> mlms = new ArrayList<MusicListModel>();
                mlms.addAll(mDatas);
                musicControlUtils.initList(mlms, position);
                DataSupport.deleteAll(MusicListModel.class);// 删除数据库MusicListModel表中的所有记录
                DataSupport.saveAll(mlms);// MusicListModel表,即当前播放列表更新
            }
        });
        holder.setOnClickListener(R.id.ll_collection_song_list_sub_more, new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckedTextView) holder.getView(R.id.ctv_collection_song_list_sub_more)).isChecked()) {
                    holder.setChecked(R.id.ctv_collection_song_list_sub_more, false);
                    holder.setVisible(R.id.ll_collection_song_list_sub_more_cover, View.GONE);
                    data.setSelected(false);
                } else {
                    holder.setChecked(R.id.ctv_collection_song_list_sub_more, true);
                    holder.setVisible(R.id.ll_collection_song_list_sub_more_cover, View.VISIBLE);
                    data.setSelected(true);
                }
            }
        });
        holder.setOnClickListener(R.id.ll_collection_song_list_sub_remove_collection, new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消收藏
                DataSupport.delete(CollectionMusicListModel.class, data.getId());
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.setOnClickListener(R.id.ll_collection_song_list_sub_song_info, new OnClickListener() {
            @Override
            public void onClick(View v) {
                new SongInfoDialog(mContext, data).show();
            }
        });
    }
}
