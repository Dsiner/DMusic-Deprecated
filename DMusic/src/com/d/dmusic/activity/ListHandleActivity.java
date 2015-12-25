package com.d.dmusic.activity;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.dialog.MyDialogAddToList;
import com.d.dmusic.dialog.MyProgressBar;
import com.d.dmusic.dialog.MyProgressBarWithBackgroundBlur;
import com.d.dmusic.fragment.CustomMusicFragment;
import com.d.dmusic.global.Contains;
import com.d.dmusic.itemtouchhelper.RecyclerListAdapter;
import com.d.dmusic.itemtouchhelper.RecyclerListAdapter.OnRecyclerViewItemClickListener;
import com.d.dmusic.itemtouchhelper.RecyclerListAdapter.OnStartDragListener;
import com.d.dmusic.itemtouchhelper.SimpleItemTouchHelperCallback;
import com.d.dmusic.utils.ListHandler;

import android.app.Dialog;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * �����б��������
 * 
 * @author D
 *
 */
public class ListHandleActivity extends BaseFinalActivity
		implements OnStartDragListener, OnRecyclerViewItemClickListener, OnClickListener {

	private int id;// �����б�Table����
	private int index;// �б��ʶ

	private ImageView ivListHandleTitleBack;// ����
	private TextView tvListHandleTitleTitle;// ����
	private TextView ivListHandleTitleSelectAll;// ȫѡ
	private LinearLayout llListHandleAddToList;// ��ӵ��б�
	private LinearLayout llListHandleDelete;// ɾ��
	private LinearLayout llListHandleSubmit;// �ύ����
	private RecyclerView recyclerView;
	private ItemTouchHelper mItemTouchHelper;
	private LinearLayoutManager layoutManager;

	// ������
	private List<MusicListModel> musicListModels;
	private RecyclerListAdapter recyclerListAdapter;
	// �����б������
	private ListHandler listHandler;
	// ������ʾdialog
	private MyProgressBarWithBackgroundBlur myProgressBarWithBackgroundBlur;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				CustomMusicFragment.isNeedReload = true;// ����ʱ����Ҫ���¼�������Դ
				myProgressBarWithBackgroundBlur.dismiss();
				finish();
			}
			if (msg.what == 2) {
				myProgressBarWithBackgroundBlur.dismiss();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list_handle);
		id = getIntent().getIntExtra("id", -1);// ���ݿ���û��id����Ϊ-1������
		index = getIntent().getIntExtra("index", -1);// �õ��б��ʶ
		musicListModels = new ArrayList<MusicListModel>();
		if (Contains.musicListModels != null)
			musicListModels = Contains.musicListModels;// ָ��ȫ�־�̬����
		listHandler = new ListHandler(index, musicListModels);
		ivListHandleTitleBack = (ImageView) findViewById(R.id.iv_list_handle_title_back);
		tvListHandleTitleTitle = (TextView) findViewById(R.id.tv_list_handle_title_title);
		ivListHandleTitleSelectAll = (TextView) findViewById(R.id.tv_list_handle_title_select_all);
		llListHandleAddToList = (LinearLayout) findViewById(R.id.ll_list_handle_add_to_list);
		llListHandleDelete = (LinearLayout) findViewById(R.id.ll_list_handle_delete);
		llListHandleSubmit = (LinearLayout) findViewById(R.id.ll_list_handle_submit);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
		recyclerListAdapter = new RecyclerListAdapter(this, musicListModels, listHandler);
		recyclerListAdapter.setOnItemClickListener(this);
		recyclerView.setHasFixedSize(true);
		recyclerView.setAdapter(recyclerListAdapter);
		// �������Բ��ֹ�������Ĭ���Ǵ�ֱ����
		layoutManager = new LinearLayoutManager(this);
		// ΪRecyclerViewָ�����ֹ������
		recyclerView.setLayoutManager(layoutManager);

		ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerListAdapter);
		mItemTouchHelper = new ItemTouchHelper(callback);
		mItemTouchHelper.attachToRecyclerView(recyclerView);

		ivListHandleTitleBack.setOnClickListener(this);
		ivListHandleTitleSelectAll.setOnClickListener(this);
		ivListHandleTitleSelectAll.setTag(false);
		llListHandleAddToList.setOnClickListener(this);
		llListHandleDelete.setOnClickListener(this);
		llListHandleSubmit.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onStartDrag(ViewHolder viewHolder) {
		// TODO Auto-generated method stub
		mItemTouchHelper.startDrag(viewHolder);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Contains.musicListModels = null;// ָ���,�ȴ��ڴ��ͷ�
	}

	@Override
	public void onItemClick(View view, int position) {
		// TODO Auto-generated method stub
		// recyclerListAdapter.notifyDataSetChanged();
		// Log.v("click at :", position + "");
		// List<MusicListModel> newItems =
		// recyclerListAdapter.getmusicListModels();
		//
		// // if (newItems.get(position).isSelected()) {
		// // newItems.get(position).setSelected(false);
		// // } else {
		// // newItems.get(position).setSelected(true);
		// // }
		// StringBuffer sb = new StringBuffer("new sort:");
		//
		// for (MusicListModel mlm : newItems) {
		// sb.append("\n" + mlm.isSelected());
		// }
		// Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
		// recyclerListAdapter.notifyDataSetChanged();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.iv_list_handle_title_back:
			finish();
			break;
		case R.id.tv_list_handle_title_select_all:

			if ((Boolean) ivListHandleTitleSelectAll.getTag()) {
				for (MusicListModel musicListModel : musicListModels) {
					if (musicListModel.isSelected()) {
						musicListModel.setSelected(false);
					} else {
						musicListModel.setSelected(true);
					}
					recyclerListAdapter.notifyDataSetChanged();
				}
				ivListHandleTitleSelectAll.setTag(false);
				ivListHandleTitleSelectAll.setText("ȫѡ");

			} else {
				for (MusicListModel musicListModel : musicListModels) {
					musicListModel.setSelected(true);
					recyclerListAdapter.notifyDataSetChanged();
				}
				ivListHandleTitleSelectAll.setTag(true);
				ivListHandleTitleSelectAll.setText("��ѡ");

			}

			break;
		// ��ӵ��б�
		case R.id.ll_list_handle_add_to_list:
			List<MusicListModel> mlm = new ArrayList<MusicListModel>();
			for (MusicListModel musicListModel : musicListModels) {
				if (musicListModel.isSelected()) {
					mlm.add(musicListModel);
				}
			}
			new MyDialogAddToList(this, index, mlm).show();
			break;
		case R.id.ll_list_handle_delete:
			myProgressBarWithBackgroundBlur = new MyProgressBarWithBackgroundBlur(this);
			myProgressBarWithBackgroundBlur.show();
			for (int i = musicListModels.size(); i > 0; i--) {
				if (musicListModels.get(i - 1).isSelected()) {
					listHandler.preAddDeleteQueueId(musicListModels.get(i - 1).getId());
					musicListModels.remove(i - 1);
				}
			}
			recyclerListAdapter.notifyDataSetChanged();
			handler.sendEmptyMessage(2);
			break;
		case R.id.ll_list_handle_submit:
			myProgressBarWithBackgroundBlur = new MyProgressBarWithBackgroundBlur(this);
			myProgressBarWithBackgroundBlur.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					listHandler.submit();
					ContentValues valuesCustom = new ContentValues();
					valuesCustom.put("sortby", 3);
					DataSupport.update(CustomMusicListModel.class, valuesCustom, id);// ���µ�ǰ�����б�����ʽ
					CustomMusicFragment.sortBy = 3;// ���Զ��巽ʽ����
					handler.sendEmptyMessage(1);
				}
			}).start();

			break;
		}

	}

}
