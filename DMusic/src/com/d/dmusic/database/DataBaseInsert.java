package com.d.dmusic.database;

import java.util.ArrayList;
import java.util.List;

import org.litepal.crud.DataSupport;

import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.CollectionMusicListModel;
import com.d.dmusic.database.models.CustomMusicListModel;
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

import android.content.ContentValues;
import android.content.Context;

public class DataBaseInsert {

	/**
	 * 
	 * @param musicListModels--待插入歌曲列表
	 * @return true:插入一条或多条,false:已存在歌曲,未插入
	 */
	public static boolean insertColletinoMusicListIntoDataBase(List<MusicListModel> musicListModels) {
		List<CollectionMusicListModel> collectionMusicListModel = new ArrayList<CollectionMusicListModel>();
		for (MusicListModel musicListModel : musicListModels) {

			List<CollectionMusicListModel> collectionMusicListModelDB = DataSupport
					.where("url = ?", musicListModel.getUrl()).find(CollectionMusicListModel.class);
			// 查询结果中没有当前音乐，则添加，否则略过
			if (collectionMusicListModelDB.size() == 0) {
				CollectionMusicListModel cmlm = new CollectionMusicListModel();
				cmlm.setSongName(musicListModel.getSongName());
				cmlm.setSinger(musicListModel.getSinger());
				cmlm.setAlbum(musicListModel.getAlbum());
				cmlm.setDuration(musicListModel.getDuration());
				cmlm.setSize(musicListModel.getSize());
				cmlm.setFilePostfix(musicListModel.getFilePostfix());
				cmlm.setUrl(musicListModel.getUrl());
				cmlm.setCollected(true);// 收藏
				cmlm.setLrcName("");
				cmlm.setLrcUrl("");
				collectionMusicListModel.add(cmlm);
			}

		}
		if (collectionMusicListModel.size() > 0) {
			DataSupport.saveAll(collectionMusicListModel);// 扫描到的列表重新插入表CollectionMusicListModel中
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param index--表指针
	 * @param musicListModels--待插入歌曲列表
	 * @return true:插入一条或多条,false:已存在歌曲,未插入
	 */
	public static boolean insertMusicListIntoDataBase(int index, List<MusicListModel> musicListModels) {
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				List<MyMusicListModel0> myMusicListModel0s = new ArrayList<MyMusicListModel0>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel0> myMusicListModel0DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel0.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel0DB.size() == 0) {
							MyMusicListModel0 myMusicListModel0 = new MyMusicListModel0();
							myMusicListModel0.setSongName(musicListModel.getSongName());
							myMusicListModel0.setSinger(musicListModel.getSinger());
							myMusicListModel0.setAlbum(musicListModel.getAlbum());
							myMusicListModel0.setDuration(musicListModel.getDuration());
							myMusicListModel0.setSize(musicListModel.getSize());
							myMusicListModel0.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel0.setUrl(musicListModel.getUrl());
							myMusicListModel0.setCollected(false);
							myMusicListModel0.setLrcName("");
							myMusicListModel0.setLrcUrl("");
							myMusicListModel0s.add(myMusicListModel0);
						}
					}
				}
				if (myMusicListModel0s.size() > 0) {
					DataSupport.saveAll(myMusicListModel0s);// 扫描到的列表重新插入表MyMusicListModel0中
					return true;

				}
				break;
			case 1:
				List<MyMusicListModel1> myMusicListModel1s = new ArrayList<MyMusicListModel1>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel1> myMusicListModel1DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel1.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel1DB.size() == 0) {
							MyMusicListModel1 myMusicListModel1 = new MyMusicListModel1();
							myMusicListModel1.setSongName(musicListModel.getSongName());
							myMusicListModel1.setSinger(musicListModel.getSinger());
							myMusicListModel1.setAlbum(musicListModel.getAlbum());
							myMusicListModel1.setDuration(musicListModel.getDuration());
							myMusicListModel1.setSize(musicListModel.getSize());
							myMusicListModel1.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel1.setUrl(musicListModel.getUrl());
							myMusicListModel1.setCollected(false);
							myMusicListModel1.setLrcName("");
							myMusicListModel1.setLrcUrl("");
							myMusicListModel1s.add(myMusicListModel1);
						}
					}
				}
				if (myMusicListModel1s.size() > 0) {
					DataSupport.saveAll(myMusicListModel1s);// 扫描到的列表重新插入表MyMusicListModel1中
					return true;

				}
				break;
			case 2:
				List<MyMusicListModel2> myMusicListModel2s = new ArrayList<MyMusicListModel2>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel2> myMusicListModel2DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel2.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel2DB.size() == 0) {
							MyMusicListModel2 myMusicListModel2 = new MyMusicListModel2();
							myMusicListModel2.setSongName(musicListModel.getSongName());
							myMusicListModel2.setSinger(musicListModel.getSinger());
							myMusicListModel2.setAlbum(musicListModel.getAlbum());
							myMusicListModel2.setDuration(musicListModel.getDuration());
							myMusicListModel2.setSize(musicListModel.getSize());
							myMusicListModel2.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel2.setUrl(musicListModel.getUrl());
							myMusicListModel2.setCollected(false);
							myMusicListModel2.setLrcName("");
							myMusicListModel2.setLrcUrl("");
							myMusicListModel2s.add(myMusicListModel2);
						}
					}
				}
				if (myMusicListModel2s.size() > 0) {
					DataSupport.saveAll(myMusicListModel2s);// 扫描到的列表重新插入表MyMusicListModel2中
					return true;

				}
				break;
			case 3:
				List<MyMusicListModel3> myMusicListModel3s = new ArrayList<MyMusicListModel3>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel3> myMusicListModel3DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel3.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel3DB.size() == 0) {
							MyMusicListModel3 myMusicListModel3 = new MyMusicListModel3();
							myMusicListModel3.setSongName(musicListModel.getSongName());
							myMusicListModel3.setSinger(musicListModel.getSinger());
							myMusicListModel3.setAlbum(musicListModel.getAlbum());
							myMusicListModel3.setDuration(musicListModel.getDuration());
							myMusicListModel3.setSize(musicListModel.getSize());
							myMusicListModel3.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel3.setUrl(musicListModel.getUrl());
							myMusicListModel3.setCollected(false);
							myMusicListModel3.setLrcName("");
							myMusicListModel3.setLrcUrl("");
							myMusicListModel3s.add(myMusicListModel3);
						}
					}
				}
				if (myMusicListModel3s.size() > 0) {
					DataSupport.saveAll(myMusicListModel3s);// 扫描到的列表重新插入表MyMusicListModel3中
					return true;

				}
				break;
			case 4:
				List<MyMusicListModel4> myMusicListModel4s = new ArrayList<MyMusicListModel4>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel4> myMusicListModel4DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel4.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel4DB.size() == 0) {
							MyMusicListModel4 myMusicListModel4 = new MyMusicListModel4();
							myMusicListModel4.setSongName(musicListModel.getSongName());
							myMusicListModel4.setSinger(musicListModel.getSinger());
							myMusicListModel4.setAlbum(musicListModel.getAlbum());
							myMusicListModel4.setDuration(musicListModel.getDuration());
							myMusicListModel4.setSize(musicListModel.getSize());
							myMusicListModel4.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel4.setUrl(musicListModel.getUrl());
							myMusicListModel4.setCollected(false);
							myMusicListModel4.setLrcName("");
							myMusicListModel4.setLrcUrl("");
							myMusicListModel4s.add(myMusicListModel4);
						}
					}
				}
				if (myMusicListModel4s.size() > 0) {
					DataSupport.saveAll(myMusicListModel4s);// 扫描到的列表重新插入表MyMusicListModel4中
					return true;

				}
				break;
			case 5:
				List<MyMusicListModel5> myMusicListModel5s = new ArrayList<MyMusicListModel5>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel5> myMusicListModel5DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel5.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel5DB.size() == 0) {
							MyMusicListModel5 myMusicListModel5 = new MyMusicListModel5();
							myMusicListModel5.setSongName(musicListModel.getSongName());
							myMusicListModel5.setSinger(musicListModel.getSinger());
							myMusicListModel5.setAlbum(musicListModel.getAlbum());
							myMusicListModel5.setDuration(musicListModel.getDuration());
							myMusicListModel5.setSize(musicListModel.getSize());
							myMusicListModel5.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel5.setUrl(musicListModel.getUrl());
							myMusicListModel5.setCollected(false);
							myMusicListModel5.setLrcName("");
							myMusicListModel5.setLrcUrl("");
							myMusicListModel5s.add(myMusicListModel5);
						}
					}
				}
				if (myMusicListModel5s.size() > 0) {
					DataSupport.saveAll(myMusicListModel5s);// 扫描到的列表重新插入表MyMusicListModel5中
					return true;

				}
				break;
			case 6:
				List<MyMusicListModel6> myMusicListModel6s = new ArrayList<MyMusicListModel6>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel6> myMusicListModel6DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel6.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel6DB.size() == 0) {
							MyMusicListModel6 myMusicListModel6 = new MyMusicListModel6();
							myMusicListModel6.setSongName(musicListModel.getSongName());
							myMusicListModel6.setSinger(musicListModel.getSinger());
							myMusicListModel6.setAlbum(musicListModel.getAlbum());
							myMusicListModel6.setDuration(musicListModel.getDuration());
							myMusicListModel6.setSize(musicListModel.getSize());
							myMusicListModel6.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel6.setUrl(musicListModel.getUrl());
							myMusicListModel6.setCollected(false);
							myMusicListModel6.setLrcName("");
							myMusicListModel6.setLrcUrl("");
							myMusicListModel6s.add(myMusicListModel6);
						}
					}
				}
				if (myMusicListModel6s.size() > 0) {
					DataSupport.saveAll(myMusicListModel6s);// 扫描到的列表重新插入表MyMusicListModel6中
					return true;

				}
				break;
			case 7:
				List<MyMusicListModel7> myMusicListModel7s = new ArrayList<MyMusicListModel7>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel7> myMusicListModel7DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel7.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel7DB.size() == 0) {
							MyMusicListModel7 myMusicListModel7 = new MyMusicListModel7();
							myMusicListModel7.setSongName(musicListModel.getSongName());
							myMusicListModel7.setSinger(musicListModel.getSinger());
							myMusicListModel7.setAlbum(musicListModel.getAlbum());
							myMusicListModel7.setDuration(musicListModel.getDuration());
							myMusicListModel7.setSize(musicListModel.getSize());
							myMusicListModel7.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel7.setUrl(musicListModel.getUrl());
							myMusicListModel7.setCollected(false);
							myMusicListModel7.setLrcName("");
							myMusicListModel7.setLrcUrl("");
							myMusicListModel7s.add(myMusicListModel7);
						}
					}
				}
				if (myMusicListModel7s.size() > 0) {
					DataSupport.saveAll(myMusicListModel7s);// 扫描到的列表重新插入表MyMusicListModel7中
					return true;

				}
				break;
			case 8:
				List<MyMusicListModel8> myMusicListModel8s = new ArrayList<MyMusicListModel8>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel8> myMusicListModel8DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel8.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel8DB.size() == 0) {
							MyMusicListModel8 myMusicListModel8 = new MyMusicListModel8();
							myMusicListModel8.setSongName(musicListModel.getSongName());
							myMusicListModel8.setSinger(musicListModel.getSinger());
							myMusicListModel8.setAlbum(musicListModel.getAlbum());
							myMusicListModel8.setDuration(musicListModel.getDuration());
							myMusicListModel8.setSize(musicListModel.getSize());
							myMusicListModel8.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel8.setUrl(musicListModel.getUrl());
							myMusicListModel8.setCollected(false);
							myMusicListModel8.setLrcName("");
							myMusicListModel8.setLrcUrl("");
							myMusicListModel8s.add(myMusicListModel8);
						}
					}
				}
				if (myMusicListModel8s.size() > 0) {
					DataSupport.saveAll(myMusicListModel8s);// 扫描到的列表重新插入表MyMusicListModel8中
					return true;

				}
				break;
			case 9:
				List<MyMusicListModel9> myMusicListModel9s = new ArrayList<MyMusicListModel9>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel9> myMusicListModel9DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel9.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel9DB.size() == 0) {
							MyMusicListModel9 myMusicListModel9 = new MyMusicListModel9();
							myMusicListModel9.setSongName(musicListModel.getSongName());
							myMusicListModel9.setSinger(musicListModel.getSinger());
							myMusicListModel9.setAlbum(musicListModel.getAlbum());
							myMusicListModel9.setDuration(musicListModel.getDuration());
							myMusicListModel9.setSize(musicListModel.getSize());
							myMusicListModel9.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel9.setUrl(musicListModel.getUrl());
							myMusicListModel9.setCollected(false);
							myMusicListModel9.setLrcName("");
							myMusicListModel9.setLrcUrl("");
							myMusicListModel9s.add(myMusicListModel9);
						}
					}
				}
				if (myMusicListModel9s.size() > 0) {
					DataSupport.saveAll(myMusicListModel9s);// 扫描到的列表重新插入表MyMusicListModel9中
					return true;

				}
				break;

			}
			break;
		case 1:
			switch (index % 10) {

			case 0:
				List<MyMusicListModel0> myMusicListModel0s = new ArrayList<MyMusicListModel0>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel0> myMusicListModel0DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel0.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel0DB.size() == 0) {
							MyMusicListModel0 myMusicListModel0 = new MyMusicListModel0();
							myMusicListModel0.setSongName(musicListModel.getSongName());
							myMusicListModel0.setSinger(musicListModel.getSinger());
							myMusicListModel0.setAlbum(musicListModel.getAlbum());
							myMusicListModel0.setDuration(musicListModel.getDuration());
							myMusicListModel0.setSize(musicListModel.getSize());
							myMusicListModel0.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel0.setUrl(musicListModel.getUrl());
							myMusicListModel0.setCollected(false);
							myMusicListModel0.setLrcName("");
							myMusicListModel0.setLrcUrl("");
							myMusicListModel0s.add(myMusicListModel0);
						}
					}
				}
				if (myMusicListModel0s.size() > 0) {
					DataSupport.saveAll(myMusicListModel0s);// 扫描到的列表重新插入表MyMusicListModel0中
					return true;

				}
				break;
			case 11:
				List<MyMusicListModel11> myMusicListModel11s = new ArrayList<MyMusicListModel11>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel11> myMusicListModel11DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel11.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel11DB.size() == 0) {
							MyMusicListModel11 myMusicListModel11 = new MyMusicListModel11();
							myMusicListModel11.setSongName(musicListModel.getSongName());
							myMusicListModel11.setSinger(musicListModel.getSinger());
							myMusicListModel11.setAlbum(musicListModel.getAlbum());
							myMusicListModel11.setDuration(musicListModel.getDuration());
							myMusicListModel11.setSize(musicListModel.getSize());
							myMusicListModel11.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel11.setUrl(musicListModel.getUrl());
							myMusicListModel11.setCollected(false);
							myMusicListModel11.setLrcName("");
							myMusicListModel11.setLrcUrl("");
							myMusicListModel11s.add(myMusicListModel11);
						}
					}
				}
				if (myMusicListModel11s.size() > 0) {
					DataSupport.saveAll(myMusicListModel11s);// 扫描到的列表重新插入表MyMusicListModel11中
					return true;

				}
				break;
			case 12:
				List<MyMusicListModel12> myMusicListModel12s = new ArrayList<MyMusicListModel12>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel12> myMusicListModel12DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel12.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel12DB.size() == 0) {
							MyMusicListModel12 myMusicListModel12 = new MyMusicListModel12();
							myMusicListModel12.setSongName(musicListModel.getSongName());
							myMusicListModel12.setSinger(musicListModel.getSinger());
							myMusicListModel12.setAlbum(musicListModel.getAlbum());
							myMusicListModel12.setDuration(musicListModel.getDuration());
							myMusicListModel12.setSize(musicListModel.getSize());
							myMusicListModel12.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel12.setUrl(musicListModel.getUrl());
							myMusicListModel12.setCollected(false);
							myMusicListModel12.setLrcName("");
							myMusicListModel12.setLrcUrl("");
							myMusicListModel12s.add(myMusicListModel12);
						}
					}
				}
				if (myMusicListModel12s.size() > 0) {
					DataSupport.saveAll(myMusicListModel12s);// 扫描到的列表重新插入表MyMusicListModel12中
					return true;

				}
				break;
			case 13:
				List<MyMusicListModel13> myMusicListModel13s = new ArrayList<MyMusicListModel13>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel13> myMusicListModel13DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel13.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel13DB.size() == 0) {
							MyMusicListModel13 myMusicListModel13 = new MyMusicListModel13();
							myMusicListModel13.setSongName(musicListModel.getSongName());
							myMusicListModel13.setSinger(musicListModel.getSinger());
							myMusicListModel13.setAlbum(musicListModel.getAlbum());
							myMusicListModel13.setDuration(musicListModel.getDuration());
							myMusicListModel13.setSize(musicListModel.getSize());
							myMusicListModel13.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel13.setUrl(musicListModel.getUrl());
							myMusicListModel13.setCollected(false);
							myMusicListModel13.setLrcName("");
							myMusicListModel13.setLrcUrl("");
							myMusicListModel13s.add(myMusicListModel13);
						}
					}
				}
				if (myMusicListModel13s.size() > 0) {
					DataSupport.saveAll(myMusicListModel13s);// 扫描到的列表重新插入表MyMusicListModel13中
					return true;

				}
				break;
			case 14:
				List<MyMusicListModel14> myMusicListModel14s = new ArrayList<MyMusicListModel14>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel14> myMusicListModel14DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel14.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel14DB.size() == 0) {
							MyMusicListModel14 myMusicListModel14 = new MyMusicListModel14();
							myMusicListModel14.setSongName(musicListModel.getSongName());
							myMusicListModel14.setSinger(musicListModel.getSinger());
							myMusicListModel14.setAlbum(musicListModel.getAlbum());
							myMusicListModel14.setDuration(musicListModel.getDuration());
							myMusicListModel14.setSize(musicListModel.getSize());
							myMusicListModel14.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel14.setUrl(musicListModel.getUrl());
							myMusicListModel14.setCollected(false);
							myMusicListModel14.setLrcName("");
							myMusicListModel14.setLrcUrl("");
							myMusicListModel14s.add(myMusicListModel14);
						}
					}
				}
				if (myMusicListModel14s.size() > 0) {
					DataSupport.saveAll(myMusicListModel14s);// 扫描到的列表重新插入表MyMusicListModel14中
					return true;

				}
				break;
			case 15:
				List<MyMusicListModel15> myMusicListModel15s = new ArrayList<MyMusicListModel15>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel15> myMusicListModel15DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel15.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel15DB.size() == 0) {
							MyMusicListModel15 myMusicListModel15 = new MyMusicListModel15();
							myMusicListModel15.setSongName(musicListModel.getSongName());
							myMusicListModel15.setSinger(musicListModel.getSinger());
							myMusicListModel15.setAlbum(musicListModel.getAlbum());
							myMusicListModel15.setDuration(musicListModel.getDuration());
							myMusicListModel15.setSize(musicListModel.getSize());
							myMusicListModel15.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel15.setUrl(musicListModel.getUrl());
							myMusicListModel15.setCollected(false);
							myMusicListModel15.setLrcName("");
							myMusicListModel15.setLrcUrl("");
							myMusicListModel15s.add(myMusicListModel15);
						}
					}
				}
				if (myMusicListModel15s.size() > 0) {
					DataSupport.saveAll(myMusicListModel15s);// 扫描到的列表重新插入表MyMusicListModel15中
					return true;

				}
				break;
			case 16:
				List<MyMusicListModel16> myMusicListModel16s = new ArrayList<MyMusicListModel16>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel16> myMusicListModel16DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel16.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel16DB.size() == 0) {
							MyMusicListModel16 myMusicListModel16 = new MyMusicListModel16();
							myMusicListModel16.setSongName(musicListModel.getSongName());
							myMusicListModel16.setSinger(musicListModel.getSinger());
							myMusicListModel16.setAlbum(musicListModel.getAlbum());
							myMusicListModel16.setDuration(musicListModel.getDuration());
							myMusicListModel16.setSize(musicListModel.getSize());
							myMusicListModel16.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel16.setUrl(musicListModel.getUrl());
							myMusicListModel16.setCollected(false);
							myMusicListModel16.setLrcName("");
							myMusicListModel16.setLrcUrl("");
							myMusicListModel16s.add(myMusicListModel16);
						}
					}
				}
				if (myMusicListModel16s.size() > 0) {
					DataSupport.saveAll(myMusicListModel16s);// 扫描到的列表重新插入表MyMusicListModel16中
					return true;

				}
				break;
			case 17:
				List<MyMusicListModel17> myMusicListModel17s = new ArrayList<MyMusicListModel17>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel17> myMusicListModel17DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel17.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel17DB.size() == 0) {
							MyMusicListModel17 myMusicListModel17 = new MyMusicListModel17();
							myMusicListModel17.setSongName(musicListModel.getSongName());
							myMusicListModel17.setSinger(musicListModel.getSinger());
							myMusicListModel17.setAlbum(musicListModel.getAlbum());
							myMusicListModel17.setDuration(musicListModel.getDuration());
							myMusicListModel17.setSize(musicListModel.getSize());
							myMusicListModel17.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel17.setUrl(musicListModel.getUrl());
							myMusicListModel17.setCollected(false);
							myMusicListModel17.setLrcName("");
							myMusicListModel17.setLrcUrl("");
							myMusicListModel17s.add(myMusicListModel17);
						}
					}
				}
				if (myMusicListModel17s.size() > 0) {
					DataSupport.saveAll(myMusicListModel17s);// 扫描到的列表重新插入表MyMusicListModel17中
					return true;

				}
				break;
			case 18:
				List<MyMusicListModel18> myMusicListModel18s = new ArrayList<MyMusicListModel18>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel18> myMusicListModel18DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel18.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel18DB.size() == 0) {
							MyMusicListModel18 myMusicListModel18 = new MyMusicListModel18();
							myMusicListModel18.setSongName(musicListModel.getSongName());
							myMusicListModel18.setSinger(musicListModel.getSinger());
							myMusicListModel18.setAlbum(musicListModel.getAlbum());
							myMusicListModel18.setDuration(musicListModel.getDuration());
							myMusicListModel18.setSize(musicListModel.getSize());
							myMusicListModel18.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel18.setUrl(musicListModel.getUrl());
							myMusicListModel18.setCollected(false);
							myMusicListModel18.setLrcName("");
							myMusicListModel18.setLrcUrl("");
							myMusicListModel18s.add(myMusicListModel18);
						}
					}
				}
				if (myMusicListModel18s.size() > 0) {
					DataSupport.saveAll(myMusicListModel18s);// 扫描到的列表重新插入表MyMusicListModel18中
					return true;

				}
				break;
			case 19:
				List<MyMusicListModel19> myMusicListModel19s = new ArrayList<MyMusicListModel19>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel19> myMusicListModel19DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel19.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel19DB.size() == 0) {
							MyMusicListModel19 myMusicListModel19 = new MyMusicListModel19();
							myMusicListModel19.setSongName(musicListModel.getSongName());
							myMusicListModel19.setSinger(musicListModel.getSinger());
							myMusicListModel19.setAlbum(musicListModel.getAlbum());
							myMusicListModel19.setDuration(musicListModel.getDuration());
							myMusicListModel19.setSize(musicListModel.getSize());
							myMusicListModel19.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel19.setUrl(musicListModel.getUrl());
							myMusicListModel19.setCollected(false);
							myMusicListModel19.setLrcName("");
							myMusicListModel19.setLrcUrl("");
							myMusicListModel19s.add(myMusicListModel19);
						}
					}
				}
				if (myMusicListModel19s.size() > 0) {
					DataSupport.saveAll(myMusicListModel19s);// 扫描到的列表重新插入表MyMusicListModel19中
					return true;

				}
				break;

			}
			break;
		}
		return false;

	}

	/**
	 * 音乐排序-清空重排
	 * 
	 * @param index
	 * @param musicListModels
	 * @return
	 */
	public static boolean insertAllMusicListIntoDataBase(int index, List<MusicListModel> musicListModels) {
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				List<MyMusicListModel0> myMusicListModel0s = new ArrayList<MyMusicListModel0>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel0 myMusicListModel0 = new MyMusicListModel0();
					myMusicListModel0.setSongName(musicListModel.getSongName());
					myMusicListModel0.setSinger(musicListModel.getSinger());
					myMusicListModel0.setAlbum(musicListModel.getAlbum());
					myMusicListModel0.setDuration(musicListModel.getDuration());
					myMusicListModel0.setSize(musicListModel.getSize());
					myMusicListModel0.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel0.setUrl(musicListModel.getUrl());
					myMusicListModel0.setCollected(false);
					myMusicListModel0.setLrcName("");
					myMusicListModel0.setLrcUrl("");
					myMusicListModel0s.add(myMusicListModel0);
				}
				if (myMusicListModel0s.size() > 0) {
					DataSupport.saveAll(myMusicListModel0s);// 扫描到的列表重新插入表MyMusicListModel0中
					return true;
				}
				break;
			case 1:
				List<MyMusicListModel1> myMusicListModel1s = new ArrayList<MyMusicListModel1>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel1 myMusicListModel1 = new MyMusicListModel1();
					myMusicListModel1.setSongName(musicListModel.getSongName());
					myMusicListModel1.setSinger(musicListModel.getSinger());
					myMusicListModel1.setAlbum(musicListModel.getAlbum());
					myMusicListModel1.setDuration(musicListModel.getDuration());
					myMusicListModel1.setSize(musicListModel.getSize());
					myMusicListModel1.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel1.setUrl(musicListModel.getUrl());
					myMusicListModel1.setCollected(false);
					myMusicListModel1.setLrcName("");
					myMusicListModel1.setLrcUrl("");
					myMusicListModel1s.add(myMusicListModel1);
				}
				if (myMusicListModel1s.size() > 0) {
					DataSupport.saveAll(myMusicListModel1s);// 扫描到的列表重新插入表MyMusicListModel1中
					return true;

				}
				break;
			case 2:
				List<MyMusicListModel2> myMusicListModel2s = new ArrayList<MyMusicListModel2>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel2 myMusicListModel2 = new MyMusicListModel2();
					myMusicListModel2.setSongName(musicListModel.getSongName());
					myMusicListModel2.setSinger(musicListModel.getSinger());
					myMusicListModel2.setAlbum(musicListModel.getAlbum());
					myMusicListModel2.setDuration(musicListModel.getDuration());
					myMusicListModel2.setSize(musicListModel.getSize());
					myMusicListModel2.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel2.setUrl(musicListModel.getUrl());
					myMusicListModel2.setCollected(false);
					myMusicListModel2.setLrcName("");
					myMusicListModel2.setLrcUrl("");
					myMusicListModel2s.add(myMusicListModel2);
				}
				if (myMusicListModel2s.size() > 0) {
					DataSupport.saveAll(myMusicListModel2s);// 扫描到的列表重新插入表MyMusicListModel2中
					return true;

				}
				break;
			case 3:
				List<MyMusicListModel3> myMusicListModel3s = new ArrayList<MyMusicListModel3>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel3 myMusicListModel3 = new MyMusicListModel3();
					myMusicListModel3.setSongName(musicListModel.getSongName());
					myMusicListModel3.setSinger(musicListModel.getSinger());
					myMusicListModel3.setAlbum(musicListModel.getAlbum());
					myMusicListModel3.setDuration(musicListModel.getDuration());
					myMusicListModel3.setSize(musicListModel.getSize());
					myMusicListModel3.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel3.setUrl(musicListModel.getUrl());
					myMusicListModel3.setCollected(false);
					myMusicListModel3.setLrcName("");
					myMusicListModel3.setLrcUrl("");
					myMusicListModel3s.add(myMusicListModel3);
				}
				if (myMusicListModel3s.size() > 0) {
					DataSupport.saveAll(myMusicListModel3s);// 扫描到的列表重新插入表MyMusicListModel3中
					return true;

				}
				break;
			case 4:
				List<MyMusicListModel4> myMusicListModel4s = new ArrayList<MyMusicListModel4>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel4 myMusicListModel4 = new MyMusicListModel4();
					myMusicListModel4.setSongName(musicListModel.getSongName());
					myMusicListModel4.setSinger(musicListModel.getSinger());
					myMusicListModel4.setAlbum(musicListModel.getAlbum());
					myMusicListModel4.setDuration(musicListModel.getDuration());
					myMusicListModel4.setSize(musicListModel.getSize());
					myMusicListModel4.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel4.setUrl(musicListModel.getUrl());
					myMusicListModel4.setCollected(false);
					myMusicListModel4.setLrcName("");
					myMusicListModel4.setLrcUrl("");
					myMusicListModel4s.add(myMusicListModel4);
				}
				if (myMusicListModel4s.size() > 0) {
					DataSupport.saveAll(myMusicListModel4s);// 扫描到的列表重新插入表MyMusicListModel4中
					return true;

				}
				break;
			case 5:
				List<MyMusicListModel5> myMusicListModel5s = new ArrayList<MyMusicListModel5>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel5 myMusicListModel5 = new MyMusicListModel5();
					myMusicListModel5.setSongName(musicListModel.getSongName());
					myMusicListModel5.setSinger(musicListModel.getSinger());
					myMusicListModel5.setAlbum(musicListModel.getAlbum());
					myMusicListModel5.setDuration(musicListModel.getDuration());
					myMusicListModel5.setSize(musicListModel.getSize());
					myMusicListModel5.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel5.setUrl(musicListModel.getUrl());
					myMusicListModel5.setCollected(false);
					myMusicListModel5.setLrcName("");
					myMusicListModel5.setLrcUrl("");
					myMusicListModel5s.add(myMusicListModel5);
				}
				if (myMusicListModel5s.size() > 0) {
					DataSupport.saveAll(myMusicListModel5s);// 扫描到的列表重新插入表MyMusicListModel5中
					return true;

				}
				break;
			case 6:
				List<MyMusicListModel6> myMusicListModel6s = new ArrayList<MyMusicListModel6>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel6 myMusicListModel6 = new MyMusicListModel6();
					myMusicListModel6.setSongName(musicListModel.getSongName());
					myMusicListModel6.setSinger(musicListModel.getSinger());
					myMusicListModel6.setAlbum(musicListModel.getAlbum());
					myMusicListModel6.setDuration(musicListModel.getDuration());
					myMusicListModel6.setSize(musicListModel.getSize());
					myMusicListModel6.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel6.setUrl(musicListModel.getUrl());
					myMusicListModel6.setCollected(false);
					myMusicListModel6.setLrcName("");
					myMusicListModel6.setLrcUrl("");
					myMusicListModel6s.add(myMusicListModel6);
				}
				if (myMusicListModel6s.size() > 0) {
					DataSupport.saveAll(myMusicListModel6s);// 扫描到的列表重新插入表MyMusicListModel6中
					return true;

				}
				break;
			case 7:
				List<MyMusicListModel7> myMusicListModel7s = new ArrayList<MyMusicListModel7>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel7 myMusicListModel7 = new MyMusicListModel7();
					myMusicListModel7.setSongName(musicListModel.getSongName());
					myMusicListModel7.setSinger(musicListModel.getSinger());
					myMusicListModel7.setAlbum(musicListModel.getAlbum());
					myMusicListModel7.setDuration(musicListModel.getDuration());
					myMusicListModel7.setSize(musicListModel.getSize());
					myMusicListModel7.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel7.setUrl(musicListModel.getUrl());
					myMusicListModel7.setCollected(false);
					myMusicListModel7.setLrcName("");
					myMusicListModel7.setLrcUrl("");
					myMusicListModel7s.add(myMusicListModel7);
				}
				if (myMusicListModel7s.size() > 0) {
					DataSupport.saveAll(myMusicListModel7s);// 扫描到的列表重新插入表MyMusicListModel7中
					return true;

				}
				break;
			case 8:
				List<MyMusicListModel8> myMusicListModel8s = new ArrayList<MyMusicListModel8>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel8 myMusicListModel8 = new MyMusicListModel8();
					myMusicListModel8.setSongName(musicListModel.getSongName());
					myMusicListModel8.setSinger(musicListModel.getSinger());
					myMusicListModel8.setAlbum(musicListModel.getAlbum());
					myMusicListModel8.setDuration(musicListModel.getDuration());
					myMusicListModel8.setSize(musicListModel.getSize());
					myMusicListModel8.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel8.setUrl(musicListModel.getUrl());
					myMusicListModel8.setCollected(false);
					myMusicListModel8.setLrcName("");
					myMusicListModel8.setLrcUrl("");
					myMusicListModel8s.add(myMusicListModel8);
				}
				if (myMusicListModel8s.size() > 0) {
					DataSupport.saveAll(myMusicListModel8s);// 扫描到的列表重新插入表MyMusicListModel8中
					return true;

				}
				break;
			case 9:
				List<MyMusicListModel9> myMusicListModel9s = new ArrayList<MyMusicListModel9>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel9 myMusicListModel9 = new MyMusicListModel9();
					myMusicListModel9.setSongName(musicListModel.getSongName());
					myMusicListModel9.setSinger(musicListModel.getSinger());
					myMusicListModel9.setAlbum(musicListModel.getAlbum());
					myMusicListModel9.setDuration(musicListModel.getDuration());
					myMusicListModel9.setSize(musicListModel.getSize());
					myMusicListModel9.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel9.setUrl(musicListModel.getUrl());
					myMusicListModel9.setCollected(false);
					myMusicListModel9.setLrcName("");
					myMusicListModel9.setLrcUrl("");
					myMusicListModel9s.add(myMusicListModel9);
				}
				if (myMusicListModel9s.size() > 0) {
					DataSupport.saveAll(myMusicListModel9s);// 扫描到的列表重新插入表MyMusicListModel9中
					return true;

				}
				break;

			}
			break;
		case 1:
			switch (index % 10) {

			case 0:
				List<MyMusicListModel0> myMusicListModel0s = new ArrayList<MyMusicListModel0>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel0 myMusicListModel0 = new MyMusicListModel0();
					myMusicListModel0.setSongName(musicListModel.getSongName());
					myMusicListModel0.setSinger(musicListModel.getSinger());
					myMusicListModel0.setAlbum(musicListModel.getAlbum());
					myMusicListModel0.setDuration(musicListModel.getDuration());
					myMusicListModel0.setSize(musicListModel.getSize());
					myMusicListModel0.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel0.setUrl(musicListModel.getUrl());
					myMusicListModel0.setCollected(false);
					myMusicListModel0.setLrcName("");
					myMusicListModel0.setLrcUrl("");
					myMusicListModel0s.add(myMusicListModel0);
				}
				if (myMusicListModel0s.size() > 0) {
					DataSupport.saveAll(myMusicListModel0s);// 扫描到的列表重新插入表MyMusicListModel0中
					return true;

				}
				break;
			case 11:
				List<MyMusicListModel11> myMusicListModel11s = new ArrayList<MyMusicListModel11>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel11 myMusicListModel11 = new MyMusicListModel11();
					myMusicListModel11.setSongName(musicListModel.getSongName());
					myMusicListModel11.setSinger(musicListModel.getSinger());
					myMusicListModel11.setAlbum(musicListModel.getAlbum());
					myMusicListModel11.setDuration(musicListModel.getDuration());
					myMusicListModel11.setSize(musicListModel.getSize());
					myMusicListModel11.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel11.setUrl(musicListModel.getUrl());
					myMusicListModel11.setCollected(false);
					myMusicListModel11.setLrcName("");
					myMusicListModel11.setLrcUrl("");
					myMusicListModel11s.add(myMusicListModel11);
				}
				if (myMusicListModel11s.size() > 0) {
					DataSupport.saveAll(myMusicListModel11s);// 扫描到的列表重新插入表MyMusicListModel11中
					return true;

				}
				break;
			case 12:
				List<MyMusicListModel12> myMusicListModel12s = new ArrayList<MyMusicListModel12>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel12 myMusicListModel12 = new MyMusicListModel12();
					myMusicListModel12.setSongName(musicListModel.getSongName());
					myMusicListModel12.setSinger(musicListModel.getSinger());
					myMusicListModel12.setAlbum(musicListModel.getAlbum());
					myMusicListModel12.setDuration(musicListModel.getDuration());
					myMusicListModel12.setSize(musicListModel.getSize());
					myMusicListModel12.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel12.setUrl(musicListModel.getUrl());
					myMusicListModel12.setCollected(false);
					myMusicListModel12.setLrcName("");
					myMusicListModel12.setLrcUrl("");
					myMusicListModel12s.add(myMusicListModel12);
				}
				if (myMusicListModel12s.size() > 0) {
					DataSupport.saveAll(myMusicListModel12s);// 扫描到的列表重新插入表MyMusicListModel12中
					return true;

				}
				break;
			case 13:
				List<MyMusicListModel13> myMusicListModel13s = new ArrayList<MyMusicListModel13>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel13 myMusicListModel13 = new MyMusicListModel13();
					myMusicListModel13.setSongName(musicListModel.getSongName());
					myMusicListModel13.setSinger(musicListModel.getSinger());
					myMusicListModel13.setAlbum(musicListModel.getAlbum());
					myMusicListModel13.setDuration(musicListModel.getDuration());
					myMusicListModel13.setSize(musicListModel.getSize());
					myMusicListModel13.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel13.setUrl(musicListModel.getUrl());
					myMusicListModel13.setCollected(false);
					myMusicListModel13.setLrcName("");
					myMusicListModel13.setLrcUrl("");
					myMusicListModel13s.add(myMusicListModel13);
				}
				if (myMusicListModel13s.size() > 0) {
					DataSupport.saveAll(myMusicListModel13s);// 扫描到的列表重新插入表MyMusicListModel13中
					return true;

				}
				break;
			case 14:
				List<MyMusicListModel14> myMusicListModel14s = new ArrayList<MyMusicListModel14>();
				if (musicListModels != null) {
					for (MusicListModel musicListModel : musicListModels) {
						List<MyMusicListModel14> myMusicListModel14DB = DataSupport
								.where("url = ?", musicListModel.getUrl()).find(MyMusicListModel14.class);
						// 查询结果中没有当前音乐，则添加，否则略过
						if (myMusicListModel14DB.size() == 0) {
							MyMusicListModel14 myMusicListModel14 = new MyMusicListModel14();
							myMusicListModel14.setSongName(musicListModel.getSongName());
							myMusicListModel14.setSinger(musicListModel.getSinger());
							myMusicListModel14.setAlbum(musicListModel.getAlbum());
							myMusicListModel14.setDuration(musicListModel.getDuration());
							myMusicListModel14.setSize(musicListModel.getSize());
							myMusicListModel14.setFilePostfix(musicListModel.getFilePostfix());
							myMusicListModel14.setUrl(musicListModel.getUrl());
							myMusicListModel14.setCollected(false);
							myMusicListModel14.setLrcName("");
							myMusicListModel14.setLrcUrl("");
							myMusicListModel14s.add(myMusicListModel14);
						}
					}
				}
				if (myMusicListModel14s.size() > 0) {
					DataSupport.saveAll(myMusicListModel14s);// 扫描到的列表重新插入表MyMusicListModel14中
					return true;

				}
				break;
			case 15:
				List<MyMusicListModel15> myMusicListModel15s = new ArrayList<MyMusicListModel15>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel15 myMusicListModel15 = new MyMusicListModel15();
					myMusicListModel15.setSongName(musicListModel.getSongName());
					myMusicListModel15.setSinger(musicListModel.getSinger());
					myMusicListModel15.setAlbum(musicListModel.getAlbum());
					myMusicListModel15.setDuration(musicListModel.getDuration());
					myMusicListModel15.setSize(musicListModel.getSize());
					myMusicListModel15.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel15.setUrl(musicListModel.getUrl());
					myMusicListModel15.setCollected(false);
					myMusicListModel15.setLrcName("");
					myMusicListModel15.setLrcUrl("");
					myMusicListModel15s.add(myMusicListModel15);
				}
				if (myMusicListModel15s.size() > 0) {
					DataSupport.saveAll(myMusicListModel15s);// 扫描到的列表重新插入表MyMusicListModel15中
					return true;

				}
				break;
			case 16:
				List<MyMusicListModel16> myMusicListModel16s = new ArrayList<MyMusicListModel16>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel16 myMusicListModel16 = new MyMusicListModel16();
					myMusicListModel16.setSongName(musicListModel.getSongName());
					myMusicListModel16.setSinger(musicListModel.getSinger());
					myMusicListModel16.setAlbum(musicListModel.getAlbum());
					myMusicListModel16.setDuration(musicListModel.getDuration());
					myMusicListModel16.setSize(musicListModel.getSize());
					myMusicListModel16.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel16.setUrl(musicListModel.getUrl());
					myMusicListModel16.setCollected(false);
					myMusicListModel16.setLrcName("");
					myMusicListModel16.setLrcUrl("");
					myMusicListModel16s.add(myMusicListModel16);
				}
				if (myMusicListModel16s.size() > 0) {
					DataSupport.saveAll(myMusicListModel16s);// 扫描到的列表重新插入表MyMusicListModel16中
					return true;

				}
				break;
			case 17:
				List<MyMusicListModel17> myMusicListModel17s = new ArrayList<MyMusicListModel17>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel17 myMusicListModel17 = new MyMusicListModel17();
					myMusicListModel17.setSongName(musicListModel.getSongName());
					myMusicListModel17.setSinger(musicListModel.getSinger());
					myMusicListModel17.setAlbum(musicListModel.getAlbum());
					myMusicListModel17.setDuration(musicListModel.getDuration());
					myMusicListModel17.setSize(musicListModel.getSize());
					myMusicListModel17.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel17.setUrl(musicListModel.getUrl());
					myMusicListModel17.setCollected(false);
					myMusicListModel17.setLrcName("");
					myMusicListModel17.setLrcUrl("");
					myMusicListModel17s.add(myMusicListModel17);
				}
				if (myMusicListModel17s.size() > 0) {
					DataSupport.saveAll(myMusicListModel17s);// 扫描到的列表重新插入表MyMusicListModel17中
					return true;

				}
				break;
			case 18:
				List<MyMusicListModel18> myMusicListModel18s = new ArrayList<MyMusicListModel18>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel18 myMusicListModel18 = new MyMusicListModel18();
					myMusicListModel18.setSongName(musicListModel.getSongName());
					myMusicListModel18.setSinger(musicListModel.getSinger());
					myMusicListModel18.setAlbum(musicListModel.getAlbum());
					myMusicListModel18.setDuration(musicListModel.getDuration());
					myMusicListModel18.setSize(musicListModel.getSize());
					myMusicListModel18.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel18.setUrl(musicListModel.getUrl());
					myMusicListModel18.setCollected(false);
					myMusicListModel18.setLrcName("");
					myMusicListModel18.setLrcUrl("");
					myMusicListModel18s.add(myMusicListModel18);
				}
				if (myMusicListModel18s.size() > 0) {
					DataSupport.saveAll(myMusicListModel18s);// 扫描到的列表重新插入表MyMusicListModel18中
					return true;

				}
				break;
			case 19:
				List<MyMusicListModel19> myMusicListModel19s = new ArrayList<MyMusicListModel19>();
				for (MusicListModel musicListModel : musicListModels) {
					MyMusicListModel19 myMusicListModel19 = new MyMusicListModel19();
					myMusicListModel19.setSongName(musicListModel.getSongName());
					myMusicListModel19.setSinger(musicListModel.getSinger());
					myMusicListModel19.setAlbum(musicListModel.getAlbum());
					myMusicListModel19.setDuration(musicListModel.getDuration());
					myMusicListModel19.setSize(musicListModel.getSize());
					myMusicListModel19.setFilePostfix(musicListModel.getFilePostfix());
					myMusicListModel19.setUrl(musicListModel.getUrl());
					myMusicListModel19.setCollected(false);
					myMusicListModel19.setLrcName("");
					myMusicListModel19.setLrcUrl("");
					myMusicListModel19s.add(myMusicListModel19);
				}
				if (myMusicListModel19s.size() > 0) {
					DataSupport.saveAll(myMusicListModel19s);// 扫描到的列表重新插入表MyMusicListModel19中
					return true;

				}
				break;

			}
			break;
		}
		return false;

	}
}
