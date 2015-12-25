package com.d.dmusic.database;

import org.litepal.crud.DataSupport;

import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.database.models.MyMusicListModel0;
import com.d.dmusic.database.models.MyMusicListModel1;
import com.d.dmusic.database.models.MyMusicListModel10;
import com.d.dmusic.database.models.MyMusicListModel11;
import com.d.dmusic.database.models.MyMusicListModel12;
import com.d.dmusic.database.models.MyMusicListModel13;
import com.d.dmusic.database.models.MyMusicListModel14;
import com.d.dmusic.database.models.MyMusicListModel15;
import com.d.dmusic.database.models.MyMusicListModel16;
import com.d.dmusic.database.models.MyMusicListModel17;
import com.d.dmusic.database.models.MyMusicListModel18;
import com.d.dmusic.database.models.MyMusicListModel19;
import com.d.dmusic.database.models.MyMusicListModel2;
import com.d.dmusic.database.models.MyMusicListModel3;
import com.d.dmusic.database.models.MyMusicListModel4;
import com.d.dmusic.database.models.MyMusicListModel5;
import com.d.dmusic.database.models.MyMusicListModel6;
import com.d.dmusic.database.models.MyMusicListModel7;
import com.d.dmusic.database.models.MyMusicListModel8;
import com.d.dmusic.database.models.MyMusicListModel9;

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
