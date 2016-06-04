package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.d.dmusic.R;
import com.d.dmusic.models.SingerListModel;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.mvp.fragments.LMBySingerMusicFragment;
import com.d.dmusic.mvp.fragments.MainFragment;

import java.util.List;

public class SingerListAdapter extends CommonAdapter<SingerListModel> {

    public SingerListAdapter(Context context, int layoutId, List<SingerListModel> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, SingerListModel singerListModel, int position) {
        setItemData(holder, singerListModel, position);
    }

    private void setItemData(ViewHolder holder, final SingerListModel data, int position) {

        holder.setText(R.id.tv_singer_list_sub_name, data.getSinger());
        holder.setText(R.id.tv_singer_list_sub_title, data.getCount() + "");
        holder.setOnClickListener(R.id.ll_singer_list_sub, new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.fragmentManager.beginTransaction()
                        .replace(R.id.main_framelayout,
                                new LMBySingerMusicFragment(data.getSinger()))
                        .addToBackStack(null).commit();
            }
        });
    }
}
