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
 * �Զ���Բ��Progress������ʾ�Ի���
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
		// ��ȡ�Ի���ǰ�Ĳ���ֵ
		//
		// int windowsWidth = dm.widthPixels;
		// int windowsHeight = dm.heightPixels;
		// // deprecated����
		// // WindowManager m = getWindowManager();
		// // Display d = m.getDefaultDisplay(); // ��ȡ��Ļ������
		// // int windowsWidth = d.getWidth();
		// // int windowsHeight = d.getHeight();
		//
		// p.width = (int) (windowsWidth * 1); // �������Ϊ��Ļ��1
		// p.height = (int) (windowsHeight * 0.618); // �߶�����Ϊ��Ļ��0.618
		// dialogWindow.setAttributes(p);
	}

	private void initDialogView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		dialogView = inflater.inflate(R.layout.progress, null);

	}
}
