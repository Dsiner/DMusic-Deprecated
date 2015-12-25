package com.d.dmusic.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.litepal.crud.DataSupport;

import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.application.SysApplication;
import com.d.dmusic.global.Contains;
import com.d.dmusic.global.MusicPlayListsInfo;

import android.R.bool;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

/**
 * ���ֿ���
 * 
 * @author D
 *
 */
public class MusicControlUtils {

	private static MusicControlUtils instanceMusicControlUtils;
	private boolean isLoaded;// �Ƿ��Ѽ��ز����б�
	public MediaPlayer mediaPlayer;
	private List<MusicListModel> musicListModels;// �����б�
	private int currentLocation;
	private int counts;
	private Context context;// ���ڷ��͹㲥
	private String currentSongName;// ��ǰ����������
	private String currentSinger;// ��ǰ�������ָ���
	public static int playMode;// ��ǰ�б���ģʽ

	public boolean isLoaded() {
		return isLoaded;
	}

	public void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	public int getPlayMode() {
		return playMode;
	}

	public void setPlayMode(int playMode) {
		this.playMode = playMode;
	}

	public int getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(int currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getCurrentSongName() {
		return currentSongName;
	}

	public void setCurrentSongName(String currentSongName) {
		this.currentSongName = currentSongName;
	}

	public String getCurrentSinger() {
		return currentSinger;
	}

	public void setCurrentSinger(String currentSinger) {
		this.currentSinger = currentSinger;
	}

	public List<MusicListModel> getMusicListModels() {
		return musicListModels;
	}

	public void setMusicListModels(List<MusicListModel> musicListModels) {
		this.musicListModels = musicListModels;
	}

	private MusicControlUtils(Context context) {
		// currentLocation = 0;
		// counts = 0;
		this.context = context;
		this.currentSongName = "";
		this.currentSinger = "";
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					autoPrepareNext();// �Զ���һ��
				}
			});
		}

	}

	/*
	 * Ϊ�˱�֤context��ͳһ�ͳ־û���ͨ��MyService.getInstanceMusicControlUtils()��ȡ������
	 * contextΪMyService.getBaseContext()
	 */
	public synchronized static MusicControlUtils getInstance(Context context) {
		if (null == instanceMusicControlUtils) {

			instanceMusicControlUtils = new MusicControlUtils(context);
		}

		return instanceMusicControlUtils;
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	// ��null�жϣ�����ǰ�ж�musicListModels�Ƿ�Ϊnull
	public void initList(List<MusicListModel> musicListModels, int location) {

		this.counts = musicListModels.size();
		this.musicListModels = musicListModels;
		currentLocation = location;
		isLoaded = true;// �����б��Ѽ���
		play();

	}

	/*
	 * ���Ĳ���ģʽ
	 */
	public void changePlayMode(int playMode) {
		this.playMode = playMode;
	}

	public void seekTo(int seekToIntMilliSecond) {
		if (isLoaded) {
			int duration = mediaPlayer.getDuration();// ����
			if (seekToIntMilliSecond >= 0 && seekToIntMilliSecond <= duration)
				mediaPlayer.seekTo(seekToIntMilliSecond);
		}

	}

	public void playByPosition(int position) {
		if (isLoaded) {
			if (position >= 0 && position < counts) {
				currentLocation = position;
				play();
			}

		}
	}

	public void DelelteAll() {
		stop();
		isLoaded = false;
		DataSupport.deleteAll(MusicListModel.class);
		musicListModels.clear();
	}

	public synchronized void DelelteByPosition(int position) {
		if (isLoaded) {
			if (position >= 0 && position < counts) {
				counts--;
				if (position > currentLocation) {
					musicListModels.get(position).delete();
					musicListModels.remove(position);
				} else if (position == currentLocation) {
					if (position == counts) {
						stop();
						musicListModels.get(position).delete();
						musicListModels.remove(position);
						currentLocation = 0;
						if (counts > 0)
							play();
					} else {
						stop();
						musicListModels.get(position).delete();
						musicListModels.remove(position);
						play();
					}

				} else if (position < currentLocation) {
					musicListModels.get(position).delete();
					musicListModels.remove(position);
					currentLocation--;
				}

			}
			if (counts == 0) {
				isLoaded = false;
			}

		}
	}

	/*
	 * �Զ��л���һ��
	 */
	public void autoPrepareNext() {
		switch (playMode) {
		// �б�ѭ��
		case 0:
			if (currentLocation < counts - 1) {
				currentLocation++;
			} else {
				currentLocation = 0;
			}
			play();
			break;
		// ˳�򲥷�
		case 1:
			if (currentLocation < counts - 1) {
				currentLocation++;
				play();
			} else {
				stop();
			}
			break;
		// �������
		case 2:
			Random random = new Random(System.currentTimeMillis());
			currentLocation = Math.abs(random.nextInt()) % counts;
			play();
			break;
		// ����ѭ��
		case 3:
			play();
			break;
		default:
			break;
		}

	}

	public void next() {
		if (isLoaded) {
			switch (playMode) {
			// �б�ѭ����˳�򲥷š�����ѭ��
			case 0:
			case 1:
			case 3:
				if (currentLocation < counts - 1) {
					currentLocation++;
				} else {
					currentLocation = 0;
				}
				break;
			// �������
			case 2:
				Random random = new Random(System.currentTimeMillis());
				currentLocation = Math.abs(random.nextInt()) % counts;
				break;

			default:
				break;
			}

			play();

		}
	}

	public void prev() {
		if (isLoaded) {

			switch (playMode) {
			// �б�ѭ����˳�򲥷š�����ѭ��
			case 0:
			case 1:
			case 3:
				if (currentLocation > 0) {
					currentLocation--;
				} else {
					currentLocation = counts - 1;
				}
				break;
			// �������
			case 2:
				Random random = new Random(System.currentTimeMillis());
				currentLocation = Math.abs(random.nextInt()) % counts;
				break;

			default:
				break;
			}

			play();
		}
	}

	public void play() {

		if (isLoaded) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
			mediaPlayer.reset();// ����
			// �㲥֪ͨ
			currentSongName = musicListModels.get(currentLocation).getSongName();
			currentSinger = musicListModels.get(currentLocation).getSinger();
			Intent intentUpdateList = new Intent(Contains.MUSIC_UPDTE_LIST);
			intentUpdateList.putExtra("songName", currentSongName);
			intentUpdateList.putExtra("singer", currentSinger);

			context.sendBroadcast(intentUpdateList);
			if (musicListModels != null) {
				try {
					mediaPlayer.setDataSource(musicListModels.get(currentLocation).getUrl());// ָ��Ҫ���ŵ���Ƶ�ļ�
					mediaPlayer.prepare();// Ԥ������Ƶ�ļ�
					mediaPlayer.start();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void playOrPause() {
		if (isLoaded) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			} else {
				mediaPlayer.start();
			}
		}
	}

	public void stop() {
		if (isLoaded) {
			if (mediaPlayer.isPlaying()) {
				mediaPlayer.stop();
			}
		}
	}

	public void onDestroy() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}
	}

}