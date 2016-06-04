package com.d.dmusic.database;

import org.litepal.crud.DataSupport;

import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.database.models.CustomMusicListModel;

import android.content.ContentValues;

public class DataBaseUpdate {

	/**
	 * 更新CustomMusicListModel表，歌曲数目
	 * 
	 * @param id
	 * @param counts
	 */
	public static void updateCountsById(int id, int counts) {
		ContentValues values = new ContentValues();
		values.put("songcounts", counts);
		DataSupport.update(CustomMusicListModel.class, values, id);
	}

}
