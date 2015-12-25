package com.d.dmusic.fragment;

import java.util.List;

import org.litepal.crud.DataSupport;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuStateChangeListener;
import com.d.dmusic.MainActivity;
import com.d.dmusic.R;
import com.d.dmusic.activity.PlayActivity;
import com.d.dmusic.adapter.CustomListSwipeAdapter;
import com.d.dmusic.adapter.models.MusicListModel;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.database.models.LocalAllMusicListModel;
import com.d.dmusic.database.models.MyMusicListModel1;
import com.d.dmusic.database.models.MyMusicListModel2;
import com.d.dmusic.database.models.MyMusicListModel3;
import com.d.dmusic.database.models.MyMusicListModel4;
import com.d.dmusic.database.models.MyMusicListModel5;
import com.d.dmusic.database.models.MyMusicListModel6;
import com.d.dmusic.database.models.MyMusicListModel7;
import com.d.dmusic.database.models.MyMusicListModel8;
import com.d.dmusic.database.models.MyMusicListModel9;
import com.d.dmusic.dialog.MyDialogTitleTop;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * ������ҳ
 * 
 * */
public class MainFragment extends Fragment implements OnClickListener {

	private TextView tvTitleTitle;// ����
	private ImageView ivTitleSearch;// ����
	private ImageView ivTitleMainMore;// ����
	public static FragmentManager fragmentManager;
	private LinearLayout loacl_music;// ��������
	private LinearLayout colletion_music;// �ҵ��ղ�
	private SwipeMenuListView lvCustomList;// �Զ��������б�
	private View viewRoot;
	// adapter
	private CustomListSwipeAdapter customListSwipeAdapter;// �Զ��������б�������
	private static List<CustomMusicListModel> customMusicListModels;

