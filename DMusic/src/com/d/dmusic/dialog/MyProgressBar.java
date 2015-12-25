package com.d.dmusic.dialog;

import com.d.dmusic.R;
import com.d.dmusic.adapter.PlayQueueAdapter;
import com.d.dmusic.service.MyService;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 自定义圆形Progress进度提示对话框
 * 
 * @author D
 *
 */
public class MyProgressBar {
	private Context context;
	private DisplayMetrics dm;
	private View dialogView;
	private MyDialogProgress dialog;

	/*
	 * 
	 * @param context
	 * 
	 * @param dm
	 */
	public MyProgressBar(Context context/* , DisplayMetrics dm */) {
		this.context = context;
		// this.dm = dm;
		initDialog();
	}

	public void show() {
		if (dialog != null)
			dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
		if (dialog != null) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}

	}

	private void initDialog() {
		dialog = new MyDialogProgress(context);
		// if (dialogView == null) {
		// initDialogView();
		// }
		initDialogView();
		dialog.setContentView(dialogView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);
		// Window dialogWindow = dialog.getWindow();
		// dialogWindow.setGravity(Gravity.CENTER);
		// WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
		// 获取对话框当前的参数值
		//
		// int windowsWidth = dm.widthPixels;
		// int windowsHeight = dm.heightPixels;
		// // deprecated方法
		// // WindowManager m = getWindowManager();
		// // Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		// // int windowsWidth = d.getWidth();
		// // int windowsHeight = d.getHeight();
		//
		// p.width = (int) (windowsWidth * 1); // 宽度设置为屏幕的1
		// p.height = (int) (windowsHeight * 0.618); // 高度设置为屏幕的0.618
		// dialogWindow.setAttributes(p);
	}

	private void initDialogView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		dialogView = inflater.inflate(R.layout.progress, null);

	}
}
