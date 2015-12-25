package com.d.dmusic.fileutils;

/**
 * 
 * @author D
 *
 */
public class FileInfo {
	private String fileName;// 文件名
	private String filePostfix;// 文件后缀类型
	private int fileStyle;// 文件类型(1:dir目录类型，2:目标过滤文件,3:其他)
	private int musicCounts;// 路径下目标过滤文件数
	private String fileAbsolutePath;// 文件绝对路径
	private boolean isEmptyDir;// 是否为空目录
	private boolean isSelected;// 是否为空目录

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
