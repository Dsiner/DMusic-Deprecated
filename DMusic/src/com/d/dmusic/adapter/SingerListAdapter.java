package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.adapter.models.SingerListModel;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;
import com.d.dmusic.fragment.LocalMusicFragmentByAlbumMusicFragment;
import com.d.dmusic.fragment.LocalMusicFragmentBySingerMusicFragment;
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

public class SingerListAdapter extends BaseAdapter {
	private Context context;
	private List<SingerListModel> singerListModels;

	public SingerListAdapter(Context context, List<SingerListModel> singerListModels) {
		this.context = context;
		this.singerListModels = singerListModels;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return singerListModels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return singerListModels.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_singer, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llSingerListSub = (LinearLayout) convertView.findViewById(R.id.ll_singer_list_sub);
			viewHoldertemp.tvSingerListSubName = (TextView) convertView.findViewById(R.id.tv_singer_list_sub_name);
			viewHoldertemp.tvSingerListSubTitle = (TextView) convertView.findViewById(R.id.tv_singer_list_sub_title);
			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvSingerListSubName.setText(singerListModels.get(position).getSinger());
		viewHolder.tvSingerListSubTitle.setText(singerListModels.get(position).getCount() + "");
		viewHolder.llSingerListSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainFragment.fragmentManager.beginTransaction()
						.replace(R.id.main_framelayout,
								new LocalMusicFragmentBySingerMusicFragment(singerListModels.get(position).getSinger()))
						.addToBackStack(null).commit();
			}
		});

		return convertView;

	}

	class ViewHolder {
		LinearLayout llSingerListSub;
		TextView tvSingerListSubName;
		TextView tvSingerListSubTitle;
	}

}
