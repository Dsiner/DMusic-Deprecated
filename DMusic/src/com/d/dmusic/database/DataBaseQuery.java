package com.d.dmusic.database;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.MyMusicListModel0;
import com.d.dmusic.database.models.MyMusicListModel1;
import com.d.dmusic.database.models.MyMusicListModel10;
import com.d.dmusic.database.models.MyMusicListModel11;
import com.d.dmusic.database.models.MyMusicListModel12;
import com.d.dmusic.database.models.MyMusicListModel13;
import com.d.dmusic.database.models.MyMusicListModel14;
import com.d.dmusic.database.models.MyMusicListModel15;
import com.d.dmusic.database.models.MyMusicListModel16;
import com.d.dmusic.database.models.MyMusicListModel17;
import com.d.dmusic.database.models.MyMusicListModel18;
import com.d.dmusic.database.models.MyMusicListModel19;
import com.d.dmusic.database.models.MyMusicListModel2;
import com.d.dmusic.database.models.MyMusicListModel3;
import com.d.dmusic.database.models.MyMusicListModel4;
import com.d.dmusic.database.models.MyMusicListModel5;
import com.d.dmusic.database.models.MyMusicListModel6;
import com.d.dmusic.database.models.MyMusicListModel7;
import com.d.dmusic.database.models.MyMusicListModel8;
import com.d.dmusic.database.models.MyMusicListModel9;
import com.d.dmusic.fileutils.MusicFilterByPath;

import android.database.Cursor;
import android.util.Log;

