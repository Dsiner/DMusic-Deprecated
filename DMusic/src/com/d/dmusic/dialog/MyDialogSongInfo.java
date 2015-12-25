package com.d.dmusic.dialog;

import com.d.dmusic.R;
import com.d.dmusic.adapter.PlayQueueAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.fileutils.MediaUtil;
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

/**
 * 歌曲信息对话框
 * 
 * @author D
 *
 */
public class MyDialogSongInfo {
	private Context context;
	private DisplayMetrics dm;
	private View dialogView;
	private MyDialogProgress dialog;
	private MusicListModel musicListModel;

	/**
	 * 
	 * @param context
	 * @param musicListModel
	 */
	public MyDialogSongInfo(Context context, MusicListModel musicListModel) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.musicListModel = musicListModel;
		// this.dm = dm;

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
		dialog = new MyDialogProgress(context);
		// if (dialogView == null) {
		// initDialogView();
		// }
		initDialogView(musicListModel);
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

	private void initDialogView(MusicListModel musicListModel) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(context);
		dialogView = inflater.inflate(R.layout.dialog_song_info, null);
		TextView tvDialogSongInfoSongName = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_song_name);
		TextView tvDialogSongInfoSinger = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_singer);
		TextView tvDialogSongInfoAlbum = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_album);
		TextView tvDialogSongInfoDuration = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_duration);
		TextView tvDialogSongInfoFilePostfix = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_filepostfix);
		TextView tvDialogSongInfoSize = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_size);
		TextView tvDialogSongInfoUrl = (TextView) dialogView.findViewById(R.id.tv_dialog_song_info_url);
		Button btnDialogSongInfoOk = (Button) dialogView.findViewById(R.id.btn_dialog_song_ok);

		tvDialogSongInfoSongName.setText(musicListModel.getSongName());
		tvDialogSongInfoSinger.setText(musicListModel.getSinger());
		tvDialogSongInfoAlbum.setText(musicListModel.getAlbum());
		long duration = musicListModel.getDuration() / 1000;

		long minute = duration / 60;
		long second = duration % 60;
		String curTimeString = String.format("%02d:%02d", minute, second);

		tvDialogSongInfoDuration.setText(curTimeString);// 格式待转
		tvDialogSongInfoFilePostfix.setText(musicListModel.getFilePostfix());
		tvDialogSongInfoSize.setText(MediaUtil.formatDataSize(musicListModel.getSize()));// 格式待转
		tvDialogSongInfoUrl.setText(musicListModel.getUrl());
		btnDialogSongInfoOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});

	}
}
