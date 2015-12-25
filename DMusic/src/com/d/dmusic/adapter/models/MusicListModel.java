package com.d.dmusic.adapter.models;

import org.litepal.crud.DataSupport;

/**
 * ͨ�������ṩ��&��ǰ�����б�
 * 
 * @author D
 *
 */
public class MusicListModel extends DataSupport {
	private int id;
	private String songName; // ������
	private String singer; // ������
	private String album; // ר����
	private long duration; // ����ʱ��
	private long size; // �ļ���С
	private String filePostfix; // �ļ���׺����
	private String url; // �ļ�·��
	private String folder;// ���ļ��о���·��
	private boolean isCollected; // �Ƿ��ղ�

	private String lrcName; // �������
	private String lrcUrl; // ���·��

	private boolean isSelected;// �������ԣ��Ƿ�ѡ��

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

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
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

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
