package com.d.dmusic.fileutils;

/**
 * 
 * @author D
 *
 */
public class FileInfo {
	private String fileName;// �ļ���
	private String filePostfix;// �ļ���׺����
	private int fileStyle;// �ļ�����(1:dirĿ¼���ͣ�2:Ŀ������ļ�,3:����)
	private int musicCounts;// ·����Ŀ������ļ���
	private String fileAbsolutePath;// �ļ�����·��
	private boolean isEmptyDir;// �Ƿ�Ϊ��Ŀ¼
	private boolean isSelected;// �Ƿ�Ϊ��Ŀ¼

	public int getMusicCounts() {
		return musicCounts;
	}

	public void setMusicCounts(int musicCounts) {
		this.musicCounts = musicCounts;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isEmptyDir() {
		return isEmptyDir;
	}

	public void setEmptyDir(boolean isEmptyDir) {
		this.isEmptyDir = isEmptyDir;
	}

	public String getFilePostfix() {
		return filePostfix;
	}

	public void setFilePostfix(String filePostfix) {
		this.filePostfix = filePostfix;
	}

	public String getFileAbsolutePath() {
		return fileAbsolutePath;
	}

	public void setFileAbsolutePath(String fileAbsolutePath) {
		this.fileAbsolutePath = fileAbsolutePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getFileStyle() {
		return fileStyle;
	}

	public void setFileStyle(int fileStyle) {
		this.fileStyle = fileStyle;
	}

}
