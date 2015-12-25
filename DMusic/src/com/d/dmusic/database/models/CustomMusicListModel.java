package com.d.dmusic.database.models;

import org.litepal.crud.DataSupport;

/**
 * ��ҳ-�ҵ������б�
 */
public class CustomMusicListModel extends DataSupport {
	private int id;
	private String listName; // �����б���
	private long songCounts; // ����������
	private int seq;// ������ʾ���
	private int pointerOfDBTable;// ָ�����ݿ���Ӧ��
	private int sortBy;// ����ʽ(1������������2����ʱ������3���Զ�������)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public long getSongCounts() {
		return songCounts;
	}

	public void setSongCounts(long songCounts) {
		this.songCounts = songCounts;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getPointerOfDBTable() {
		return pointerOfDBTable;
	}

	public void setPointerOfDBTable(int pointerOfDBTable) {
		this.pointerOfDBTable = pointerOfDBTable;
	}

	public int getSortBy() {
		return sortBy;
	}

	public void setSortBy(int sortBy) {
		this.sortBy = sortBy;
	}

}
