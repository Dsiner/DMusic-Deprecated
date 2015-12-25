package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.FolderListModel;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;
import com.d.dmusic.fragment.LocalMusicFragmentByAlbumMusicFragment;
import com.d.dmusic.fragment.LocalMusicFragmentByFolderMusicFragment;
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

public class FolderListAdapter extends BaseAdapter {
	private Context context;
	private List<FolderListModel> folderListModels;

	public FolderListAdapter(Context context, List<FolderListModel> folderListModels) {
		this.context = context;
		this.folderListModels = folderListModels;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return folderListModels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return folderListModels.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_folder, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llFolderListSub = (LinearLayout) convertView.findViewById(R.id.ll_folder_list_sub);
			viewHoldertemp.tvFolderListSubName = (TextView) convertView.findViewById(R.id.tv_folder_list_dir_name);
			viewHoldertemp.tvFolderListSubTitle = (TextView) convertView.findViewById(R.id.tv_folder_list_music_counts);
			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvFolderListSubName.setText(folderListModels.get(position).getFolder());
		viewHolder.tvFolderListSubTitle.setText(folderListModels.get(position).getCount() + "");
		viewHolder.llFolderListSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainFragment.fragmentManager.beginTransaction()
						.replace(R.id.main_framelayout,
								new LocalMusicFragmentByFolderMusicFragment(folderListModels.get(position).getFolder()))
						.addToBackStack(null).commit();
			}
		});

		return convertView;

	}

	class ViewHolder {
		LinearLayout llFolderListSub;
		TextView tvFolderListSubName;
		TextView tvFolderListSubTitle;
	}

}
