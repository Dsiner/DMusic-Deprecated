package com.d.dmusic.fileutils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class MediaUtil {
	public static List<MusicInfo> getMusicInfos(Context context) {

		Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
				null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

		if (cursor != null && cursor.moveToFirst()) {
			List<MusicInfo> musicInfos = new ArrayList<MusicInfo>();
			do {
				long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
				String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))); // 音乐标题
				String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));// 显示名称
				String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // 艺术家
				long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));// 专辑ID
				String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // 专辑
				long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); // 时长
				long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
				String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
				int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // 是否为音乐
				if (isMusic != 0) { // 只把音乐添加到集合当中
					MusicInfo MusicInfo = new MusicInfo();
					MusicInfo.setId(id);
					MusicInfo.setTitle(title);
					MusicInfo.setDisplayName(displayName);
					MusicInfo.setArtist(artist);
					MusicInfo.setAlbumId(albumId);
					MusicInfo.setAlbum(album);
					MusicInfo.setDuration(duration);
					MusicInfo.setSize(size);
					MusicInfo.setUrl(url);
					musicInfos.add(MusicInfo);
				}
			} while (cursor.moveToNext());

			return musicInfos;

		} else {
			return null;
		}

	}

	/**
	 * 格式化时间，把毫秒转换成分:秒(00:00)格式
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(int time) {
		String min = time / 1000 / 60 + "";
		String sec = time / 1000 % 60 + "";
		if (min.length() < 2) {
			min = "0" + min;
		}
		if (sec.length() < 2) {
			sec = "0" + sec;
		}

		return min + ":" + sec;
	}

	/**
	 * 返回byte的数据大小对应的文本
	 * 
	 * @param size
	 * @return
	 */
	public static String formatDataSize(long size) {
		DecimalFormat formater = new DecimalFormat("####.00");
		if (size < 1024) {
			return size + "bytes";
		} else if (size < 1024 * 1024) {
			float kbsize = size / 1024f;
			return formater.format(kbsize) + "KB";
		} else if (size < 1024 * 1024 * 1024) {
			float mbsize = size / 1024f / 1024f;
			return formater.format(mbsize) + "MB";
		} else if (size < 1024 * 1024 * 1024 * 1024) {
			float gbsize = size / 1024f / 1024f / 1024f;
			return formater.format(gbsize) + "GB";
		} else {
			return size + "";
		}
	}
}