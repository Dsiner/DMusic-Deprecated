package com.d.dmusic.global;

import java.util.List;

import com.d.dmusic.adapter.models.MusicListModel;

public class Contains {
	public static final String PLAYER_RELOAD = "com.d.dmusic.action.player_control_reload";// �Ӹ����б���и�������
	public static final String PLAYER_CONTROL_PLAY_PAUSE = "com.d.dmusic.action.player_control_play_pause";// ���š���ͣ
	public static final String PLAYER_CONTROL_PREV = "com.d.dmusic.action.player_control_prev";// ��һ��
	public static final String PLAYER_CONTROL_NEXT = "com.d.dmusic.action.player_control_next";// ��һ��
	public static final String MUSIC_CURRENT = "com.d.dmusic.action.music_current";// ��������service�Ĺ㲥
	public static final String MUSIC_UPDTE_LIST = "com.d.dmusic.action.music_update_list";// ��������service�Ĺ㲥,���µ�ǰ������Ϣ
	public static final String MUSIC_SEEK_TO_TIME = "com.d.dmusic.action.music_seek_to_time";// ��������SeekBar���ȸı�㲥������ʱ����ת
	public static final int PLAY_PAUSE_FLAG = 0x1;
	public static final int NEXT_FLAG = 0x2;
	public static final int PRE_FLAG = 0x3;
	public static List<MusicListModel> musicListModels;// ����addAll���أ����������������б�
	public static int playerMode;// ���ֲ�����ģʽ��0:��ͨģʽ��1������ģʽ��2��֪ͨ��ģʽ

}
