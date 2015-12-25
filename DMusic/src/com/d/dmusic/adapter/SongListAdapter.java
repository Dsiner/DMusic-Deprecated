package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseInsert;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.MyDialogAddToList;
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
import android.widget.Toast;

public class SongListAdapter extends BaseAdapter {
	private Context context;
	private int index;// �б��ʶ
	private List<MusicListModel> musicListModels;

	public SongListAdapter(Context context, int index, List<MusicListModel> musicListModels) {
		this.context = context;
		this.index = index;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_song, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llMusicListSub = (LinearLayout) convertView.findViewById(R.id.ll_music_list_sub);
			viewHoldertemp.tvMusicListSubName = (TextView) convertView.findViewById(R.id.tv_music_list_sub_name);
			viewHoldertemp.tvMusicListSubTitle = (TextView) convertView.findViewById(R.id.tv_music_list_sub_title);
			viewHoldertemp.llMusicListSubMore = (LinearLayout) convertView.findViewById(R.id.ll_music_list_sub_more);
			viewHoldertemp.ctvMusicListSubMore = (CheckedTextView) convertView
					.findViewById(R.id.ctv_music_list_sub_more);
			viewHoldertemp.llMusicListSubMoreCover = (LinearLayout) convertView
					.findViewById(R.id.ll_music_list_sub_more_cover);

			viewHoldertemp.llMusicListSubCollect = (LinearLayout) convertView
					.findViewById(R.id.ll_music_list_sub_collect);

			viewHoldertemp.llMusicListSubAddToList = (LinearLayout) convertView
					.findViewById(R.id.ll_music_list_sub_add_to_list);
			viewHoldertemp.llMusicListSubSongInfo = (LinearLayout) convertView
					.findViewById(R.id.ll_music_list_sub_song_info);

			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvMusicListSubName.setText(musicListModels.get(position).getSongName());
		viewHolder.tvMusicListSubTitle.setText(musicListModels.get(position).getSinger());
		if (musicListModels.get(position).isSelected()) {
			viewHolder.ctvMusicListSubMore.setChecked(true);
			viewHolder.llMusicListSubMoreCover.setVisibility(View.VISIBLE);
		} else {
			viewHolder.ctvMusicListSubMore.setChecked(false);
			viewHolder.llMusicListSubMoreCover.setVisibility(View.GONE);
		}
		viewHolder.llMusicListSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MusicControlUtils musicControlUtils = MyService.getInstanceMusicControlUtils();
				List<MusicListModel> mlms = new ArrayList<MusicListModel>();
				mlms.addAll(musicListModels);
				musicControlUtils.initList(mlms, position);
				DataSupport.deleteAll(MusicListModel.class);// ɾ�����ݿ�MusicListModel���е����м�¼
				DataSupport.saveAll(mlms);// MusicListModel��,����ǰ�����б����
			}
		});
		viewHolder.llMusicListSubMore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (viewHolder.ctvMusicListSubMore.isChecked()) {
					viewHolder.ctvMusicListSubMore.setChecked(false);
					viewHolder.llMusicListSubMoreCover.setVisibility(View.GONE);
					musicListModels.get(position).setSelected(false);
				} else {
					viewHolder.ctvMusicListSubMore.setChecked(true);
					viewHolder.llMusicListSubMoreCover.setVisibility(View.VISIBLE);
					musicListModels.get(position).setSelected(true);
				}
			}
		});

		viewHolder.llMusicListSubCollect.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<MusicListModel> mlm = new ArrayList<MusicListModel>();
				mlm.add(musicListModels.get(position));
				// ����ø����Ѵ������ղ��б�
				if (DataBaseInsert.insertColletinoMusicListIntoDataBase(mlm)) {
					// �������˵��ջ�
					viewHolder.ctvMusicListSubMore.setChecked(false);
					viewHolder.llMusicListSubMoreCover.setVisibility(View.GONE);
					musicListModels.get(position).setSelected(false);
				} else {
					Toast.makeText(context, "�����Ѵ��ڣ�", Toast.LENGTH_SHORT).show();
				}
			}
		});
		viewHolder.llMusicListSubAddToList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<MusicListModel> mlm = new ArrayList<MusicListModel>();
				mlm.add(musicListModels.get(position));
				new MyDialogAddToList(context, index, mlm).show();
			}
		});
		viewHolder.llMusicListSubSongInfo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyDialogSongInfo(context, musicListModels.get(position)).show();
			}
		});
		return convertView;

	}

	class ViewHolder {
		LinearLayout llMusicListSub;
		TextView tvMusicListSubName;
		TextView tvMusicListSubTitle;
		LinearLayout llMusicListSubMore;
		CheckedTextView ctvMusicListSubMore;
		LinearLayout llMusicListSubMoreCover;
		LinearLayout llMusicListSubCollect;
		LinearLayout llMusicListSubAddToList;
		LinearLayout llMusicListSubSongInfo;
	}

}
