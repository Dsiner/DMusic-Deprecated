package com.d.dmusic.utils;

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

import android.R.integer;
import android.content.ContentValues;

/**
 * 歌曲列表-排序工具类
 * 
 * @author D
 *
 */
public class ListHandler {
	private int index;
	private List<Integer> waitDeleteIdQueue;// 滑动删除队列，放弃操作前不可撤销
	private List<Integer> deleteIdQueue;// 最终确定删除队列
	private List<MusicListModel> musicListModels;

	public ListHandler(int index, List<MusicListModel> musicListModels) {

		this.index = index;
		this.waitDeleteIdQueue = new ArrayList<Integer>();
		this.deleteIdQueue = new ArrayList<Integer>();
		this.musicListModels = musicListModels;

	}

	public void preAddDeleteQueueId(int id) {
		Integer i = id;
		waitDeleteIdQueue.add(i);
	}

	/**
	 * 提交
	 */
	public void submit() {
		delete();
		reorder();
	}

	/**
	 * Step1: 提交操作-从数据库中删除等待队列
	 */
	public void delete() {
		// deleteIdQueue.clear();
		deleteIdQueue.addAll(waitDeleteIdQueue);
		deleteByIdQueue(index);
		// waitDeleteIdQueue.clear();// 清空队列
	}

	/**
	 * Step2: 提交操作-重新排序、更新customSeq字段
	 */
	public void reorder() {
		reorderByIndex(index);
	}

	public void deleteByIdQueue(int index) {
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel0.class, mInteger.intValue());
				}
				break;
			case 1:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel1.class, mInteger.intValue());
				}
				break;
			case 2:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel2.class, mInteger.intValue());
				}
				break;
			case 3:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel3.class, mInteger.intValue());
				}
				break;
			case 4:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel4.class, mInteger.intValue());
				}
				break;
			case 5:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel5.class, mInteger.intValue());
				}
				break;
			case 6:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel6.class, mInteger.intValue());
				}
				break;
			case 7:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel7.class, mInteger.intValue());
				}
				break;
			case 8:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel8.class, mInteger.intValue());
				}
				break;
			case 9:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel9.class, mInteger.intValue());
				}
				break;
			}

			break;
		case 1:
			switch (index % 10) {
			case 0:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel10.class, mInteger.intValue());
				}
				break;
			case 1:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel11.class, mInteger.intValue());
				}
				break;
			case 2:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel12.class, mInteger.intValue());
				}
				break;
			case 3:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel13.class, mInteger.intValue());
				}
				break;
			case 4:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel14.class, mInteger.intValue());
				}
				break;
			case 5:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel15.class, mInteger.intValue());
				}
				break;
			case 6:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel16.class, mInteger.intValue());
				}
				break;
			case 7:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel17.class, mInteger.intValue());
				}
				break;
			case 8:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel18.class, mInteger.intValue());
				}
				break;
			case 9:
				for (Integer mInteger : deleteIdQueue) {
					DataSupport.delete(MyMusicListModel19.class, mInteger.intValue());
				}
				break;
			}
			break;
		}
	}

	private void reorderByIndex(int index) {
		switch (index / 10) {
		case 0:
			switch (index % 10) {
			case 0:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel0.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 1:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel1.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 2:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel2.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 3:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel3.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 4:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel4.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 5:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel5.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 6:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel6.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 7:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel7.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 8:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel8.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 9:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel9.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			}

			break;
		case 1:
			switch (index % 10) {
			case 0:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel10.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 1:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel11.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 2:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel12.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 3:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel13.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 4:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel14.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 5:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel15.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 6:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel16.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 7:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel17.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 8:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel18.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			case 9:
				if (musicListModels != null) {
					int i = 0;
					for (MusicListModel musicListModel : musicListModels) {
						ContentValues values = new ContentValues();
						values.put("customseq", i);
						DataSupport.update(MyMusicListModel19.class, values, musicListModel.getId());
						i++;
					}
				}
				break;
			}
			break;
		}
	}
}
