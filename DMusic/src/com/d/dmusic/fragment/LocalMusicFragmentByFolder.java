package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.adapter.FolderListAdapter;
import com.d.dmusic.adapter.SingerListAdapter;
import com.d.dmusic.adapter.SongListAdapter;
import com.d.dmusic.adapter.models.FolderListModel;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.adapter.models.SingerListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.MyProgressBar;
import com.d.dmusic.fileutils.MusicFilterByPath;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ListView;

/*
 * ��������-�ļ���
 * 
 * */
public class LocalMusicFragmentByFolder extends Fragment implements OnClickListener {
	private View viewRoot;
	private ListView lvLocalMusicList;
	private FolderListAdapter folderListAdapter;

	public LocalMusicFragmentByFolder(FolderListAdapter folderListAdapter) {
		this.folderListAdapter = folderListAdapter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("��������:", "LocalMusicFragmentByFolder:onCreate");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("��������:", "LocalMusicFragmentByFolder:onResume");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("��������:", "LocalMusicFragmentByFolder:onCreateView");
		viewRoot = inflater.inflate(R.layout.fragment_local_folder, container, false);
		lvLocalMusicList = (ListView) viewRoot.findViewById(R.id.lv_local_folder_list);

		lvLocalMusicList.setAdapter(folderListAdapter);

		return viewRoot;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.v("��������:", "LocalMusicFragmentByFolder:onDestroyView");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("��������:", "LocalMusicFragmentByFolder:onDestroy");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		}
	}

}
