package com.d.dmusic.fileutils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/*
 * @author D
 */
public class FileUtils {
	private static String[] imageFormatSet = new String[] { ".ape", ".mp3", ".wav" };
	private String rootPath;
	private String currentPath;
	private int counts;

	private List<FileInfo> currentDirLists;
	private List<FileInfo> specDirMusicList;

	public FileUtils(String rootPath) {
		this.rootPath = rootPath;
		this.currentPath = rootPath.toString();
		// Log.v("内存卡路径rootDir：", rootPath);
		// Log.v("内存卡路径rootDir：", currentPath);
		// currentDirLists = new ArrayList<FileInfo>();
		// getCurrentDirLists(currentPath);
	}

	public List<FileInfo> getCurrentDirLists(String currentPath) {
		if (currentDirLists == null)
			currentDirLists = new ArrayList<FileInfo>();
		currentDirLists.clear();
		File files = new File(currentPath);
		if (!files.exists() || !files.isDirectory())
			return currentDirLists;// 如果当前选择目录不存在，直接返回空的currentDirLists
		File[] file = files.listFiles();

		for (File f : file) {
			if (f.isDirectory()) {
				FileInfo fileInfo = new FileInfo();
				String path = f.getAbsolutePath();
				fileInfo.setFileAbsolutePath(path);
				// Log.v("getAbsolutePath：", fileInfo.getFileAbsolutePath());

				fileInfo.setFileName(path.substring(path.lastIndexOf("/") + 1));
				// Log.v("getFileName：", fileInfo.getFileName());

				fileInfo.setFileStyle(1);// dir目录类型
				File[] flists = f.listFiles();
				if (flists != null) {
					if (0 == flists.length) {
						fileInfo.setEmptyDir(true);
					} else {
						fileInfo.setEmptyDir(false);
					}
				} else {
					fileInfo.setEmptyDir(true);
				}

				counts = 0;
				// getMusicCountsLoop(path); // 禁止放在主线程中，耗时
				fileInfo.setMusicCounts(counts);
				// Log.v("isEmptyDir：", "" + f.listFiles().length);
				currentDirLists.add(fileInfo);

			}
		}
		// Log.v("列表长度：", "" + currentDirLists.size());
		return currentDirLists;
	}

	public boolean isEndDir(String currentDirPath) {
		int count = 0;
		File files = new File(currentDirPath);
		File[] file = files.listFiles();
		if (file != null) {
			for (File f : file) {
				if (f.isDirectory()) {
					count++;
				}
			}
		}

		if (count == 0)
			return true;
		else
			return false;

	}

	public List<FileInfo> getMusicInfo(String[] paths) {
		if (specDirMusicList == null)
			specDirMusicList = new ArrayList<FileInfo>();
		specDirMusicList.clear();
		for (String path : paths) {
			getMusicFiles(path);
		}

		return specDirMusicList;
	}

	private void writeToSQL(String path) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileAbsolutePath(path);
		fileInfo.setFileName(path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")));
		fileInfo.setFilePostfix(path.substring(path.lastIndexOf(".") - 1));
		fileInfo.setFileStyle(2);// dir目录类型
		specDirMusicList.add(fileInfo);
	}

	private void getMusicFiles(String path) {
		File files = new File(path);
		File[] file = files.listFiles();

		for (File f : file) {
			if (f.isDirectory()) {
				getMusicFiles(f.getAbsolutePath());
			} else {
				for (String format : imageFormatSet) {
					if (path.endsWith(format)) {
						writeToSQL(f.getPath());
					}
				}

			}
		}
	}

	private synchronized void writeMusicName(String path) {
	}

	public int getMusicCounts(String path) {
		counts = 0;
		getMusicCountsLoop(path);
		return counts;
	}

	public void getMusicCountsLoop(String path) {

		File files = new File(path);
		File[] file = files.listFiles();

		for (File f : file) {
			String p = f.getAbsolutePath();
			if (f.isDirectory()) {
				getMusicCountsLoop(f.getAbsolutePath());
			} else {
				for (String format : imageFormatSet) {
					if (p.endsWith(format)) {
						counts++;
					}
				}

			}
		}

	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

}
