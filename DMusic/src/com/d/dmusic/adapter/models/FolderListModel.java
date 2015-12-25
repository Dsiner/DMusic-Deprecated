package com.d.dmusic.adapter.models;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.database.models.LocalAllMusicListModel;

/**
 * 文件夹列表
 * 
 * @author D
 *
 */
public class FolderListModel {
	private int id;
	private String folder; // 路径
	private int count; // 歌曲数

	private boolean isSelected;// 额外属性：是否选中

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
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
