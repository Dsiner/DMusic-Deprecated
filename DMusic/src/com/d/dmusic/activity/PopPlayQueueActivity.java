package com.d.dmusic.activity;

import com.d.dmusic.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 第一种方式：新开Activity-未使用
 * 
 * @author D
 *
 */
public class PopPlayQueueActivity extends Activity implements OnClickListener {

	private ImageView ivPlayQueuePlayMode;
	private TextView tvPlayQueuePlayMode;
	private TextView tvPlayQueueCounts;
	private TextView ivPlayQueueDeleteAll;
	private ListView lvPlayQueueList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_queue);
		ivPlayQueuePlayMode = (ImageView) findViewById(R.id.iv_play_queue_play_mode);
		tvPlayQueuePlayMode = (TextView) findViewById(R.id.tv_play_queue_play_mode);
		tvPlayQueueCounts = (TextView) findViewById(R.id.tv_play_queue_counts);
		ivPlayQueueDeleteAll = (TextView) findViewById(R.id.tv_play_queue_delete_all);
		lvPlayQueueList = (ListView) findViewById(R.id.lv_play_queue_list);
		lvPlayQueueList.setAdapter(new QueueAdapter());
		ivPlayQueuePlayMode.setOnClickListener(this);
		ivPlayQueueDeleteAll.setOnClickListener(this);
	}

	private void initDialogView() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
		View view = getWindow().getDecorView();
		WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();
		// 位于底部，填充父窗体(if needed so it completely fills its container)
		lp.gravity = Gravity.BOTTOM | Gravity.FILL_HORIZONTAL;
		// lp.x =
		// getResources().getDimensionPixelSize(R.dimen.playqueue_dialog_marginright);
		// lp.y =
		// getResources().getDimensionPixelSize(R.dimen.playqueue_dialog_marginbottom);
		// lp.width =
		// getResources().getDimensionPixelSize(R.dimen.playqueue_dialog_width);
		// lp.height =
		// getResources().getDimensionPixelSize(R.dimen.playqueue_dialog_height);

		getWindowManager().updateViewLayout(view, lp);
	}

	public class QueueAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 20;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHoldertemp = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(PopPlayQueueActivity.this).inflate(R.layout.play_queue_sub, null);
				viewHoldertemp = new ViewHolder();
				viewHoldertemp.llPlayQueueSub = (LinearLayout) convertView.findViewById(R.id.ll_play_queue_sub);
				viewHoldertemp.tvPlayQueueSubSongName = (TextView) convertView
						.findViewById(R.id.tv_play_queue_sub_songname);
				viewHoldertemp.tvPlayQueueSubSinger = (TextView) convertView
						.findViewById(R.id.tv_play_queue_sub_singer);
				viewHoldertemp.ivPlayQueueSubDelete = (ImageView) convertView
						.findViewById(R.id.iv_play_queue_sub_delete);
				convertView.setTag(viewHoldertemp);
			} else {
				viewHoldertemp = (ViewHolder) convertView.getTag();
			}
			final ViewHolder viewHolder = viewHoldertemp;
			viewHolder.tvPlayQueueSubSongName.setText("position:" + position);
			viewHolder.tvPlayQueueSubSinger.setText("position:" + position);
			viewHolder.ivPlayQueueSubDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(PopPlayQueueActivity.this, "hello:" + position, 1000).show();
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
