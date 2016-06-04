package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.d.dmusic.R;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.database.DataBaseInsert;
import com.d.dmusic.dialog.AddToListDialog;
import com.d.dmusic.dialog.SongInfoDialog;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class SongListAdapter extends CommonAdapter<MusicListModel> {
    private int index;// 列表标识

    public SongListAdapter(Context context, int layoutId, List<MusicListModel> datas, int index) {
        super(context, layoutId, datas);
        this.index = index;
    }

    @Override
    public void convert(ViewHolder holder, MusicListModel musicListModel, int position) {
        setItemData(holder, musicListModel, position);
    }

    private void setItemData(final ViewHolder holder, final MusicListModel data, final int position) {
        holder.setText(R.id.tv_music_list_sub_name, data.getSongName());
        holder.setText(R.id.tv_music_list_sub_title, data.getSinger());
        if (data.isSelected()) {
            holder.setChecked(R.id.ctv_music_list_sub_more, true);
            holder.setVisible(R.id.ll_music_list_sub_more_cover, View.VISIBLE);
        } else {
            holder.setChecked(R.id.ctv_music_list_sub_more, false);
            holder.setVisible(R.id.ll_music_list_sub_more_cover, View.GONE);
        }

        holder.setOnClickListener(R.id.ll_music_list_sub, new OnClickListener() {
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
        holder.setOnClickListener(R.id.ll_music_list_sub_more, new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckedTextView) holder.getView(R.id.ctv_music_list_sub_more)).isChecked()) {
                    holder.setChecked(R.id.ctv_music_list_sub_more, false);
                    holder.setVisible(R.id.ll_music_list_sub_more_cover, View.GONE);
                    data.setSelected(false);
                } else {
                    holder.setChecked(R.id.ctv_music_list_sub_more, true);
                    holder.setVisible(R.id.ll_music_list_sub_more_cover, View.VISIBLE);
                    data.setSelected(true);
                }
            }
        });
        holder.setOnClickListener(R.id.ll_music_list_sub_collect, new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MusicListModel> mlm = new ArrayList<MusicListModel>();
                mlm.add(data);
                // 如果该歌曲已存在于收藏列表
                if (DataBaseInsert.insertColletinoMusicListIntoDataBase(mlm)) {
                    // 将下拉菜单收回
                    holder.setChecked(R.id.ctv_music_list_sub_more, false);
                    holder.setVisible(R.id.ll_music_list_sub_more_cover, View.GONE);
                    data.setSelected(false);
                } else {
                    Toast.makeText(mContext, "歌曲已存在！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.setOnClickListener(R.id.ll_music_list_sub_add_to_list, new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MusicListModel> mlm = new ArrayList<MusicListModel>();
                mlm.add(data);
                new AddToListDialog(mContext, index, mlm).show();
            }
        });
        holder.setOnClickListener(R.id.ll_music_list_sub_song_info, new OnClickListener() {
            @Override
            public void onClick(View v) {
                new SongInfoDialog(mContext, data).show();
            }
        });
    }
}
