package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;
import com.d.dmusic.fragment.CustomMusicFragment;
import com.d.dmusic.fragment.LocalMusicFragment;
import com.d.dmusic.fragment.MainFragment;
import com.d.dmusic.global.Contains;
import com.d.dmusic.service.MusicControlUtils;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
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
 * 首页-自定义歌曲列表适配器
 */
public class CustomListSwipeAdapter extends BaseSwipListAdapter {
	private Context context;
	private List<CustomMusicListModel> customMusicListModels;
	private FragmentManager fragmentManager;

	public CustomListSwipeAdapter(Context context, List<CustomMusicListModel> customMusicListModels,
			FragmentManager fragmentManager) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.customMusicListModels = customMusicListModels;
		this.fragmentManager = fragmentManager;
	}

	// 更新数据源
	public void refresh() {
		Log.v("excute:", "refresh()");
		customMusicListModels = MainFragment.getCustomMusicListsFromDataBase();
		notifyDataSetChanged();
	}

	// 置顶
	public void stick(int position) {
		int currentStickSeq = DataSupport.min(CustomMusicListModel.class, "seq", int.class);
		customMusicListModels.get(position).setSeq(--currentStickSeq);
		customMusicListModels.get(position).save();

		refresh();
	}

	// 删除
	public void delete(int position) {
		DataBaseUtils.deleteMusicListFromDataBaseByIndex(customMusicListModels.get(position).getPointerOfDBTable());// 清空此表
		customMusicListModels.get(position).delete();

		refresh();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return customMusicListModels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return customMusicListModels.get(position);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.list_sub_swipe, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llCustomListSubSwipe = (LinearLayout) convertView
					.findViewById(R.id.ll_custom_list_sub_swipe);
			viewHoldertemp.tvCustomListSubSwipeName = (TextView) convertView
					.findViewById(R.id.tv_custom_list_sub_swipe_name);
			viewHoldertemp.tvCustomListSubSwipeCounts = (TextView) convertView
					.findViewById(R.id.tv_custom_list_sub_swipe_counts);

			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}

		final ViewHolder viewHolder = viewHoldertemp;

		viewHolder.tvCustomListSubSwipeName.setText(customMusicListModels.get(position).getListName());
		viewHolder.tvCustomListSubSwipeCounts.setText(customMusicListModels.get(position).getSongCounts() + "首");
		viewHolder.llCustomListSubSwipe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentManager.beginTransaction()
						.replace(R.id.main_framelayout,
								new CustomMusicFragment(customMusicListModels.get(position).getId(),
										customMusicListModels.get(position).getPointerOfDBTable(),
										customMusicListModels.get(position).getListName(),
										customMusicListModels.get(position).getSortBy()))
						.addToBackStack(null).commit();
			}
		});
		return convertView;

	}

	class ViewHolder {
		LinearLayout llCustomListSubSwipe;
		TextView tvCustomListSubSwipeName;
		TextView tvCustomListSubSwipeCounts;

	}
}
