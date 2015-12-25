package com.d.dmusic.dialog;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.adapter.AddToListAdapter;
import com.d.dmusic.adapter.PlayQueueAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseInsert;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.service.MyService;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ��ӵ��б�Ի���
 * 
 * @author D
 *
 */
public class MyDialogAddToList {
	private Context context;
	private int currentIndex;// �б��ʶ
	private DisplayMetrics dm;
	private View dialogView;
	private AddToListAdapter addToListAdapter;// listview������
	private ListView lvDialogAddToList;// listview
	private ImageView ivQuit;
	private TextView tvOK;// ȷ����ť
	private MyDialog dialog;
	private List<MusicListModel> musicListModels;// �������������
	private List<CustomMusicListModel> customMusicListModels;// ����ǰ�б�����Զ����б����

	/**
	 * 
	 * @param context
	 * @param musicListModels
	 */
	public MyDialogAddToList(Context context, int index, List<MusicListModel> musicListModels) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.currentIndex = index;
		this.musicListModels = musicListModels;
		// this.dm = dm;
		customMusicListModels = DataSupport.select("listName", "pointerOfDBTable")
				.where("pointerOfDBTable!=?", String.valueOf(currentIndex)).order("seq asc")
				.find(CustomMusicListModel.class);
		initDialog();
	}

	public void show() {
		if (dialog != null)
			dialog.show();
	}

	public void dismiss() {
		if (dialog != null) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}

	}

	private void initDialog() {
		dialog = new MyDialog(context);
		initDialogView();

		dialog.setContentView(dialogView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);

		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ

		DisplayMetrics dm = new DisplayMetrics();
		// static����
		MainActivity.windowManager.getDefaultDisplay().getMetrics(dm);
		int windowsWidth = dm.widthPixels;
		int windowsHeight = dm.heightPixels;

		p.width = (int) (windowsWidth * 1); // �������Ϊ��Ļ��1
		p.height = (int) (windowsHeight * 0.382); // �߶�����Ϊ��Ļ��0.382
		dialogWindow.setAttributes(p);

	}

	/**
	 * ��ʼ��Dialog��ͼ
	 * 
	 * @param musicListModels
	 */
	private void initDialogView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		dialogView = inflater.inflate(R.layout.dialog_add_to_list, null);
		lvDialogAddToList = (ListView) dialogView.findViewById(R.id.lv_dialog_add_to_list_list);
		ivQuit = (ImageView) dialogView.findViewById(R.id.iv_dialog_add_to_list_quit);
		tvOK = (TextView) dialogView.findViewById(R.id.tv_dialog_add_to_list_ok);
		addToListAdapter = new AddToListAdapter(context, customMusicListModels);
		lvDialogAddToList.setAdapter(addToListAdapter);
		ivQuit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();// ȡ���Ի���

			}
		});
		tvOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int position = addToListAdapter.getLastSelectedPostion();
				if (position != -1) {

					int index = customMusicListModels.get(position).getPointerOfDBTable();// �õ�����������б�ı�ָ��
					// �����б�
					if (DataBaseInsert.insertMusicListIntoDataBase(index, musicListModels)) {
						dismiss();
					} else {
						Toast.makeText(context, "�����Ѵ��ڣ�", Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(context, "δѡ���κ��б�", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}
}
