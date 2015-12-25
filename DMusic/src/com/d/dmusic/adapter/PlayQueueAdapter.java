package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;
import com.d.dmusic.global.Contains;
import com.d.dmusic.service.MusicControlUtils;
import com.d.dmusic.service.MyService;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PlayQueueAdapter extends BaseAdapter {
	private Context context;
	private List<MusicListModel> musicListModels;
	private TextView tvPlayQueueCounts;

	public PlayQueueAdapter(Context context, List<MusicListModel> musicListModels, TextView tvPlayQueueCounts) {
		this.context = context;
		this.musicListModels = musicListModels;
		this.tvPlayQueueCounts = tvPlayQueueCounts;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return musicListModels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return musicListModels.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.play_queue_sub, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llPlayQueueSub = (LinearLayout) convertView.findViewById(R.id.ll_play_queue_sub);
			viewHoldertemp.tvPlayQueueSubSongName = (TextView) convertView
					.findViewById(R.id.tv_play_queue_sub_songname);
			viewHoldertemp.tvPlayQueueSubSinger = (TextView) convertView.findViewById(R.id.tv_play_queue_sub_singer);
			viewHoldertemp.ivPlayQueueSubDelete = (ImageView) convertView.findViewById(R.id.iv_play_queue_sub_delete);
			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvPlayQueueSubSongName.setText(musicListModels.get(position).getSongName());
		viewHolder.tvPlayQueueSubSinger.setText(musicListModels.get(position).getSinger());
		viewHolder.llPlayQueueSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyService.getInstanceMusicControlUtils().playByPosition(position);
			}
		});
		viewHolder.ivPlayQueueSubDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MyService.getInstanceMusicControlUtils().DelelteByPosition(position);
				notifyDataSetChanged();
				tvPlayQueueCounts.setText(musicListModels.size() + "สื");
			}
		});

		return convertView;

	}

	class ViewHolder {
		LinearLayout llPlayQueueSub;
		TextView tvPlayQueueSubSongName;
		TextView tvPlayQueueSubSinger;
		ImageView ivPlayQueueSubDelete;
	}

}
