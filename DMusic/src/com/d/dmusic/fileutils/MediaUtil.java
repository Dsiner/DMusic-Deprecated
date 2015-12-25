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
				long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID)); // ����id
				String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))); // ���ֱ���
				String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));// ��ʾ����
				String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)); // ������
				long albumId = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));// ר��ID
				String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)); // ר��
				long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)); // ʱ��
				long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)); // �ļ���С
				String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)); // �ļ�·��
				int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)); // �Ƿ�Ϊ����
				if (isMusic != 0) { // ֻ��������ӵ����ϵ���
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
	 * ��ʽ��ʱ�䣬�Ѻ���ת���ɷ�:��(00:00)��ʽ
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
	 * ����byte�����ݴ�С��Ӧ���ı�
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