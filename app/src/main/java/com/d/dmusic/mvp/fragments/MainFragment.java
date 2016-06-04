package com.d.dmusic.mvp.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuStateChangeListener;
import com.d.dmusic.R;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.dialog.TopMenuDialog;
import com.d.dmusic.mvp.adapter.CustomListSwipeAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/*
 * 音乐首页
 * 
 * */
public class MainFragment extends Fragment implements OnClickListener {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;// 返回
    @Bind(R.id.tv_title_title)
    TextView tvTitleTitle;// 标题
    @Bind(R.id.iv_title_search)
    ImageView ivTitleSearch;// 搜索
    @Bind(R.id.iv_title_more)
    ImageView ivTitleMainMore;// 更多
    @Bind(R.id.ll_local_music)
    LinearLayout loacl_music;// 本地音乐
    @Bind(R.id.ll_collection_music)
    LinearLayout colletion_music;// 我的收藏
    @Bind(R.id.lV_custom_list)
    SwipeMenuListView lvCustomList;// 自定义音乐列表

    private View viewRoot;
    public static FragmentManager fragmentManager;
    // adapter
    private CustomListSwipeAdapter customListSwipeAdapter;// 自定义音乐列表适配器
    private static List<CustomMusicListModel> customMusicListModels;

    // 标题栏，dialog
    private TopMenuDialog dialog;
    private View dialogView;
    private LinearLayout llTitleMainMoreBlank;
    private View vTitleMainMoreBlank;
    private LinearLayout llTitleMainMoreAddList;
    // 新建歌曲列表dialog
    private AlertDialog newListDialog;
    private EditText etNewListDialogInputName;
    private TextView tvNewListDialogCancel;
    private TextView tvNewListDialogOk;
    public static boolean isNeedReload;// 是否需要获取计算数据源
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                customMusicListModels = getCustomMusicListsFromDataBase();
                customListSwipeAdapter.notifyDataSetChanged();
            }
        }

        ;
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
        ButterKnife.bind(this, viewRoot);
        ivTitleBack.setVisibility(View.GONE);
        tvTitleTitle.setText("首页");
        customListSwipeAdapter = new CustomListSwipeAdapter(getActivity(), getCustomMusicListsFromDataBase(),
                fragmentManager);
        isNeedReload = false;// 自定义列表数据已获取
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
                openItem.setTitle("置顶");
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
                deleteItem.setTitle("删除");
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
            // 本地音乐
            case R.id.ll_local_music:
                fragmentManager.beginTransaction().replace(R.id.main_framelayout, new LocalMusicFragment())
                        .addToBackStack(null).commit();
                break;
            // 我的收藏
            case R.id.ll_collection_music:
                fragmentManager.beginTransaction().replace(R.id.main_framelayout, new CollectionMusicFragment())
                        .addToBackStack(null).commit();
                break;
            case R.id.iv_title_more:
                initDialog();
                dialog.show();
                break;
            // 标题栏dialog
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
            // 新建歌曲列表dialog
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
     * 插入新的歌曲列表--查找未用DBTable
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
     * 插入新的歌曲列表
     */
    private boolean insertNewListToDataBase(String listName) {
        List<CustomMusicListModel> cmlmsExist = DataSupport.where("listName = ?", listName)
                .find(CustomMusicListModel.class);
        if (cmlmsExist.size() != 0) {
            Toast.makeText(getActivity(), "该列表已存在！", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity(), "列表已满！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /*
     * 更新customMusicListModels
     */
    public static List<CustomMusicListModel> getCustomMusicListsFromDataBase() {
        return customMusicListModels = DataSupport.order("seq asc").find(CustomMusicListModel.class);
    }

    /**
     * 显示自定义新建歌曲列表对话框
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
        builder.setView(view);
        newListDialog = builder.create();// 获取dialog
        newListDialog.show();// 显示对话框
    }

    private void dismissNewListDialog() {
        if (newListDialog != null) {
            if (newListDialog.isShowing()) {
                newListDialog.dismiss();
            }
        }
    }

    private void initDialog() {
        dialog = new TopMenuDialog(getActivity());
        initDialogView();
        dialog.setContentView(dialogView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.TOP);
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int windowsWidth = dm.widthPixels;
        int windowsHeight = dm.heightPixels;

        p.width = (int) (windowsWidth * 1); // 宽度设置为屏幕的1
        p.height = (int) (windowsHeight * 1); // 高度设置为屏幕的0.618
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

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
