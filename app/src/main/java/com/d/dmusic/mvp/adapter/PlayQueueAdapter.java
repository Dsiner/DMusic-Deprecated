package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.service.MyService;

import java.util.List;

public class PlayQueueAdapter extends CommonAdapter<MusicListModel> {
    private TextView tvPlayQueueCounts;

    public PlayQueueAdapter(Context context, int layoutId, List<MusicListModel> datas, TextView tvPlayQueueCounts) {
        super(context, layoutId, datas);
        this.tvPlayQueueCounts = tvPlayQueueCounts;
    }

    @Override
    public void convert(ViewHolder holder, MusicListModel musicListModel, int position) {
        setItemData(holder, musicListModel, position);
    }

    private void setItemData(final ViewHolder holder, final MusicListModel data, final int position) {
        holder.setText(R.id.tv_play_queue_sub_songname, data.getSongName());
        holder.setText(R.id.tv_play_queue_sub_singer, "--" + data.getSinger());
        holder.setOnClickListener(R.id.ll_play_queue_sub, new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyService.getInstanceMusicControlUtils().playByPosition(position);
            }
        });
        holder.setOnClickListener(R.id.iv_play_queue_sub_delete, new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyService.getInstanceMusicControlUtils().DelelteByPosition(position);
                notifyDataSetChanged();
                tvPlayQueueCounts.setText(mDatas.size() + "首");
            }
        });
    }
}
