package com.d.dmusic.adapter.models;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.database.models.LocalAllMusicListModel;

/**
 * 专辑列表
 * 
 * @author D
 *
 */
public class AlbumListModel {
	private int id;
	private String album; // 专辑名
	private int count; // 歌曲数

	private boolean isSelected;// 额外属性：是否选中

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
