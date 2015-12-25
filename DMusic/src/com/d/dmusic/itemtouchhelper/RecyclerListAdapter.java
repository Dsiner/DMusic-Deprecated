/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.d.dmusic.itemtouchhelper;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.utils.ListHandler;

/**
 * Simple RecyclerView.Adapter that implements {@link ItemTouchHelperAdapter} to
 * respond to move and dismiss events from a
 * {@link android.support.v7.widget.helper.ItemTouchHelper}.
 *
 * @author Paul Burke (ipaulpro)
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>
		implements ItemTouchHelperAdapter {

	private static List<MusicListModel> musicListModels;
	private static OnRecyclerViewItemClickListener mOnItemClickListener = null;
	private final OnStartDragListener mDragStartListener;

	// 歌曲列表管理类
	private ListHandler listHandler;

	public RecyclerListAdapter(OnStartDragListener dragStartListener, List<MusicListModel> musicListModels,
			ListHandler listHandler) {
		this.mDragStartListener = dragStartListener;
		this.musicListModels = musicListModels;
		this.listHandler = listHandler;
	}

	public List<MusicListModel> getmusicListModels() {
		return musicListModels;
	}

	private void refresh() {
		notifyDataSetChanged();
	}

	/**
	 * Listener for manual initiation of a drag.
	 */
	public interface OnStartDragListener {

		/**
		 * Called when a view is requesting a start of a drag.
		 *
		 * @param viewHolder
		 *            The holder of the view to drag.
		 */
		void onStartDrag(RecyclerView.ViewHolder viewHolder);
	}

	// define interface
	public static interface OnRecyclerViewItemClickListener {
		void onItemClick(View view, int position);
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_handle_sub, parent, false);
		ItemViewHolder itemViewHolder = new ItemViewHolder(view);
		return itemViewHolder;
	}

	@Override
	public void onBindViewHolder(final ItemViewHolder holder, int position) {
		holder.tvSongName.setText(musicListModels.get(position).getSongName());
		holder.tvSinger.setText(musicListModels.get(position).getSinger());
		// Start a drag whenever the handle view it touched
		if (musicListModels.get(position).isSelected()) {
			holder.ivIsSelected.setBackgroundResource(R.drawable.scan_dir_select_cover);
		} else {
			holder.ivIsSelected.setBackgroundResource(R.drawable.scan_dir_select);
		}
		holder.handleView.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
					mDragStartListener.onStartDrag(holder);
				}
				return false;
			}
		});
	}

	@Override
	public void onItemDismiss(int position) {
		listHandler.preAddDeleteQueueId(musicListModels.get(position).getId());// 添加到等待删除队列
		musicListModels.remove(position);
		notifyItemRemoved(position);
	}

	@Override
	public void onItemMove(int fromPosition, int toPosition) {
		Collections.swap(musicListModels, fromPosition, toPosition);
		notifyItemMoved(fromPosition, toPosition);
	}

	@Override
	public int getItemCount() {
		return musicListModels.size();
	}

	public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	/**
	 * Simple example of a view holder that implements
	 * {@link ItemTouchHelperViewHolder} and has a "handle" view that initiates
	 * a drag event when touched.
	 */
	public static class ItemViewHolder extends RecyclerView.ViewHolder
			implements ItemTouchHelperViewHolder, OnClickListener {

		public final ImageView ivIsSelected;
		public final TextView tvSongName;
		public final TextView tvSinger;
		public final ImageView handleView;

		public ItemViewHolder(View itemView) {
			super(itemView);
			ivIsSelected = (ImageView) itemView.findViewById(R.id.tv_list_handle_sub_select);
			tvSongName = (TextView) itemView.findViewById(R.id.tv_list_handle_sub_song_name);
			tvSinger = (TextView) itemView.findViewById(R.id.tv_list_handle_sub_singer);
			handleView = (ImageView) itemView.findViewById(R.id.iv_list_handle_sub_handle);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onItemSelected() {
			itemView.setBackgroundColor(Color.LTGRAY);
		}

		@Override
		public void onItemClear() {
			itemView.setBackgroundColor(0);
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int position = getAdapterPosition();// getPosition();
			if (musicListModels.get(position).isSelected()) {
				ivIsSelected.setBackgroundResource(R.drawable.scan_dir_select);
				musicListModels.get(position).setSelected(false);

			} else {
				ivIsSelected.setBackgroundResource(R.drawable.scan_dir_select_cover);
				musicListModels.get(position).setSelected(true);

			}
			if (mOnItemClickListener != null) {
				mOnItemClickListener.onItemClick(v, position);
			}

		}
	}

}
