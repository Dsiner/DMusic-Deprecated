package com.d.dmusic.adapter.models;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.database.models.LocalAllMusicListModel;

/**
 * �����б�
 * 
 * @author D
 *
 */
public class SingerListModel {
	private int id;
	private String singer; // ������
	private int count; // ������

	private boolean isSelected;// �������ԣ��Ƿ�ѡ��

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
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
