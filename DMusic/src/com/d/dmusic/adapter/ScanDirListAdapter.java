package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;

import android.content.Context;
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

public class ScanDirListAdapter extends BaseAdapter {
	private Context context;
	private TextView tvCurrentDir;
	private List<FileInfo> currentDirLists;
	private FileUtils fileUtils;
	private String rootPath;
	private String currentPath;
	// private List<String> pathQueue;

	public ScanDirListAdapter(Context context, String rootPath, TextView tvCurrentDir) {
		this.fileUtils = new FileUtils(rootPath);
		// this.currentDirLists = new ArrayList<FileInfo>();
		this.context = context;
		this.rootPath = rootPath;
		this.currentPath = rootPath;
		this.tvCurrentDir = tvCurrentDir;
		this.tvCurrentDir.setText(rootPath);
		this.currentDirLists = fileUtils.getCurrentDirLists(rootPath);

		// this.pathQueue = pathQueue;
	}

	public List<FileInfo> getCurrentDirLists() {
		return currentDirLists;
	}

	public boolean ReturnParentDir() {
		// TODO Auto-generated method stub
		Log.v("return--开始路径", currentPath);
		if (currentPath.length() > rootPath.length()) {
			currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
			currentDirLists = fileUtils.getCurrentDirLists(currentPath);
			tvCurrentDir.setText(currentPath);
			Log.v("return--返回路径", currentPath);
			notifyDataSetChanged();
			return true;
		}
		return false;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return currentDirLists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return currentDirLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		/**
		 * 方法一
		 */
		/********************* 方法1 *******************/
		ViewHolder viewHoldertemp = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_scan_dir_sub, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.llDirSub = (LinearLayout) convertView.findViewById(R.id.ll_dir_sub);
			viewHoldertemp.tvDirName = (TextView) convertView.findViewById(R.id.tv_dir_name);
			viewHoldertemp.tvMusicCounts = (TextView) convertView.findViewById(R.id.tv_music_counts);
			viewHoldertemp.ctvIsSelected = (CheckedTextView) convertView.findViewById(R.id.ctv_isselected);
			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvDirName.setText(currentDirLists.get(position).getFileName());
		viewHolder.tvMusicCounts
				.setText(/* currentDirLists.get(position).getMusicCounts() */ "?" + "首");
		viewHolder.ctvIsSelected.setChecked(currentDirLists.get(position).isSelected());
		viewHolder.llDirSub.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Log.v("onClick--开始路径", currentPath);
				String str = currentDirLists.get(position).getFileAbsolutePath().toString();
				if (!fileUtils.isEndDir(str)) {
					currentPath = str.toString();
					// pathQueue.add(currentPath);
					currentDirLists = fileUtils.getCurrentDirLists(currentPath);

					tvCurrentDir.setText(currentPath);
					notifyDataSetChanged();
				} else {
					if (viewHolder.ctvIsSelected.isChecked()) {
						viewHolder.ctvIsSelected.setChecked(false);
						currentDirLists.get(position).setSelected(false);
					} else {
						viewHolder.ctvIsSelected.setChecked(true);
						currentDirLists.get(position).setSelected(true);
					}
				}
				// Log.v("onClick--结束路径", currentPath);

			}
		});
		viewHolder.ctvIsSelected.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (viewHolder.ctvIsSelected.isChecked()) {
					viewHolder.ctvIsSelected.setChecked(false);
					currentDirLists.get(position).setSelected(false);
				} else {
					viewHolder.ctvIsSelected.setChecked(true);
					currentDirLists.get(position).setSelected(true);
				}
			}
		});
		/********************* 方法1 *******************/

		/**
		 * 方法二
		 */
		/********************* 方法2-1 *******************/
		// ViewHolder viewHolder = null;
		// if (convertView == null) {
		// convertView =
		// LayoutInflater.from(context).inflate(R.layout.activity_scan_dir_sub,
		// null);
		// viewHolder = new ViewHolder();
		// viewHolder.llDirSub = (LinearLayout)
		// convertView.findViewById(R.id.ll_dir_sub);
		// viewHolder.tvDirName = (TextView)
		// convertView.findViewById(R.id.tv_dir_name);
		// viewHolder.tvMusicCounts = (TextView)
		// convertView.findViewById(R.id.tv_music_counts);
		// viewHolder.ctvIsSelected = (CheckedTextView)
		// convertView.findViewById(R.id.ctv_isselected);
		// convertView.setTag(viewHolder);
		// } else {
		// viewHolder = (ViewHolder) convertView.getTag();
		// }
		// viewHolder.tvDirName.setText(currentDirLists.get(position).getFileName());
		// viewHolder.tvMusicCounts.setText(currentDirLists.get(position).getMusicCounts()
		// + "首");
		// viewHolder.ctvIsSelected.setChecked(currentDirLists.get(position).isSelected());
		// viewHolder.llDirSub.setOnClickListener(new
		// MyLLSubOnclickListener(viewHolder, position));
		// viewHolder.ctvIsSelected.setOnClickListener(new
		// MyOnclickListener(viewHolder, position));
		/********************* 方法2-1 *******************/

		return convertView;

	}

	/********************* 方法2-2 *******************/
	// class MyLLSubOnclickListener implements OnClickListener {
	// private ViewHolder viewHolder;
	// private int position;
	//
	// public MyLLSubOnclickListener(ViewHolder viewHolder, int position) {
	// this.viewHolder = viewHolder;
	// this.position = position;
	// }
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// String str =
	// currentDirLists.get(position).getFileAbsolutePath().toString();
	// if (!fileUtils.isEndDir(str)) {
	// currentPath = str.toString();
	// currentDirLists = fileUtils.getCurrentDirLists(currentPath);
	//
	// tvCurrentDir.setText(currentPath);
	// notifyDataSetChanged();
	// }
	//
	// }
	// }
	//
	// class MyOnclickListener implements OnClickListener {
	//
	// private ViewHolder viewHolder;
	// private int position;
	//
	// public MyOnclickListener(ViewHolder viewHolder, int position) {
	// this.viewHolder = viewHolder;
	// this.position = position;
	// }
	//
	// @Override
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// if (viewHolder.ctvIsSelected.isChecked()) {
	// viewHolder.ctvIsSelected.setChecked(false);
	// currentDirLists.get(position).setSelected(false);
	// } else {
	// viewHolder.ctvIsSelected.setChecked(true);
	// currentDirLists.get(position).setSelected(true);
	// }
	// }
	// }

	/********************* 方法2-2 *******************/

	class ViewHolder {
		LinearLayout llDirSub;
		TextView tvDirName;
		TextView tvMusicCounts;
		CheckedTextView ctvIsSelected;
	}

}
