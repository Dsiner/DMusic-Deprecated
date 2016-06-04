package com.d.dmusic.fragment;

import android.support.v4.app.Fragment;

public class FragmentModel {
	
	public String mTitle = "";
	public Fragment mFragment;
	public boolean isFirstPage;
	
	public FragmentModel(String title, Fragment fg,boolean isfirst){
		mTitle = title;
		mFragment = fg;
		isFirstPage = isfirst;
	}

}
