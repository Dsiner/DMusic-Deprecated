package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;

import com.d.dmusic.R;
import com.d.dmusic.models.FolderListModel;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.mvp.fragments.LMByFolderMusicFragment;
import com.d.dmusic.mvp.fragments.MainFragment;

import java.util.List;

/**
 * Created by D on 2016/6/2.
 */
public class FolderListAdapter extends CommonAdapter<FolderListModel> {
    public FolderListAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, FolderListModel data, int position) {
        setItemData(holder, data, position);
    }

    private void setItemData(final ViewHolder holder, final FolderListModel data, final int position) {
        holder.setText(R.id.tv_folder_list_dir_name, data.getFolder());
        holder.setText(R.id.tv_folder_list_music_counts, data.getCount() + "");
        holder.setOnClickListener(R.id.ll_folder_list_sub, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.fragmentManager.beginTransaction()
                        .replace(R.id.main_framelayout,
                                new LMByFolderMusicFragment(data.getFolder()))
                        .addToBackStack(null).commit();
            }
        });
    }
}
