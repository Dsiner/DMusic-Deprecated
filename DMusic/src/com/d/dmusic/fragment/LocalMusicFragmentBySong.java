package com.d.dmusic.fragment;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.adapter.SongListAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.dialog.MyProgressBar;
import com.d.dmusic.fileutils.MusicFilterByPath;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * 本地音乐-歌曲
 * 
 * */
public class LocalMusicFragmentBySong extends Fragment implements OnClickListener {

	private View viewRoot;
	private ListView lvLocalMusicList;
	private SongListAdapter songListAdapter;

	public LocalMusicFragmentBySong(SongListAdapter songListAdapter) {
		this.songListAdapter = songListAdapter;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v("生命周期:", "LocalMusicFragmentBySong:onCreate");

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.v("生命周期:", "LocalMusicFragmentBySong:onResume");
		// songListAdapter.notifyDataSetChanged();// 添加此代码，尝试以此修复bug

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("生命周期:", "LocalMusicFragmentBySong:onCreateView");
		viewRoot = inflater.inflate(R.layout.fragment_local_song, container, false);
		lvLocalMusicList = (ListView) viewRoot.findViewById(R.id.lv_local_music_list);

		lvLocalMusicList.setAdapter(songListAdapter);

		return viewRoot;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.v("生命周期:", "LocalMusicFragmentBySong:onDestroyView");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("生命周期:", "LocalMusicFragmentBySong:onDestroy");
	}

	// private void getMusicListFromDataBase() {
	// List<LocalAllMusicListModel> LocalAllMusicListModels =
	// DataSupport.findAll(LocalAllMusicListModel.class);
	// if (LocalAllMusicListModels != null) {
	//
	// for (LocalAllMusicListModel localAllMusicListModel :
	// LocalAllMusicListModels) {
	// MusicListModel musicListModel = new MusicListModel();
	// musicListModel.setSongName(localAllMusicListModel.getSongName());
	// musicListModel.setSinger(localAllMusicListModel.getSinger());
	// musicListModel.setAlbum(localAllMusicListModel.getAlbum());
	// musicListModel.setDuration(localAllMusicListModel.getDuration());
	// musicListModel.setSize(localAllMusicListModel.getSize());
	// musicListModel.setFilePostfix(localAllMusicListModel.getFilePostfix());
	// musicListModel.setUrl(localAllMusicListModel.getUrl());
	// // musicListModel.setCollected(false);
	// // musicListModel.setLrcName("");
	// // musicListModel.setLrcUrl("");
	// musicListModels.add(musicListModel);
	//
	// }
	// }
	//
	// // musicListModels =
	// // MusicFilterByPath.getMusicListsModelByPath(getActivity(),
	// // Environment.getExternalStorageDirectory().getAbsolutePath());
	// }
	//
	// private void insertMusicListIntoDataBase() {
	// DataSupport.deleteAll(LocalAllMusicListModel.class);//
	// 删除数据库LocalAllMusicListModel表中的所有记录
	// List<LocalAllMusicListModel> LocalAllMusicListModels = new
	// ArrayList<LocalAllMusicListModel>();
	// List<MusicListModel> musicListModels =
	// MusicFilterByPath.getMusicListsModelByPath(getActivity(),
	// Environment.getExternalStorageDirectory().getAbsolutePath());
	// if (musicListModels != null) {
	// for (MusicListModel musicListModel : musicListModels) {
	// LocalAllMusicListModel localAllMusicListModel = new
	// LocalAllMusicListModel();
	// localAllMusicListModel.setSongName(musicListModel.getSongName());
	// localAllMusicListModel.setSinger(musicListModel.getSinger());
	// localAllMusicListModel.setAlbum(musicListModel.getAlbum());
	// localAllMusicListModel.setDuration(musicListModel.getDuration());
	// localAllMusicListModel.setSize(musicListModel.getSize());
	// localAllMusicListModel.setFilePostfix(musicListModel.getFilePostfix());
	// localAllMusicListModel.setUrl(musicListModel.getUrl());
	// localAllMusicListModel.setFolder(musicListModel.getFolder());
	// localAllMusicListModel.setCollected(false);
	// localAllMusicListModel.setLrcName("");
	// localAllMusicListModel.setLrcUrl("");
	// LocalAllMusicListModels.add(localAllMusicListModel);
	// }
	// DataSupport.saveAll(LocalAllMusicListModels);//
	// 扫描到的列表重新插入表LocalAllMusicListModel中
	// }
	//
	// }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		}
	}

}
