package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.CollectionMusicListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.MyDialogSongInfo;
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
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 我的收藏-列表Adapter
 * 
 * @author D
 *
 */
public class CollectionSongListAdapter extends BaseAdapter {
	private Context context;
	private List<MusicListModel> musicListModels;

	public CollectionSongListAdapter(Context context, List<MusicListModel> musicListModels) {
		this.context = context;
		this.musicListModels = musicListModels;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_collection_song, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llCollectionSongListSub = (LinearLayout) convertView
					.findViewById(R.id.ll_collection_song_list_sub);
			viewHoldertemp.tvCollectionSongListSubName = (TextView) convertView
					.findViewById(R.id.tv_collection_song_list_sub_name);
			viewHoldertemp.tvCollectionSongListSubTitle = (TextView) convertView
					.findViewById(R.id.tv_collection_song_list_sub_title);
			viewHoldertemp.llCollectionSongListSubMore = (LinearLayout) convertView
					.findViewById(R.id.ll_collection_song_list_sub_more);
			viewHoldertemp.ctvCollectionSongListSubMore = (CheckedTextView) convertView
					.findViewById(R.id.ctv_collection_song_list_sub_more);
			viewHoldertemp.llCollectionSongListSubMoreCover = (LinearLayout) convertView
					.findViewById(R.id.ll_collection_song_list_sub_more_cover);

			viewHoldertemp.llCollectionSongListSubRemoveCollection = (LinearLayout) convertView
					.findViewById(R.id.ll_collection_song_list_sub_remove_collection);
			viewHoldertemp.llCollectionSongListSubSongInfo = (LinearLayout) convertView
					.findViewById(R.id.ll_collection_song_list_sub_song_info);

			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvCollectionSongListSubName.setText(musicListModels.get(position).getSongName());
		viewHolder.tvCollectionSongListSubTitle
				.setText(musicListModels.get(position).getSinger() + musicListModels.get(position).getSongName());
		if (musicListModels.get(position).isSelected()) {
			viewHolder.ctvCollectionSongListSubMore.setChecked(true);
			viewHolder.llCollectionSongListSubMoreCover.setVisibility(View.VISIBLE);
		} else {
			viewHolder.ctvCollectionSongListSubMore.setChecked(false);
			viewHolder.llCollectionSongListSubMoreCover.setVisibility(View.GONE);
		}
		viewHolder.llCollectionSongListSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MusicControlUtils musicControlUtils = MyService.getInstanceMusicControlUtils();
				List<MusicListModel> mlms = new ArrayList<MusicListModel>();
				mlms.addAll(musicListModels);
				musicControlUtils.initList(mlms, position);
				DataSupport.deleteAll(MusicListModel.class);// 删除数据库MusicListModel表中的所有记录
				DataSupport.saveAll(mlms);// MusicListModel表,即当前播放列表更新
			}
		});
		viewHolder.llCollectionSongListSubMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (viewHolder.ctvCollectionSongListSubMore.isChecked()) {
					viewHolder.ctvCollectionSongListSubMore.setChecked(false);
					viewHolder.llCollectionSongListSubMoreCover.setVisibility(View.GONE);
					musicListModels.get(position).setSelected(false);
				} else {
					viewHolder.ctvCollectionSongListSubMore.setChecked(true);
					viewHolder.llCollectionSongListSubMoreCover.setVisibility(View.VISIBLE);
					musicListModels.get(position).setSelected(true);
				}
			}
		});

		viewHolder.llCollectionSongListSubRemoveCollection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 取消收藏
				DataSupport.delete(CollectionMusicListModel.class, musicListModels.get(position).getId());
				musicListModels.remove(position);
				notifyDataSetChanged();
			}
		});

		viewHolder.llCollectionSongListSubSongInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyDialogSongInfo(context, musicListModels.get(position)).show();
			}
		});
		return convertView;

	}

	class ViewHolder {
		LinearLayout llCollectionSongListSub;
		TextView tvCollectionSongListSubName;
		TextView tvCollectionSongListSubTitle;
		LinearLayout llCollectionSongListSubMore;
		CheckedTextView ctvCollectionSongListSubMore;
		LinearLayout llCollectionSongListSubMoreCover;
		LinearLayout llCollectionSongListSubRemoveCollection;
		LinearLayout llCollectionSongListSubSongInfo;
	}

}
