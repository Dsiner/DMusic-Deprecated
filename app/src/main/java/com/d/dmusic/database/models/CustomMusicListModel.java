package com.d.dmusic.database.models;

import org.litepal.crud.DataSupport;

/**
 * 首页-我的音乐列表
 */
public class CustomMusicListModel extends DataSupport {
	private int id;
	private String listName; // 歌曲列表名
	private long songCounts; // 包含歌曲数
	private int seq;// 排序显示序号
	private int pointerOfDBTable;// 指向数据库相应表
	private int sortBy;// 排序方式(1：按名称排序，2：按时间排序，3：自定义排序)

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
