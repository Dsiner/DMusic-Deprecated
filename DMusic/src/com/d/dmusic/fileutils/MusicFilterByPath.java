package com.d.dmusic.fileutils;

import java.util.ArrayList;
import java.util.List;

import com.d.dmusic.adapter.models.MusicListModel;

import android.content.Context;
import android.util.Log;

public class MusicFilterByPath {
	private static String[] imageFormatSet = new String[] { ".ape", ".mp3", ".wav" };

	public static List<MusicListModel> getMusicListsModelByPath(Context context, String path) {
		MediaUtil mediaUtil = new MediaUtil();
		List<MusicInfo> musicInfos = mediaUtil.getMusicInfos(context);
		if (musicInfos == null)
			return null;
		List<MusicListModel> musicListModels = new ArrayList<MusicListModel>();
		for (MusicInfo musicInfo : musicInfos) {
			String str = musicInfo.getUrl().toString();
			Log.v("path", str);
			Log.v("path", path);
			if (str.length() > path.length()) {
				// 加"/"表明为路径
				if (str.substring(0, path.length() + 1).equals(path + "/")) {
					String displayName = musicInfo.getDisplayName().toString();
					// 文件格式过滤，MediaPlayer对其他格式支持不好（例如.wma）
					for (String format : imageFormatSet) {
						if (displayName.endsWith(format)) {
							MusicListModel musicListModel = new MusicListModel();
							musicListModel.setSongName(displayName.substring(0, displayName.lastIndexOf(".")));
							musicListModel.setSinger(musicInfo.getArtist());
							musicListModel.setAlbum(musicInfo.getAlbum());
							musicListModel.setDuration(musicInfo.getDuration());
							musicListModel.setSize(musicInfo.getSize());
							musicListModel.setFilePostfix(displayName.substring(displayName.lastIndexOf(".")));
							musicListModel.setUrl(musicInfo.getUrl());
							musicListModel
									.setFolder(musicInfo.getUrl().substring(0, musicInfo.getUrl().lastIndexOf("/")));
							musicListModels.add(musicListModel);
						}
					}

				}

			}
		}
		if (musicListModels.size() > 0) {
			return musicListModels;
		} else {
			return null;
		}

	}

}
