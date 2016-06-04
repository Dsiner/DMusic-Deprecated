package com.d.dmusic.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.fileutils.MediaUtil;
import com.d.dmusic.models.MusicListModel;

/**
 * 歌曲信息对话框
 *
 * @author D
 */
public class SongInfoDialog {
    private Context context;
    private View dialogView;
    private Dialog dialog;
    private MusicListModel musicListModel;

    /**
     * @param context
     * @param musicListModel
     */
    public SongInfoDialog(Context context, MusicListModel musicListModel) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.musicListModel = musicListModel;
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
        dialog = new Dialog(context, R.style.MyDialogStyleProgress);
        initDialogView(musicListModel);
        dialog.setContentView(dialogView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
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
        TextView tvDialogSongInfoOk = (TextView) dialogView.findViewById(R.id.tv_dialog_song_ok);

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
        tvDialogSongInfoOk.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });

    }
}
