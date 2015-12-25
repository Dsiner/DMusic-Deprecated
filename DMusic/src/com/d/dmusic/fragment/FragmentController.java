package com.d.dmusic.fragment;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class FragmentController {

	private static FragmentController instance;
	private Context mContext;

	private Map<String, FragmentModel> mFragmentModelMaps = new HashMap<String, FragmentModel>();

	private FragmentController(Context context) {
		mContext = context;
	}

	public static synchronized FragmentController getInstance(Context context) {
		if (instance == null) {
			instance = new FragmentController(context);
		}
		return instance;
	}

	public FragmentModel getMainFragmentModel() {
		FragmentModel fragmentModel = mFragmentModelMaps.get(FragmentBuilder.MAIN_FRAGMENT);
		if (fragmentModel == null) {
			fragmentModel = FragmentBuilder.getMainFragmentModel();
			mFragmentModelMaps.put(FragmentBuilder.MAIN_FRAGMENT, fragmentModel);
		}
		return fragmentModel;
	}

	public static class FragmentBuilder {
		public static final String MAIN_FRAGMENT = "MAIN_FRAGMENT";

		public static FragmentModel getMainFragmentModel() {
			MainFragment fragment = new MainFragment();
			FragmentModel fragmentModel = new FragmentModel("Ö÷½çÃæ", fragment, true);
			return fragmentModel;
		}

	}

}