public class DataBaseQuery {
	/**
	 * 自定义列表-按名称排序
	 */
	public static int getMusicListFromDataBaseByName(int index, List<MusicListModel> musicListModels) {
		int counts = 0;
		musicListModels.clear();
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				List<MyMusicListModel0> myMusicListModel0s = DataSupport.order("songname")
						.find(MyMusicListModel0.class);
				if (musicListModels != null) {

					for (MyMusicListModel0 myMusicListModel0 : myMusicListModel0s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel0.getId());
						musicListModel.setSongName(myMusicListModel0.getSongName());
						musicListModel.setSinger(myMusicListModel0.getSinger());
						musicListModel.setAlbum(myMusicListModel0.getAlbum());
						musicListModel.setDuration(myMusicListModel0.getDuration());
						musicListModel.setSize(myMusicListModel0.getSize());
						musicListModel.setFilePostfix(myMusicListModel0.getFilePostfix());
						musicListModel.setUrl(myMusicListModel0.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 1:
				List<MyMusicListModel1> myMusicListModel1s = DataSupport.order("songname")
						.find(MyMusicListModel1.class);
				if (musicListModels != null) {

					for (MyMusicListModel1 myMusicListModel1 : myMusicListModel1s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel1.getId());
						musicListModel.setSongName(myMusicListModel1.getSongName());
						musicListModel.setSinger(myMusicListModel1.getSinger());
						musicListModel.setAlbum(myMusicListModel1.getAlbum());
						musicListModel.setDuration(myMusicListModel1.getDuration());
						musicListModel.setSize(myMusicListModel1.getSize());
						musicListModel.setFilePostfix(myMusicListModel1.getFilePostfix());
						musicListModel.setUrl(myMusicListModel1.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 2:
				List<MyMusicListModel2> myMusicListModel2s = DataSupport.order("songname")
						.find(MyMusicListModel2.class);
				if (musicListModels != null) {

					for (MyMusicListModel2 myMusicListModel2 : myMusicListModel2s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel2.getId());
						musicListModel.setSongName(myMusicListModel2.getSongName());
						musicListModel.setSinger(myMusicListModel2.getSinger());
						musicListModel.setAlbum(myMusicListModel2.getAlbum());
						musicListModel.setDuration(myMusicListModel2.getDuration());
						musicListModel.setSize(myMusicListModel2.getSize());
						musicListModel.setFilePostfix(myMusicListModel2.getFilePostfix());
						musicListModel.setUrl(myMusicListModel2.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 3:
				List<MyMusicListModel3> myMusicListModel3s = DataSupport.order("songname")
						.find(MyMusicListModel3.class);
				if (musicListModels != null) {

					for (MyMusicListModel3 myMusicListModel3 : myMusicListModel3s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel3.getId());
						musicListModel.setSongName(myMusicListModel3.getSongName());
						musicListModel.setSinger(myMusicListModel3.getSinger());
						musicListModel.setAlbum(myMusicListModel3.getAlbum());
						musicListModel.setDuration(myMusicListModel3.getDuration());
						musicListModel.setSize(myMusicListModel3.getSize());
						musicListModel.setFilePostfix(myMusicListModel3.getFilePostfix());
						musicListModel.setUrl(myMusicListModel3.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 4:
				List<MyMusicListModel4> myMusicListModel4s = DataSupport.order("songname")
						.find(MyMusicListModel4.class);
				if (musicListModels != null) {

					for (MyMusicListModel4 myMusicListModel4 : myMusicListModel4s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel4.getId());
						musicListModel.setSongName(myMusicListModel4.getSongName());
						musicListModel.setSinger(myMusicListModel4.getSinger());
						musicListModel.setAlbum(myMusicListModel4.getAlbum());
						musicListModel.setDuration(myMusicListModel4.getDuration());
						musicListModel.setSize(myMusicListModel4.getSize());
						musicListModel.setFilePostfix(myMusicListModel4.getFilePostfix());
						musicListModel.setUrl(myMusicListModel4.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 5:
				List<MyMusicListModel5> myMusicListModel5s = DataSupport.order("songname")
						.find(MyMusicListModel5.class);
				if (musicListModels != null) {

					for (MyMusicListModel5 myMusicListModel5 : myMusicListModel5s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel5.getId());
						musicListModel.setSongName(myMusicListModel5.getSongName());
						musicListModel.setSinger(myMusicListModel5.getSinger());
						musicListModel.setAlbum(myMusicListModel5.getAlbum());
						musicListModel.setDuration(myMusicListModel5.getDuration());
						musicListModel.setSize(myMusicListModel5.getSize());
						musicListModel.setFilePostfix(myMusicListModel5.getFilePostfix());
						musicListModel.setUrl(myMusicListModel5.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 6:
				List<MyMusicListModel6> myMusicListModel6s = DataSupport.order("songname")
						.find(MyMusicListModel6.class);
				if (musicListModels != null) {

					for (MyMusicListModel6 myMusicListModel6 : myMusicListModel6s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel6.getId());
						musicListModel.setSongName(myMusicListModel6.getSongName());
						musicListModel.setSinger(myMusicListModel6.getSinger());
						musicListModel.setAlbum(myMusicListModel6.getAlbum());
						musicListModel.setDuration(myMusicListModel6.getDuration());
						musicListModel.setSize(myMusicListModel6.getSize());
						musicListModel.setFilePostfix(myMusicListModel6.getFilePostfix());
						musicListModel.setUrl(myMusicListModel6.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 7:
				List<MyMusicListModel7> myMusicListModel7s = DataSupport.order("songname")
						.find(MyMusicListModel7.class);
				if (musicListModels != null) {

					for (MyMusicListModel7 myMusicListModel7 : myMusicListModel7s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel7.getId());
						musicListModel.setSongName(myMusicListModel7.getSongName());
						musicListModel.setSinger(myMusicListModel7.getSinger());
						musicListModel.setAlbum(myMusicListModel7.getAlbum());
						musicListModel.setDuration(myMusicListModel7.getDuration());
						musicListModel.setSize(myMusicListModel7.getSize());
						musicListModel.setFilePostfix(myMusicListModel7.getFilePostfix());
						musicListModel.setUrl(myMusicListModel7.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 8:
				List<MyMusicListModel8> myMusicListModel8s = DataSupport.order("songname")
						.find(MyMusicListModel8.class);
				if (musicListModels != null) {

					for (MyMusicListModel8 myMusicListModel8 : myMusicListModel8s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel8.getId());
						musicListModel.setSongName(myMusicListModel8.getSongName());
						musicListModel.setSinger(myMusicListModel8.getSinger());
						musicListModel.setAlbum(myMusicListModel8.getAlbum());
						musicListModel.setDuration(myMusicListModel8.getDuration());
						musicListModel.setSize(myMusicListModel8.getSize());
						musicListModel.setFilePostfix(myMusicListModel8.getFilePostfix());
						musicListModel.setUrl(myMusicListModel8.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 9:
				List<MyMusicListModel9> myMusicListModel9s = DataSupport.order("songname")
						.find(MyMusicListModel9.class);
				if (musicListModels != null) {

					for (MyMusicListModel9 myMusicListModel9 : myMusicListModel9s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel9.getId());
						musicListModel.setSongName(myMusicListModel9.getSongName());
						musicListModel.setSinger(myMusicListModel9.getSinger());
						musicListModel.setAlbum(myMusicListModel9.getAlbum());
						musicListModel.setDuration(myMusicListModel9.getDuration());
						musicListModel.setSize(myMusicListModel9.getSize());
						musicListModel.setFilePostfix(myMusicListModel9.getFilePostfix());
						musicListModel.setUrl(myMusicListModel9.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			}
			break;
		case 1:
			switch (index % 10) {
			case 0:
				List<MyMusicListModel10> myMusicListModel10s = DataSupport.order("songname")
						.find(MyMusicListModel10.class);
				if (musicListModels != null) {

					for (MyMusicListModel10 myMusicListModel10 : myMusicListModel10s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel10.getId());
						musicListModel.setSongName(myMusicListModel10.getSongName());
						musicListModel.setSinger(myMusicListModel10.getSinger());
						musicListModel.setAlbum(myMusicListModel10.getAlbum());
						musicListModel.setDuration(myMusicListModel10.getDuration());
						musicListModel.setSize(myMusicListModel10.getSize());
						musicListModel.setFilePostfix(myMusicListModel10.getFilePostfix());
						musicListModel.setUrl(myMusicListModel10.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 1:
				List<MyMusicListModel11> myMusicListModel11s = DataSupport.order("songname")
						.find(MyMusicListModel11.class);
				if (musicListModels != null) {

					for (MyMusicListModel11 myMusicListModel11 : myMusicListModel11s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel11.getId());
						musicListModel.setSongName(myMusicListModel11.getSongName());
						musicListModel.setSinger(myMusicListModel11.getSinger());
						musicListModel.setAlbum(myMusicListModel11.getAlbum());
						musicListModel.setDuration(myMusicListModel11.getDuration());
						musicListModel.setSize(myMusicListModel11.getSize());
						musicListModel.setFilePostfix(myMusicListModel11.getFilePostfix());
						musicListModel.setUrl(myMusicListModel11.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 2:
				List<MyMusicListModel12> myMusicListModel12s = DataSupport.order("songname")
						.find(MyMusicListModel12.class);
				if (musicListModels != null) {

					for (MyMusicListModel12 myMusicListModel12 : myMusicListModel12s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel12.getId());
						musicListModel.setSongName(myMusicListModel12.getSongName());
						musicListModel.setSinger(myMusicListModel12.getSinger());
						musicListModel.setAlbum(myMusicListModel12.getAlbum());
						musicListModel.setDuration(myMusicListModel12.getDuration());
						musicListModel.setSize(myMusicListModel12.getSize());
						musicListModel.setFilePostfix(myMusicListModel12.getFilePostfix());
						musicListModel.setUrl(myMusicListModel12.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 3:
				List<MyMusicListModel13> myMusicListModel13s = DataSupport.order("songname")
						.find(MyMusicListModel13.class);
				if (musicListModels != null) {

					for (MyMusicListModel13 myMusicListModel13 : myMusicListModel13s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel13.getId());
						musicListModel.setSongName(myMusicListModel13.getSongName());
						musicListModel.setSinger(myMusicListModel13.getSinger());
						musicListModel.setAlbum(myMusicListModel13.getAlbum());
						musicListModel.setDuration(myMusicListModel13.getDuration());
						musicListModel.setSize(myMusicListModel13.getSize());
						musicListModel.setFilePostfix(myMusicListModel13.getFilePostfix());
						musicListModel.setUrl(myMusicListModel13.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 4:
				List<MyMusicListModel14> myMusicListModel14s = DataSupport.order("songname")
						.find(MyMusicListModel14.class);
				if (musicListModels != null) {

					for (MyMusicListModel14 myMusicListModel14 : myMusicListModel14s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel14.getId());
						musicListModel.setSongName(myMusicListModel14.getSongName());
						musicListModel.setSinger(myMusicListModel14.getSinger());
						musicListModel.setAlbum(myMusicListModel14.getAlbum());
						musicListModel.setDuration(myMusicListModel14.getDuration());
						musicListModel.setSize(myMusicListModel14.getSize());
						musicListModel.setFilePostfix(myMusicListModel14.getFilePostfix());
						musicListModel.setUrl(myMusicListModel14.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 5:
				List<MyMusicListModel15> myMusicListModel15s = DataSupport.order("songname")
						.find(MyMusicListModel15.class);
				if (musicListModels != null) {

					for (MyMusicListModel15 myMusicListModel15 : myMusicListModel15s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel15.getId());
						musicListModel.setSongName(myMusicListModel15.getSongName());
						musicListModel.setSinger(myMusicListModel15.getSinger());
						musicListModel.setAlbum(myMusicListModel15.getAlbum());
						musicListModel.setDuration(myMusicListModel15.getDuration());
						musicListModel.setSize(myMusicListModel15.getSize());
						musicListModel.setFilePostfix(myMusicListModel15.getFilePostfix());
						musicListModel.setUrl(myMusicListModel15.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 6:
				List<MyMusicListModel16> myMusicListModel16s = DataSupport.order("songname")
						.find(MyMusicListModel16.class);
				if (musicListModels != null) {

					for (MyMusicListModel16 myMusicListModel16 : myMusicListModel16s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel16.getId());
						musicListModel.setSongName(myMusicListModel16.getSongName());
						musicListModel.setSinger(myMusicListModel16.getSinger());
						musicListModel.setAlbum(myMusicListModel16.getAlbum());
						musicListModel.setDuration(myMusicListModel16.getDuration());
						musicListModel.setSize(myMusicListModel16.getSize());
						musicListModel.setFilePostfix(myMusicListModel16.getFilePostfix());
						musicListModel.setUrl(myMusicListModel16.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 7:
				List<MyMusicListModel17> myMusicListModel17s = DataSupport.order("songname")
						.find(MyMusicListModel17.class);
				if (musicListModels != null) {

					for (MyMusicListModel17 myMusicListModel17 : myMusicListModel17s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel17.getId());
						musicListModel.setSongName(myMusicListModel17.getSongName());
						musicListModel.setSinger(myMusicListModel17.getSinger());
						musicListModel.setAlbum(myMusicListModel17.getAlbum());
						musicListModel.setDuration(myMusicListModel17.getDuration());
						musicListModel.setSize(myMusicListModel17.getSize());
						musicListModel.setFilePostfix(myMusicListModel17.getFilePostfix());
						musicListModel.setUrl(myMusicListModel17.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 8:
				List<MyMusicListModel18> myMusicListModel18s = DataSupport.order("songname")
						.find(MyMusicListModel18.class);
				if (musicListModels != null) {

					for (MyMusicListModel18 myMusicListModel18 : myMusicListModel18s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel18.getId());
						musicListModel.setSongName(myMusicListModel18.getSongName());
						musicListModel.setSinger(myMusicListModel18.getSinger());
						musicListModel.setAlbum(myMusicListModel18.getAlbum());
						musicListModel.setDuration(myMusicListModel18.getDuration());
						musicListModel.setSize(myMusicListModel18.getSize());
						musicListModel.setFilePostfix(myMusicListModel18.getFilePostfix());
						musicListModel.setUrl(myMusicListModel18.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 9:
				List<MyMusicListModel19> myMusicListModel19s = DataSupport.order("songname")
						.find(MyMusicListModel19.class);
				if (musicListModels != null) {

					for (MyMusicListModel19 myMusicListModel19 : myMusicListModel19s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel19.getId());
						musicListModel.setSongName(myMusicListModel19.getSongName());
						musicListModel.setSinger(myMusicListModel19.getSinger());
						musicListModel.setAlbum(myMusicListModel19.getAlbum());
						musicListModel.setDuration(myMusicListModel19.getDuration());
						musicListModel.setSize(myMusicListModel19.getSize());
						musicListModel.setFilePostfix(myMusicListModel19.getFilePostfix());
						musicListModel.setUrl(myMusicListModel19.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			}
			break;
		}

		return counts;

	}

	/**
	 * 按时间排序-即按id主键查询
	 * 
	 * @param index
	 * @param musicListModels
	 * @return counts 歌曲数目
	 */
	public static int getMusicListFromDataBaseByTime(int index, List<MusicListModel> musicListModels) {

		int counts = 0;
		musicListModels.clear();// 清空数据
		switch (index / 10) {
		case 0: {
			switch (index % 10) {
			case 0:
				List<MyMusicListModel0> MyMusicListModel0s = DataSupport.findAll(MyMusicListModel0.class);
				if (MyMusicListModel0s != null) {

					for (MyMusicListModel0 myMusicListModel0 : MyMusicListModel0s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel0.getId());
						musicListModel.setSongName(myMusicListModel0.getSongName());
						musicListModel.setSinger(myMusicListModel0.getSinger());
						musicListModel.setAlbum(myMusicListModel0.getAlbum());
						musicListModel.setDuration(myMusicListModel0.getDuration());
						musicListModel.setSize(myMusicListModel0.getSize());
						musicListModel.setFilePostfix(myMusicListModel0.getFilePostfix());
						musicListModel.setUrl(myMusicListModel0.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;

					}
				}
				break;
			case 1:
				List<MyMusicListModel1> MyMusicListModel1s = DataSupport.findAll(MyMusicListModel1.class);
				if (MyMusicListModel1s != null) {

					for (MyMusicListModel1 myMusicListModel1 : MyMusicListModel1s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel1.getId());
						musicListModel.setSongName(myMusicListModel1.getSongName());
						musicListModel.setSinger(myMusicListModel1.getSinger());
						musicListModel.setAlbum(myMusicListModel1.getAlbum());
						musicListModel.setDuration(myMusicListModel1.getDuration());
						musicListModel.setSize(myMusicListModel1.getSize());
						musicListModel.setFilePostfix(myMusicListModel1.getFilePostfix());
						musicListModel.setUrl(myMusicListModel1.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 2:
				List<MyMusicListModel2> MyMusicListModel2s = DataSupport.findAll(MyMusicListModel2.class);
				if (MyMusicListModel2s != null) {

					for (MyMusicListModel2 myMusicListModel2 : MyMusicListModel2s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel2.getId());
						musicListModel.setSongName(myMusicListModel2.getSongName());
						musicListModel.setSinger(myMusicListModel2.getSinger());
						musicListModel.setAlbum(myMusicListModel2.getAlbum());
						musicListModel.setDuration(myMusicListModel2.getDuration());
						musicListModel.setSize(myMusicListModel2.getSize());
						musicListModel.setFilePostfix(myMusicListModel2.getFilePostfix());
						musicListModel.setUrl(myMusicListModel2.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 3:
				List<MyMusicListModel3> MyMusicListModel3s = DataSupport.findAll(MyMusicListModel3.class);
				if (MyMusicListModel3s != null) {

					for (MyMusicListModel3 myMusicListModel3 : MyMusicListModel3s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel3.getId());
						musicListModel.setSongName(myMusicListModel3.getSongName());
						musicListModel.setSinger(myMusicListModel3.getSinger());
						musicListModel.setAlbum(myMusicListModel3.getAlbum());
						musicListModel.setDuration(myMusicListModel3.getDuration());
						musicListModel.setSize(myMusicListModel3.getSize());
						musicListModel.setFilePostfix(myMusicListModel3.getFilePostfix());
						musicListModel.setUrl(myMusicListModel3.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 4:
				List<MyMusicListModel4> MyMusicListModel4s = DataSupport.findAll(MyMusicListModel4.class);
				if (MyMusicListModel4s != null) {

					for (MyMusicListModel4 myMusicListModel4 : MyMusicListModel4s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel4.getId());
						musicListModel.setSongName(myMusicListModel4.getSongName());
						musicListModel.setSinger(myMusicListModel4.getSinger());
						musicListModel.setAlbum(myMusicListModel4.getAlbum());
						musicListModel.setDuration(myMusicListModel4.getDuration());
						musicListModel.setSize(myMusicListModel4.getSize());
						musicListModel.setFilePostfix(myMusicListModel4.getFilePostfix());
						musicListModel.setUrl(myMusicListModel4.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 5:
				List<MyMusicListModel5> MyMusicListModel5s = DataSupport.findAll(MyMusicListModel5.class);
				if (MyMusicListModel5s != null) {

					for (MyMusicListModel5 myMusicListModel5 : MyMusicListModel5s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel5.getId());
						musicListModel.setSongName(myMusicListModel5.getSongName());
						musicListModel.setSinger(myMusicListModel5.getSinger());
						musicListModel.setAlbum(myMusicListModel5.getAlbum());
						musicListModel.setDuration(myMusicListModel5.getDuration());
						musicListModel.setSize(myMusicListModel5.getSize());
						musicListModel.setFilePostfix(myMusicListModel5.getFilePostfix());
						musicListModel.setUrl(myMusicListModel5.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 6:
				List<MyMusicListModel6> MyMusicListModel6s = DataSupport.findAll(MyMusicListModel6.class);
				if (MyMusicListModel6s != null) {

					for (MyMusicListModel6 myMusicListModel6 : MyMusicListModel6s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel6.getId());
						musicListModel.setSongName(myMusicListModel6.getSongName());
						musicListModel.setSinger(myMusicListModel6.getSinger());
						musicListModel.setAlbum(myMusicListModel6.getAlbum());
						musicListModel.setDuration(myMusicListModel6.getDuration());
						musicListModel.setSize(myMusicListModel6.getSize());
						musicListModel.setFilePostfix(myMusicListModel6.getFilePostfix());
						musicListModel.setUrl(myMusicListModel6.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 7:
				List<MyMusicListModel7> MyMusicListModel7s = DataSupport.findAll(MyMusicListModel7.class);
				if (MyMusicListModel7s != null) {

					for (MyMusicListModel7 myMusicListModel7 : MyMusicListModel7s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel7.getId());
						musicListModel.setSongName(myMusicListModel7.getSongName());
						musicListModel.setSinger(myMusicListModel7.getSinger());
						musicListModel.setAlbum(myMusicListModel7.getAlbum());
						musicListModel.setDuration(myMusicListModel7.getDuration());
						musicListModel.setSize(myMusicListModel7.getSize());
						musicListModel.setFilePostfix(myMusicListModel7.getFilePostfix());
						musicListModel.setUrl(myMusicListModel7.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 8:
				List<MyMusicListModel8> MyMusicListModel8s = DataSupport.findAll(MyMusicListModel8.class);
				if (MyMusicListModel8s != null) {

					for (MyMusicListModel8 myMusicListModel8 : MyMusicListModel8s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel8.getId());
						musicListModel.setSongName(myMusicListModel8.getSongName());
						musicListModel.setSinger(myMusicListModel8.getSinger());
						musicListModel.setAlbum(myMusicListModel8.getAlbum());
						musicListModel.setDuration(myMusicListModel8.getDuration());
						musicListModel.setSize(myMusicListModel8.getSize());
						musicListModel.setFilePostfix(myMusicListModel8.getFilePostfix());
						musicListModel.setUrl(myMusicListModel8.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 9:
				List<MyMusicListModel9> MyMusicListModel9s = DataSupport.findAll(MyMusicListModel9.class);
				if (MyMusicListModel9s != null) {

					for (MyMusicListModel9 myMusicListModel9 : MyMusicListModel9s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel9.getId());
						musicListModel.setSongName(myMusicListModel9.getSongName());
						musicListModel.setSinger(myMusicListModel9.getSinger());
						musicListModel.setAlbum(myMusicListModel9.getAlbum());
						musicListModel.setDuration(myMusicListModel9.getDuration());
						musicListModel.setSize(myMusicListModel9.getSize());
						musicListModel.setFilePostfix(myMusicListModel9.getFilePostfix());
						musicListModel.setUrl(myMusicListModel9.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;

			}
		}
			break;
		case 1: {
			switch (index % 10) {

			case 0:
				List<MyMusicListModel10> MyMusicListModel10s = DataSupport.findAll(MyMusicListModel10.class);
				if (MyMusicListModel10s != null) {

					for (MyMusicListModel10 myMusicListModel10 : MyMusicListModel10s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel10.getId());
						musicListModel.setSongName(myMusicListModel10.getSongName());
						musicListModel.setSinger(myMusicListModel10.getSinger());
						musicListModel.setAlbum(myMusicListModel10.getAlbum());
						musicListModel.setDuration(myMusicListModel10.getDuration());
						musicListModel.setSize(myMusicListModel10.getSize());
						musicListModel.setFilePostfix(myMusicListModel10.getFilePostfix());
						musicListModel.setUrl(myMusicListModel10.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 1:
				List<MyMusicListModel11> MyMusicListModel11s = DataSupport.findAll(MyMusicListModel11.class);
				if (MyMusicListModel11s != null) {

					for (MyMusicListModel11 myMusicListModel11 : MyMusicListModel11s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel11.getId());
						musicListModel.setSongName(myMusicListModel11.getSongName());
						musicListModel.setSinger(myMusicListModel11.getSinger());
						musicListModel.setAlbum(myMusicListModel11.getAlbum());
						musicListModel.setDuration(myMusicListModel11.getDuration());
						musicListModel.setSize(myMusicListModel11.getSize());
						musicListModel.setFilePostfix(myMusicListModel11.getFilePostfix());
						musicListModel.setUrl(myMusicListModel11.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 2:
				List<MyMusicListModel12> MyMusicListModel12s = DataSupport.findAll(MyMusicListModel12.class);
				if (MyMusicListModel12s != null) {

					for (MyMusicListModel12 myMusicListModel12 : MyMusicListModel12s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel12.getId());
						musicListModel.setSongName(myMusicListModel12.getSongName());
						musicListModel.setSinger(myMusicListModel12.getSinger());
						musicListModel.setAlbum(myMusicListModel12.getAlbum());
						musicListModel.setDuration(myMusicListModel12.getDuration());
						musicListModel.setSize(myMusicListModel12.getSize());
						musicListModel.setFilePostfix(myMusicListModel12.getFilePostfix());
						musicListModel.setUrl(myMusicListModel12.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 3:
				List<MyMusicListModel13> MyMusicListModel13s = DataSupport.findAll(MyMusicListModel13.class);
				if (MyMusicListModel13s != null) {

					for (MyMusicListModel13 myMusicListModel13 : MyMusicListModel13s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel13.getId());
						musicListModel.setSongName(myMusicListModel13.getSongName());
						musicListModel.setSinger(myMusicListModel13.getSinger());
						musicListModel.setAlbum(myMusicListModel13.getAlbum());
						musicListModel.setDuration(myMusicListModel13.getDuration());
						musicListModel.setSize(myMusicListModel13.getSize());
						musicListModel.setFilePostfix(myMusicListModel13.getFilePostfix());
						musicListModel.setUrl(myMusicListModel13.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 4:
				List<MyMusicListModel14> MyMusicListModel14s = DataSupport.findAll(MyMusicListModel14.class);
				if (MyMusicListModel14s != null) {

					for (MyMusicListModel14 myMusicListModel14 : MyMusicListModel14s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel14.getId());
						musicListModel.setSongName(myMusicListModel14.getSongName());
						musicListModel.setSinger(myMusicListModel14.getSinger());
						musicListModel.setAlbum(myMusicListModel14.getAlbum());
						musicListModel.setDuration(myMusicListModel14.getDuration());
						musicListModel.setSize(myMusicListModel14.getSize());
						musicListModel.setFilePostfix(myMusicListModel14.getFilePostfix());
						musicListModel.setUrl(myMusicListModel14.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 5:
				List<MyMusicListModel15> MyMusicListModel15s = DataSupport.findAll(MyMusicListModel15.class);
				if (MyMusicListModel15s != null) {

					for (MyMusicListModel15 myMusicListModel15 : MyMusicListModel15s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel15.getId());
						musicListModel.setSongName(myMusicListModel15.getSongName());
						musicListModel.setSinger(myMusicListModel15.getSinger());
						musicListModel.setAlbum(myMusicListModel15.getAlbum());
						musicListModel.setDuration(myMusicListModel15.getDuration());
						musicListModel.setSize(myMusicListModel15.getSize());
						musicListModel.setFilePostfix(myMusicListModel15.getFilePostfix());
						musicListModel.setUrl(myMusicListModel15.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 6:
				List<MyMusicListModel16> MyMusicListModel16s = DataSupport.findAll(MyMusicListModel16.class);
				if (MyMusicListModel16s != null) {

					for (MyMusicListModel16 myMusicListModel16 : MyMusicListModel16s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel16.getId());
						musicListModel.setSongName(myMusicListModel16.getSongName());
						musicListModel.setSinger(myMusicListModel16.getSinger());
						musicListModel.setAlbum(myMusicListModel16.getAlbum());
						musicListModel.setDuration(myMusicListModel16.getDuration());
						musicListModel.setSize(myMusicListModel16.getSize());
						musicListModel.setFilePostfix(myMusicListModel16.getFilePostfix());
						musicListModel.setUrl(myMusicListModel16.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 7:
				List<MyMusicListModel17> MyMusicListModel17s = DataSupport.findAll(MyMusicListModel17.class);
				if (MyMusicListModel17s != null) {

					for (MyMusicListModel17 myMusicListModel17 : MyMusicListModel17s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel17.getId());
						musicListModel.setSongName(myMusicListModel17.getSongName());
						musicListModel.setSinger(myMusicListModel17.getSinger());
						musicListModel.setAlbum(myMusicListModel17.getAlbum());
						musicListModel.setDuration(myMusicListModel17.getDuration());
						musicListModel.setSize(myMusicListModel17.getSize());
						musicListModel.setFilePostfix(myMusicListModel17.getFilePostfix());
						musicListModel.setUrl(myMusicListModel17.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 8:
				List<MyMusicListModel18> MyMusicListModel18s = DataSupport.findAll(MyMusicListModel18.class);
				if (MyMusicListModel18s != null) {

					for (MyMusicListModel18 myMusicListModel18 : MyMusicListModel18s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel18.getId());
						musicListModel.setSongName(myMusicListModel18.getSongName());
						musicListModel.setSinger(myMusicListModel18.getSinger());
						musicListModel.setAlbum(myMusicListModel18.getAlbum());
						musicListModel.setDuration(myMusicListModel18.getDuration());
						musicListModel.setSize(myMusicListModel18.getSize());
						musicListModel.setFilePostfix(myMusicListModel18.getFilePostfix());
						musicListModel.setUrl(myMusicListModel18.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 9:
				List<MyMusicListModel19> MyMusicListModel19s = DataSupport.findAll(MyMusicListModel19.class);
				if (MyMusicListModel19s != null) {

					for (MyMusicListModel19 myMusicListModel19 : MyMusicListModel19s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel19.getId());
						musicListModel.setSongName(myMusicListModel19.getSongName());
						musicListModel.setSinger(myMusicListModel19.getSinger());
						musicListModel.setAlbum(myMusicListModel19.getAlbum());
						musicListModel.setDuration(myMusicListModel19.getDuration());
						musicListModel.setSize(myMusicListModel19.getSize());
						musicListModel.setFilePostfix(myMusicListModel19.getFilePostfix());
						musicListModel.setUrl(myMusicListModel19.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;

			}
		}
			break;
		}
		return counts;

	}

	/**
	 * 自定义列表-自定义排序
	 */
	public static int getMusicListFromDataBaseBycustomSeq(int index, List<MusicListModel> musicListModels) {
		int counts = 0;
		musicListModels.clear();// 清空数据
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				List<MyMusicListModel0> myMusicListModel0s = DataSupport.order("customseq")
						.find(MyMusicListModel0.class);
				if (musicListModels != null) {

					for (MyMusicListModel0 myMusicListModel0 : myMusicListModel0s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel0.getId());
						musicListModel.setSongName(myMusicListModel0.getSongName());
						musicListModel.setSinger(myMusicListModel0.getSinger());
						musicListModel.setAlbum(myMusicListModel0.getAlbum());
						musicListModel.setDuration(myMusicListModel0.getDuration());
						musicListModel.setSize(myMusicListModel0.getSize());
						musicListModel.setFilePostfix(myMusicListModel0.getFilePostfix());
						musicListModel.setUrl(myMusicListModel0.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 1:
				List<MyMusicListModel1> myMusicListModel1s = DataSupport.order("customseq")
						.find(MyMusicListModel1.class);
				if (musicListModels != null) {

					for (MyMusicListModel1 myMusicListModel1 : myMusicListModel1s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel1.getId());
						musicListModel.setSongName(myMusicListModel1.getSongName());
						musicListModel.setSinger(myMusicListModel1.getSinger());
						musicListModel.setAlbum(myMusicListModel1.getAlbum());
						musicListModel.setDuration(myMusicListModel1.getDuration());
						musicListModel.setSize(myMusicListModel1.getSize());
						musicListModel.setFilePostfix(myMusicListModel1.getFilePostfix());
						musicListModel.setUrl(myMusicListModel1.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 2:
				List<MyMusicListModel2> myMusicListModel2s = DataSupport.order("customseq")
						.find(MyMusicListModel2.class);
				if (musicListModels != null) {

					for (MyMusicListModel2 myMusicListModel2 : myMusicListModel2s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel2.getId());
						musicListModel.setSongName(myMusicListModel2.getSongName());
						musicListModel.setSinger(myMusicListModel2.getSinger());
						musicListModel.setAlbum(myMusicListModel2.getAlbum());
						musicListModel.setDuration(myMusicListModel2.getDuration());
						musicListModel.setSize(myMusicListModel2.getSize());
						musicListModel.setFilePostfix(myMusicListModel2.getFilePostfix());
						musicListModel.setUrl(myMusicListModel2.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 3:
				List<MyMusicListModel3> myMusicListModel3s = DataSupport.order("customseq")
						.find(MyMusicListModel3.class);
				if (musicListModels != null) {

					for (MyMusicListModel3 myMusicListModel3 : myMusicListModel3s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel3.getId());
						musicListModel.setSongName(myMusicListModel3.getSongName());
						musicListModel.setSinger(myMusicListModel3.getSinger());
						musicListModel.setAlbum(myMusicListModel3.getAlbum());
						musicListModel.setDuration(myMusicListModel3.getDuration());
						musicListModel.setSize(myMusicListModel3.getSize());
						musicListModel.setFilePostfix(myMusicListModel3.getFilePostfix());
						musicListModel.setUrl(myMusicListModel3.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 4:
				List<MyMusicListModel4> myMusicListModel4s = DataSupport.order("customseq")
						.find(MyMusicListModel4.class);
				if (musicListModels != null) {

					for (MyMusicListModel4 myMusicListModel4 : myMusicListModel4s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel4.getId());
						musicListModel.setSongName(myMusicListModel4.getSongName());
						musicListModel.setSinger(myMusicListModel4.getSinger());
						musicListModel.setAlbum(myMusicListModel4.getAlbum());
						musicListModel.setDuration(myMusicListModel4.getDuration());
						musicListModel.setSize(myMusicListModel4.getSize());
						musicListModel.setFilePostfix(myMusicListModel4.getFilePostfix());
						musicListModel.setUrl(myMusicListModel4.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 5:
				List<MyMusicListModel5> myMusicListModel5s = DataSupport.order("customseq")
						.find(MyMusicListModel5.class);
				if (musicListModels != null) {

					for (MyMusicListModel5 myMusicListModel5 : myMusicListModel5s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel5.getId());
						musicListModel.setSongName(myMusicListModel5.getSongName());
						musicListModel.setSinger(myMusicListModel5.getSinger());
						musicListModel.setAlbum(myMusicListModel5.getAlbum());
						musicListModel.setDuration(myMusicListModel5.getDuration());
						musicListModel.setSize(myMusicListModel5.getSize());
						musicListModel.setFilePostfix(myMusicListModel5.getFilePostfix());
						musicListModel.setUrl(myMusicListModel5.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 6:
				List<MyMusicListModel6> myMusicListModel6s = DataSupport.order("customseq")
						.find(MyMusicListModel6.class);
				if (musicListModels != null) {

					for (MyMusicListModel6 myMusicListModel6 : myMusicListModel6s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel6.getId());
						musicListModel.setSongName(myMusicListModel6.getSongName());
						musicListModel.setSinger(myMusicListModel6.getSinger());
						musicListModel.setAlbum(myMusicListModel6.getAlbum());
						musicListModel.setDuration(myMusicListModel6.getDuration());
						musicListModel.setSize(myMusicListModel6.getSize());
						musicListModel.setFilePostfix(myMusicListModel6.getFilePostfix());
						musicListModel.setUrl(myMusicListModel6.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 7:
				List<MyMusicListModel7> myMusicListModel7s = DataSupport.order("customseq")
						.find(MyMusicListModel7.class);
				if (musicListModels != null) {

					for (MyMusicListModel7 myMusicListModel7 : myMusicListModel7s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel7.getId());
						musicListModel.setSongName(myMusicListModel7.getSongName());
						musicListModel.setSinger(myMusicListModel7.getSinger());
						musicListModel.setAlbum(myMusicListModel7.getAlbum());
						musicListModel.setDuration(myMusicListModel7.getDuration());
						musicListModel.setSize(myMusicListModel7.getSize());
						musicListModel.setFilePostfix(myMusicListModel7.getFilePostfix());
						musicListModel.setUrl(myMusicListModel7.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 8:
				List<MyMusicListModel8> myMusicListModel8s = DataSupport.order("customseq")
						.find(MyMusicListModel8.class);
				if (musicListModels != null) {

					for (MyMusicListModel8 myMusicListModel8 : myMusicListModel8s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel8.getId());
						musicListModel.setSongName(myMusicListModel8.getSongName());
						musicListModel.setSinger(myMusicListModel8.getSinger());
						musicListModel.setAlbum(myMusicListModel8.getAlbum());
						musicListModel.setDuration(myMusicListModel8.getDuration());
						musicListModel.setSize(myMusicListModel8.getSize());
						musicListModel.setFilePostfix(myMusicListModel8.getFilePostfix());
						musicListModel.setUrl(myMusicListModel8.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 9:
				List<MyMusicListModel9> myMusicListModel9s = DataSupport.order("customseq")
						.find(MyMusicListModel9.class);
				if (musicListModels != null) {

					for (MyMusicListModel9 myMusicListModel9 : myMusicListModel9s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel9.getId());
						musicListModel.setSongName(myMusicListModel9.getSongName());
						musicListModel.setSinger(myMusicListModel9.getSinger());
						musicListModel.setAlbum(myMusicListModel9.getAlbum());
						musicListModel.setDuration(myMusicListModel9.getDuration());
						musicListModel.setSize(myMusicListModel9.getSize());
						musicListModel.setFilePostfix(myMusicListModel9.getFilePostfix());
						musicListModel.setUrl(myMusicListModel9.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			}
			break;
		case 1:
			switch (index % 10) {
			case 0:
				List<MyMusicListModel10> myMusicListModel10s = DataSupport.order("customseq")
						.find(MyMusicListModel10.class);
				if (musicListModels != null) {

					for (MyMusicListModel10 myMusicListModel10 : myMusicListModel10s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel10.getId());
						musicListModel.setSongName(myMusicListModel10.getSongName());
						musicListModel.setSinger(myMusicListModel10.getSinger());
						musicListModel.setAlbum(myMusicListModel10.getAlbum());
						musicListModel.setDuration(myMusicListModel10.getDuration());
						musicListModel.setSize(myMusicListModel10.getSize());
						musicListModel.setFilePostfix(myMusicListModel10.getFilePostfix());
						musicListModel.setUrl(myMusicListModel10.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 1:
				List<MyMusicListModel11> myMusicListModel11s = DataSupport.order("customseq")
						.find(MyMusicListModel11.class);
				if (musicListModels != null) {

					for (MyMusicListModel11 myMusicListModel11 : myMusicListModel11s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel11.getId());
						musicListModel.setSongName(myMusicListModel11.getSongName());
						musicListModel.setSinger(myMusicListModel11.getSinger());
						musicListModel.setAlbum(myMusicListModel11.getAlbum());
						musicListModel.setDuration(myMusicListModel11.getDuration());
						musicListModel.setSize(myMusicListModel11.getSize());
						musicListModel.setFilePostfix(myMusicListModel11.getFilePostfix());
						musicListModel.setUrl(myMusicListModel11.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 2:
				List<MyMusicListModel12> myMusicListModel12s = DataSupport.order("customseq")
						.find(MyMusicListModel12.class);
				if (musicListModels != null) {

					for (MyMusicListModel12 myMusicListModel12 : myMusicListModel12s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel12.getId());
						musicListModel.setSongName(myMusicListModel12.getSongName());
						musicListModel.setSinger(myMusicListModel12.getSinger());
						musicListModel.setAlbum(myMusicListModel12.getAlbum());
						musicListModel.setDuration(myMusicListModel12.getDuration());
						musicListModel.setSize(myMusicListModel12.getSize());
						musicListModel.setFilePostfix(myMusicListModel12.getFilePostfix());
						musicListModel.setUrl(myMusicListModel12.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 3:
				List<MyMusicListModel13> myMusicListModel13s = DataSupport.order("customseq")
						.find(MyMusicListModel13.class);
				if (musicListModels != null) {

					for (MyMusicListModel13 myMusicListModel13 : myMusicListModel13s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel13.getId());
						musicListModel.setSongName(myMusicListModel13.getSongName());
						musicListModel.setSinger(myMusicListModel13.getSinger());
						musicListModel.setAlbum(myMusicListModel13.getAlbum());
						musicListModel.setDuration(myMusicListModel13.getDuration());
						musicListModel.setSize(myMusicListModel13.getSize());
						musicListModel.setFilePostfix(myMusicListModel13.getFilePostfix());
						musicListModel.setUrl(myMusicListModel13.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 4:
				List<MyMusicListModel14> myMusicListModel14s = DataSupport.order("customseq")
						.find(MyMusicListModel14.class);
				if (musicListModels != null) {

					for (MyMusicListModel14 myMusicListModel14 : myMusicListModel14s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel14.getId());
						musicListModel.setSongName(myMusicListModel14.getSongName());
						musicListModel.setSinger(myMusicListModel14.getSinger());
						musicListModel.setAlbum(myMusicListModel14.getAlbum());
						musicListModel.setDuration(myMusicListModel14.getDuration());
						musicListModel.setSize(myMusicListModel14.getSize());
						musicListModel.setFilePostfix(myMusicListModel14.getFilePostfix());
						musicListModel.setUrl(myMusicListModel14.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 5:
				List<MyMusicListModel15> myMusicListModel15s = DataSupport.order("customseq")
						.find(MyMusicListModel15.class);
				if (musicListModels != null) {

					for (MyMusicListModel15 myMusicListModel15 : myMusicListModel15s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel15.getId());
						musicListModel.setSongName(myMusicListModel15.getSongName());
						musicListModel.setSinger(myMusicListModel15.getSinger());
						musicListModel.setAlbum(myMusicListModel15.getAlbum());
						musicListModel.setDuration(myMusicListModel15.getDuration());
						musicListModel.setSize(myMusicListModel15.getSize());
						musicListModel.setFilePostfix(myMusicListModel15.getFilePostfix());
						musicListModel.setUrl(myMusicListModel15.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 6:
				List<MyMusicListModel16> myMusicListModel16s = DataSupport.order("customseq")
						.find(MyMusicListModel16.class);
				if (musicListModels != null) {

					for (MyMusicListModel16 myMusicListModel16 : myMusicListModel16s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel16.getId());
						musicListModel.setSongName(myMusicListModel16.getSongName());
						musicListModel.setSinger(myMusicListModel16.getSinger());
						musicListModel.setAlbum(myMusicListModel16.getAlbum());
						musicListModel.setDuration(myMusicListModel16.getDuration());
						musicListModel.setSize(myMusicListModel16.getSize());
						musicListModel.setFilePostfix(myMusicListModel16.getFilePostfix());
						musicListModel.setUrl(myMusicListModel16.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 7:
				List<MyMusicListModel17> myMusicListModel17s = DataSupport.order("customseq")
						.find(MyMusicListModel17.class);
				if (musicListModels != null) {

					for (MyMusicListModel17 myMusicListModel17 : myMusicListModel17s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel17.getId());
						musicListModel.setSongName(myMusicListModel17.getSongName());
						musicListModel.setSinger(myMusicListModel17.getSinger());
						musicListModel.setAlbum(myMusicListModel17.getAlbum());
						musicListModel.setDuration(myMusicListModel17.getDuration());
						musicListModel.setSize(myMusicListModel17.getSize());
						musicListModel.setFilePostfix(myMusicListModel17.getFilePostfix());
						musicListModel.setUrl(myMusicListModel17.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 8:
				List<MyMusicListModel18> myMusicListModel18s = DataSupport.order("customseq")
						.find(MyMusicListModel18.class);
				if (musicListModels != null) {

					for (MyMusicListModel18 myMusicListModel18 : myMusicListModel18s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel18.getId());
						musicListModel.setSongName(myMusicListModel18.getSongName());
						musicListModel.setSinger(myMusicListModel18.getSinger());
						musicListModel.setAlbum(myMusicListModel18.getAlbum());
						musicListModel.setDuration(myMusicListModel18.getDuration());
						musicListModel.setSize(myMusicListModel18.getSize());
						musicListModel.setFilePostfix(myMusicListModel18.getFilePostfix());
						musicListModel.setUrl(myMusicListModel18.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			case 9:
				List<MyMusicListModel19> myMusicListModel19s = DataSupport.order("customseq")
						.find(MyMusicListModel19.class);
				if (musicListModels != null) {

					for (MyMusicListModel19 myMusicListModel19 : myMusicListModel19s) {
						MusicListModel musicListModel = new MusicListModel();
						musicListModel.setId(myMusicListModel19.getId());
						musicListModel.setSongName(myMusicListModel19.getSongName());
						musicListModel.setSinger(myMusicListModel19.getSinger());
						musicListModel.setAlbum(myMusicListModel19.getAlbum());
						musicListModel.setDuration(myMusicListModel19.getDuration());
						musicListModel.setSize(myMusicListModel19.getSize());
						musicListModel.setFilePostfix(myMusicListModel19.getFilePostfix());
						musicListModel.setUrl(myMusicListModel19.getUrl());
						// musicListModel.setCollected(false);
						// musicListModel.setLrcName("");
						// musicListModel.setLrcUrl("");
						musicListModels.add(musicListModel);
						counts++;
					}
				}
				break;
			}
			break;
		}

		return counts;

	}

}
