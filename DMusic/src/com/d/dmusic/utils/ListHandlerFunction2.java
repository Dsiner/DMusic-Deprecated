package com.d.dmusic.utils;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.DataBaseInsert;
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
import com.d.dmusic.fileutils.MusicFilterByPath;

import android.R.integer;
import android.content.ContentValues;

/**
 * 歌曲列表-排序工具类-方法二
 * 
 * @author D
 *
 */
public class ListHandlerFunction2 {
	private int index;
	private List<MusicListModel> musicListModels;

	public ListHandlerFunction2(int index, List<MusicListModel> musicListModels) {

		this.index = index;
		this.musicListModels = musicListModels;

	}

	/**
	 * 提交
	 */
	public void submit() {
	}

	private void reorderByIndex(int index) {
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				DataSupport.deleteAll(MyMusicListModel0.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 1:
				DataSupport.deleteAll(MyMusicListModel1.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 2:
				DataSupport.deleteAll(MyMusicListModel2.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 3:
				DataSupport.deleteAll(MyMusicListModel3.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 4:
				DataSupport.deleteAll(MyMusicListModel4.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 5:
				DataSupport.deleteAll(MyMusicListModel5.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 6:
				DataSupport.deleteAll(MyMusicListModel6.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 7:
				DataSupport.deleteAll(MyMusicListModel7.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 8:
				DataSupport.deleteAll(MyMusicListModel8.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 9:
				DataSupport.deleteAll(MyMusicListModel9.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			}

			break;
		case 1:
			switch (index % 10) {
			case 0:
				DataSupport.deleteAll(MyMusicListModel10.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 1:
				DataSupport.deleteAll(MyMusicListModel11.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 2:
				DataSupport.deleteAll(MyMusicListModel12.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 3:
				DataSupport.deleteAll(MyMusicListModel13.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 4:
				DataSupport.deleteAll(MyMusicListModel14.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 5:
				DataSupport.deleteAll(MyMusicListModel15.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 6:
				DataSupport.deleteAll(MyMusicListModel16.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 7:
				DataSupport.deleteAll(MyMusicListModel17.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 8:
				DataSupport.deleteAll(MyMusicListModel18.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			case 9:
				DataSupport.deleteAll(MyMusicListModel19.class);
				DataBaseInsert.insertAllMusicListIntoDataBase(index, musicListModels);
				break;
			}
			break;
		}
	}
}
