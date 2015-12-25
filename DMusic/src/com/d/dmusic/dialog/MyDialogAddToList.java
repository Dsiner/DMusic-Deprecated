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
 * 添加到列表对话框
 * 
 * @author D
 *
 */
public class MyDialogAddToList {
	private Context context;
	private int currentIndex;// 列表标识
	private DisplayMetrics dm;
	private View dialogView;
	private AddToListAdapter addToListAdapter;// listview适配器
	private ListView lvDialogAddToList;// listview
	private ImageView ivQuit;
	private TextView tvOK;// 确定按钮
	private MyDialog dialog;
	private List<MusicListModel> musicListModels;// 待插入歌曲队列
	private List<CustomMusicListModel> customMusicListModels;// 除当前列表外的自定义列表队列

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
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

		DisplayMetrics dm = new DisplayMetrics();
		// static常量
		MainActivity.windowManager.getDefaultDisplay().getMetrics(dm);
		int windowsWidth = dm.widthPixels;
		int windowsHeight = dm.heightPixels;

		p.width = (int) (windowsWidth * 1); // 宽度设置为屏幕的1
		p.height = (int) (windowsHeight * 0.382); // 高度设置为屏幕的0.382
		dialogWindow.setAttributes(p);

	}

	/**
	 * 初始化Dialog视图
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
				dismiss();// 取消对话框

			}
		});
		tvOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int position = addToListAdapter.getLastSelectedPostion();
				if (position != -1) {

					int index = customMusicListModels.get(position).getPointerOfDBTable();// 拿到待插入歌曲列表的表指针
					// 插入列表
					if (DataBaseInsert.insertMusicListIntoDataBase(index, musicListModels)) {
						dismiss();
					} else {
						Toast.makeText(context, "歌曲已存在！", Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(context, "未选则任何列表！", Toast.LENGTH_SHORT).show();
				}

			}
		});

	}
}