	// ��������dialog
	private MyDialogTitleTop dialog;
	private View dialogView;
	private LinearLayout llTitleMainMoreBlank;
	private View vTitleMainMoreBlank;
	private LinearLayout llTitleMainMoreAddList;
	// �½������б�dialog
	private AlertDialog newListDialog;
	private EditText etNewListDialogInputName;
	private TextView tvNewListDialogCancel;
	private TextView tvNewListDialogOk;
	public static boolean isNeedReload;// �Ƿ���Ҫ��ȡ��������Դ
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				customMusicListModels = getCustomMusicListsFromDataBase();
				customListSwipeAdapter.notifyDataSetChanged();
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isNeedReload = true;
		fragmentManager = getActivity().getSupportFragmentManager();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		viewRoot = inflater.inflate(R.layout.fragment_main, container, false);
		tvTitleTitle = (TextView) viewRoot.findViewById(R.id.tv_title_title);
		ivTitleSearch = (ImageView) viewRoot.findViewById(R.id.iv_title_search);
		ivTitleMainMore = (ImageView) viewRoot.findViewById(R.id.iv_title_more);
		loacl_music = (LinearLayout) viewRoot.findViewById(R.id.ll_local_music);
		colletion_music = (LinearLayout) viewRoot.findViewById(R.id.ll_collection_music);
		tvTitleTitle.setText("��ҳ");
		lvCustomList = (SwipeMenuListView) viewRoot.findViewById(R.id.lV_custom_list);
		// DataSupport.deleteAll(CustomMusicListModel.class);
		customListSwipeAdapter = new CustomListSwipeAdapter(getActivity(), getCustomMusicListsFromDataBase(),
				fragmentManager);
		isNeedReload = false;// �Զ����б������ѻ�ȡ
		lvCustomList.setAdapter(customListSwipeAdapter);
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// TODO Auto-generated method stub
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
				// set item background
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
				// set item width
				openItem.setWidth(dp2px(90));
				// set item title
				openItem.setTitle("�ö�");
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				deleteItem.setTitle("ɾ��");
				deleteItem.setTitleSize(18);
				deleteItem.setTitleColor(Color.WHITE);
				// set a icon
				// deleteItem.setIcon(R.drawable.ic_launcher);
				// add to menu
				menu.addMenuItem(deleteItem);

			}
		};
		lvCustomList.setMenuCreator(creator);
		lvCustomList.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
				// TODO Auto-generated method stub
				CustomMusicListModel customMusicListModel = customMusicListModels.get(position);
				switch (index) {
				case 0:
					customListSwipeAdapter.stick(position);
					// Toast.makeText(getActivity(),
					// customMusicListModel.getSeq() + "", 1000).show();
					break;
				case 1:
					customListSwipeAdapter.delete(position);
					// Toast.makeText(getActivity(),
					// customMusicListModel.getId() + "", 1000).show();
					break;
				}
				return false;
			}
		});
		lvCustomList.setOnMenuStateChangeListener(new OnMenuStateChangeListener() {

			@Override
			public void onMenuOpen(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onMenuClose(int arg0) {
				// TODO Auto-generated method stub
				// Log.v("onMenuClose:", arg0 + "");

			}
		});
		lvCustomList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		loacl_music.setOnClickListener(this);
		colletion_music.setOnClickListener(this);
		ivTitleMainMore.setOnClickListener(this);

		return viewRoot;

	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (isNeedReload) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					handler.sendEmptyMessage(1);
				}
			}).start();
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// ��������
		case R.id.ll_local_music:
			fragmentManager.beginTransaction().replace(R.id.main_framelayout, new LocalMusicFragment())
					.addToBackStack(null).commit();
			break;
		// �ҵ��ղ�
		case R.id.ll_collection_music:
			fragmentManager.beginTransaction().replace(R.id.main_framelayout, new CollectionMusicFragment())
					.addToBackStack(null).commit();
			break;
		case R.id.iv_title_more:
			initDialog();
			dialog.show();
			break;
		// ������dialog
		case R.id.ll_title_main_more_add_list:
			dialog.dismiss();
			showNewListDialog();
			break;
		case R.id.ll_title_main_more_blank:
			dialog.dismiss();
			break;
		case R.id.v_title_main_more_blank:
			dialog.dismiss();
			break;
		// �½������б�dialog
		case R.id.tv_new_list_dialog_cancel:
			dismissNewListDialog();
			break;
		case R.id.tv_new_list_dialog_ok:
			if (insertNewListToDataBase(etNewListDialogInputName.getText().toString())) {
				customListSwipeAdapter.refresh();
				// customMusicListModels.size()- 1
				lvCustomList.setSelection(ListView.FOCUS_DOWN);
				dismissNewListDialog();
			}

			break;
		}

	}

	/**
	 * �����µĸ����б�--����δ��DBTable
	 */
	public int getUnusedTable(List<CustomMusicListModel> customMusicListModels) {
		int a = 0;
		for (CustomMusicListModel customMusicListModel : customMusicListModels) {
			if (customMusicListModel.getPointerOfDBTable() != a)
				return a;
			a++;
		}
		return a;
	}

	/**
	 * �����µĸ����б�
	 */
	private boolean insertNewListToDataBase(String listName) {
		List<CustomMusicListModel> cmlmsExist = DataSupport.where("listName = ?", listName)
				.find(CustomMusicListModel.class);
		if (cmlmsExist.size() != 0) {
			Toast.makeText(getActivity(), "���б��Ѵ��ڣ�", Toast.LENGTH_SHORT).show();
			return false;
		}

		List<CustomMusicListModel> cmlms = DataSupport.select("pointerOfDBTable").order("pointerOfDBTable asc")
				.find(CustomMusicListModel.class);
		int counts = cmlms.size();
		if (counts < 20) {
			int seq = DataSupport.max(CustomMusicListModel.class, "seq", int.class);
			int pointerOfDBTable = getUnusedTable(cmlms);
			CustomMusicListModel customMusicListModel = new CustomMusicListModel();
			customMusicListModel.setListName(listName);
			customMusicListModel.setSongCounts(0);
			customMusicListModel.setSeq(seq + 1);
			customMusicListModel.setPointerOfDBTable(pointerOfDBTable);
			customMusicListModel.save();

		} else {
			Toast.makeText(getActivity(), "�б�������", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;

	}

	/*
	 * ����customMusicListModels
	 */
	public static List<CustomMusicListModel> getCustomMusicListsFromDataBase() {
		return customMusicListModels = DataSupport.order("seq asc").find(CustomMusicListModel.class);
	}

	/**
	 * ��ʾ�Զ����½������б�Ի���
	 */
	private void showNewListDialog() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View view = inflater.inflate(R.layout.new_list_dialog, null);
		etNewListDialogInputName = (EditText) view.findViewById(R.id.et_new_list_dialog_input_name);
		tvNewListDialogCancel = (TextView) view.findViewById(R.id.tv_new_list_dialog_cancel);
		tvNewListDialogOk = (TextView) view.findViewById(R.id.tv_new_list_dialog_ok);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		tvNewListDialogCancel.setOnClickListener(this);
		tvNewListDialogOk.setOnClickListener(this);
		tvNewListDialogOk.setClickable(false);
		etNewListDialogInputName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				Log.v("et:", s.toString());
				if (s.toString().length() > 0 && s.toString().length() < 40) {
					tvNewListDialogOk.setClickable(true);
					tvNewListDialogOk.setTextColor(Color.parseColor("#FFA523"));
				} else {
					tvNewListDialogOk.setClickable(false);
					tvNewListDialogOk.setTextColor(Color.parseColor("#77FFA523"));
				}
			}
		});
		// builder.setTitle("�Զ���Ի���");//���ñ���
		// builder.setIcon(R.drawable.ic_launcher);//����ͼ��
		builder.setView(view);
		newListDialog = builder.create();// ��ȡdialog
		newListDialog.show();// ��ʾ�Ի���
	}

	private void dismissNewListDialog() {
		if (newListDialog != null) {
			if (newListDialog.isShowing()) {
				newListDialog.dismiss();
			}
		}
	}

	private void initDialog() {
		dialog = new MyDialogTitleTop(getActivity());
		// if (dialogView == null) {
		// initDialogView();
		// }
		initDialogView();
		dialog.setContentView(dialogView);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(true);

		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.TOP);
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); // ��ȡ�Ի���ǰ�Ĳ���ֵ

		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int windowsWidth = dm.widthPixels;
		int windowsHeight = dm.heightPixels;

		p.width = (int) (windowsWidth * 1); // �������Ϊ��Ļ��1
		p.height = (int) (windowsHeight * 1); // �߶�����Ϊ��Ļ��0.618
		dialogWindow.setAttributes(p);
	}

	private void initDialogView() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		dialogView = inflater.inflate(R.layout.title_main_more_dialog, null);
		llTitleMainMoreBlank = (LinearLayout) dialogView.findViewById(R.id.ll_title_main_more_blank);
		vTitleMainMoreBlank = dialogView.findViewById(R.id.v_title_main_more_blank);
		llTitleMainMoreAddList = (LinearLayout) dialogView.findViewById(R.id.ll_title_main_more_add_list);

		llTitleMainMoreBlank.setOnClickListener(this);
		vTitleMainMoreBlank.setOnClickListener(this);
		llTitleMainMoreAddList.setOnClickListener(this);

	}
}
