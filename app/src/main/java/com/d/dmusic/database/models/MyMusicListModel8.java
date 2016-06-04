package com.d.dmusic.database.models;

import org.litepal.crud.DataSupport;

public class MyMusicListModel8 extends DataSupport {

	private int id;
	private String songName; // 歌曲名
	private String singer; // 歌手名
	private String album; // 专辑名
	private long duration; // 歌曲时长
	private long size; // 文件大小
	private String filePostfix; // 文件格式(后缀类型)
	private String url; // 文件路径
	private boolean isCollected; // 是否收藏

	private String lrcName; // 歌词名称
	private String lrcUrl; // 歌词路径

	private int customSeq;// 自定义序号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFilePostfix() {
		return filePostfix;
	}

	public void setFilePostfix(String filePostfix) {
		this.filePostfix = filePostfix;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isCollected() {
		return isCollected;
	}

	public void setCollected(boolean isCollected) {
		this.isCollected = isCollected;
	}

	public String getLrcName() {
		return lrcName;
	}

	public void setLrcName(String lrcName) {
		this.lrcName = lrcName;
	}

	public String getLrcUrl() {
		return lrcUrl;
	}

	public void setLrcUrl(String lrcUrl) {
		this.lrcUrl = lrcUrl;
	}

	public int getCustomSeq() {
		return customSeq;
	}

	public void setCustomSeq(int customSeq) {
		this.customSeq = customSeq;
	}

}
