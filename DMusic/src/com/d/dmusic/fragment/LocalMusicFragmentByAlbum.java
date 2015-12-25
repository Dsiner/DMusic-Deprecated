package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.R;
import com.d.dmusic.adapter.AlbumListAdapter;
import com.d.dmusic.adapter.SingerListAdapter;
import com.d.dmusic.adapter.SongListAdapter;
import com.d.dmusic.adapter.models.AlbumListModel;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.adapter.models.SingerListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.MyProgressBar;
import com.d.dmusic.fileutils.MediaUtil;
import com.d.dmusic.fileutils.MusicFilterByPath;
import com.d.dmusic.global.Contains;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
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
import android.widget.ListView;

/*
 * 本地音乐-专辑
 * 
 * */
public class LocalMusicFragmentByAlbum extends Fragment implements OnClickListener {
	private View viewRoot;
	private ListView lvLocalMusicList;
	private AlbumListAdapter albumListAdapter;

	public LocalMusicFragmentByAlbum(AlbumListAdapter albumListAdapter) {
		this.albumListAdapter = albumListAdapter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("生命周期:", "LocalMusicFragmentByAlbum:onCreate");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("生命周期:", "LocalMusicFragmentByAlbum:onResume");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("生命周期:", "LocalMusicFragmentByAlbum:onCreateView");

		viewRoot = inflater.inflate(R.layout.fragment_local_album, container, false);
		lvLocalMusicList = (ListView) viewRoot.findViewById(R.id.lv_local_album_list);

		lvLocalMusicList.setAdapter(albumListAdapter);

		return viewRoot;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.v("生命周期:", "LocalMusicFragmentByAlbum:onDestroyView");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("生命周期:", "LocalMusicFragmentByAlbum:onDestroy");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		}
	}

}
