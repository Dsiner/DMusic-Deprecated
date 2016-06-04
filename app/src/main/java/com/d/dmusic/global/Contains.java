package com.d.dmusic.global;

import java.util.List;

import com.d.dmusic.models.MusicListModel;

public class Contains {
	public static final String PLAYER_RELOAD = "com.d.dmusic.action.player_control_reload";// 从歌曲列表点中歌曲播放
	public static final String PLAYER_CONTROL_PLAY_PAUSE = "com.d.dmusic.action.player_control_play_pause";// 播放、暂停
	public static final String PLAYER_CONTROL_PREV = "com.d.dmusic.action.player_control_prev";// 上一首
	public static final String PLAYER_CONTROL_NEXT = "com.d.dmusic.action.player_control_next";// 下一首
	public static final String MUSIC_CURRENT = "com.d.dmusic.action.music_current";// 用来接收service的广播
	public static final String MUSIC_UPDTE_LIST = "com.d.dmusic.action.music_update_list";// 用来接收service的广播,更新当前歌曲信息
	public static final String MUSIC_SEEK_TO_TIME = "com.d.dmusic.action.music_seek_to_time";// 用来发送SeekBar进度改变广播，播放时间跳转
	public static final int PLAY_PAUSE_FLAG = 0x1;
	public static final int NEXT_FLAG = 0x2;
	public static final int PRE_FLAG = 0x3;
	public static List<MusicListModel> musicListModels;// 用于addAll加载，并管理、排序音乐列表
	public static int playerMode;// 音乐播放器模式，0:普通模式，1：极简模式，2：通知栏模式

}
