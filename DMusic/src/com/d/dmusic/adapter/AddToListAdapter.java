package com.d.dmusic.adapter;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.R;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.CustomMusicListModel;
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

/**
 * ��ӵ��б�Dialog������
 * 
 * @author D
 *
 */
public class AddToListAdapter extends BaseAdapter {
	private Context context;
	private List<CustomMusicListModel> customMusicListModels;
	private int lastSelectedPosition;// ���һ�α�ѡ�е�λ��
	private CheckedTextView lastCTVDialogAddToListIsselected;// ��һ�β�����CheckedTextView

	public AddToListAdapter(Context context, List<CustomMusicListModel> customMusicListModels) {
		this.context = context;
		this.customMusicListModels = customMusicListModels;
		this.lastSelectedPosition = -1;// ��ʼ��Ϊ-1��δѡ���κ�
	}

	/**
	 * ����ѡ�е�λ��
	 * 
	 * @return
	 */
	public int getLastSelectedPostion() {
		return lastSelectedPosition;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.dialog_add_to_list_sub, null);
			viewHoldertemp = new ViewHolder();
			viewHoldertemp.tvDialogAddToListListName = (TextView) convertView
					.findViewById(R.id.tv_dialog_add_to_list_sub_list_name);
			viewHoldertemp.ctvDialogAddToListIsselected = (CheckedTextView) convertView
					.findViewById(R.id.ctv_dialog_add_to_list_sub_isselected);
			convertView.setTag(viewHoldertemp);
		} else {
			viewHoldertemp = (ViewHolder) convertView.getTag();
		}
		final ViewHolder viewHolder = viewHoldertemp;
		viewHolder.tvDialogAddToListListName.setText(customMusicListModels.get(position).getListName());
		viewHolder.ctvDialogAddToListIsselected.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (lastSelectedPosition != position) {
					if (lastCTVDialogAddToListIsselected != null)
						lastCTVDialogAddToListIsselected.setChecked(false);
				}

				if (viewHolder.ctvDialogAddToListIsselected.isChecked()) {
					lastSelectedPosition = -1;// ��ָ���κ�λ��
					viewHolder.ctvDialogAddToListIsselected.setChecked(false);
					lastCTVDialogAddToListIsselected = null;// ��ָ���κζ���
				} else {
					lastSelectedPosition = position;// ָ��ǰλ��
					viewHolder.ctvDialogAddToListIsselected.setChecked(true);
					lastCTVDialogAddToListIsselected = viewHolder.ctvDialogAddToListIsselected;// ���õ�ǰ��������

				}

			}

		});

		return convertView;

	}

	class ViewHolder {
		TextView tvDialogAddToListListName;
		CheckedTextView ctvDialogAddToListIsselected;
	}

}
