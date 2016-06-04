package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.d.dmusic.R;
import com.d.dmusic.models.AlbumListModel;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.mvp.fragments.LMByAlbumMusicFragment;
import com.d.dmusic.mvp.fragments.MainFragment;

import java.util.List;

/**
 * 专辑适配器
 */
public class AlbumListAdapter extends CommonAdapter<AlbumListModel> {

    public AlbumListAdapter(Context context, int layoutId, List<AlbumListModel> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, AlbumListModel albumListModel, int position) {
        setItemData(holder, albumListModel, position);
    }

    private void setItemData(final ViewHolder holder, final AlbumListModel data, final int position) {
        holder.setText(R.id.tv_album_list_sub_name, data.getAlbum());
        holder.setText(R.id.tv_album_list_sub_title, data.getCount() + "");
        holder.setOnClickListener(R.id.ll_album_list_sub, new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.fragmentManager.beginTransaction()
                        .replace(R.id.main_framelayout,
                                new LMByAlbumMusicFragment(data.getAlbum()))
                        .addToBackStack(null).commit();
            }
        });
    }
}
