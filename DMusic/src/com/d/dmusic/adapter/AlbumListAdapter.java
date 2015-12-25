package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.AlbumListModel;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;
import com.d.dmusic.fragment.LocalMusicFragment;
import com.d.dmusic.fragment.LocalMusicFragmentByAlbumMusicFragment;
import com.d.dmusic.fragment.MainFragment;
import com.d.dmusic.global.Contains;
import com.d.dmusic.service.MusicControlUtils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlbumListAdapter extends BaseAdapter {
	private Context context;
	private List<AlbumListModel> albumListModels;

	public AlbumListAdapter(Context context, List<AlbumListModel> albumListModels) {
		this.context = context;
		this.albumListModels = albumListModels;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return albumListModels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return albumListModels.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHoldertemp = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.list_album, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llAlbumListSub = (LinearLayout) convertView.findViewById(R.id.ll_album_list_sub);
			viewHoldertemp.tvAlbumListSubName = (TextView) convertView.findViewById(R.id.tv_album_list_sub_name);
			viewHoldertemp.tvAlbumListSubTitle = (TextView) convertView.findViewById(R.id.tv_album_list_sub_title);
			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvAlbumListSubName.setText(albumListModels.get(position).getAlbum());
		viewHolder.tvAlbumListSubTitle.setText(albumListModels.get(position).getCount() + "");
		viewHolder.llAlbumListSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainFragment.fragmentManager.beginTransaction()
						.replace(R.id.main_framelayout,
								new LocalMusicFragmentByAlbumMusicFragment(albumListModels.get(position).getAlbum()))
						.addToBackStack(null).commit();
			}
		});

		return convertView;

	}

	class ViewHolder {
		LinearLayout llAlbumListSub;
		TextView tvAlbumListSubName;
		TextView tvAlbumListSubTitle;
	}

}
