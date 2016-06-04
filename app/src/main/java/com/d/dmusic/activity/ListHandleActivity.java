package com.d.dmusic.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.database.models.CustomMusicListModel;
import com.d.dmusic.dialog.AddToListDialog;
import com.d.dmusic.dialog.BlurBGDialog;
import com.d.dmusic.global.Contains;
import com.d.dmusic.itemtouchhelper.RecyclerListAdapter;
import com.d.dmusic.itemtouchhelper.RecyclerListAdapter.OnRecyclerViewItemClickListener;
import com.d.dmusic.itemtouchhelper.RecyclerListAdapter.OnStartDragListener;
import com.d.dmusic.itemtouchhelper.SimpleItemTouchHelperCallback;
import com.d.dmusic.models.MusicListModel;
import com.d.dmusic.mvp.fragments.CustomMusicFragment;
import com.d.dmusic.utils.ListHandler;
import com.d.dmusic.utils.StatusBarCompat;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐列表管理、排序
 *
 * @author D
 */
public class ListHandleActivity extends BaseFinalActivity
        implements OnStartDragListener, OnRecyclerViewItemClickListener, OnClickListener {

    private int id;// 歌曲列表Table主键
    private int index;// 列表标识

    private ImageView ivListHandleTitleBack;// 返回
    private TextView tvListHandleTitleTitle;// 标题
    private TextView ivListHandleTitleSelectAll;// 全选
    private LinearLayout llListHandleAddToList;// 添加到列表
    private LinearLayout llListHandleDelete;// 删除
    private LinearLayout llListHandleSubmit;// 提交操作
    private RecyclerView recyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private LinearLayoutManager layoutManager;

    // 适配器
    private List<MusicListModel> musicListModels;
    private RecyclerListAdapter recyclerListAdapter;
    // 歌曲列表管理类
    private ListHandler listHandler;
    // 进度提示dialog
    private BlurBGDialog blurBGDialog;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                CustomMusicFragment.isNeedReload = true;// 返回时，需要重新加载数据源
                blurBGDialog.dismiss();
                finish();
            }
            if (msg.what == 2) {
                blurBGDialog.dismiss();
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_handle);
        StatusBarCompat.compat(ListHandleActivity.this, 0xffFD8D22);//沉浸式状态栏

        id = getIntent().getIntExtra("id", -1);// 数据库中没有id主键为-1的数据
        index = getIntent().getIntExtra("index", -1);// 拿到列表标识
        musicListModels = new ArrayList<MusicListModel>();
        if (Contains.musicListModels != null)
            musicListModels = Contains.musicListModels;// 指向全局静态变量
        listHandler = new ListHandler(index, musicListModels);
        ivListHandleTitleBack = (ImageView) findViewById(R.id.iv_list_handle_title_back);
        tvListHandleTitleTitle = (TextView) findViewById(R.id.tv_list_handle_title_title);
        ivListHandleTitleSelectAll = (TextView) findViewById(R.id.tv_list_handle_title_select_all);
        llListHandleAddToList = (LinearLayout) findViewById(R.id.ll_list_handle_add_to_list);
        llListHandleDelete = (LinearLayout) findViewById(R.id.ll_list_handle_delete);
        llListHandleSubmit = (LinearLayout) findViewById(R.id.ll_list_handle_submit);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerListAdapter = new RecyclerListAdapter(this, musicListModels, listHandler);
        recyclerListAdapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerListAdapter);
        // 创建线性布局管理器（默认是垂直方向）
        layoutManager = new LinearLayoutManager(this);
        // 为RecyclerView指定布局管理对象
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(recyclerListAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        ivListHandleTitleBack.setOnClickListener(this);
        ivListHandleTitleSelectAll.setOnClickListener(this);
        ivListHandleTitleSelectAll.setTag(false);
        llListHandleAddToList.setOnClickListener(this);
        llListHandleDelete.setOnClickListener(this);
        llListHandleSubmit.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onStartDrag(ViewHolder viewHolder) {
        // TODO Auto-generated method stub
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Contains.musicListModels = null;// 指向空,等待内存释放
    }

    @Override
    public void onItemClick(View view, int position) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.iv_list_handle_title_back:
                finish();
                break;
            case R.id.tv_list_handle_title_select_all:

                if ((Boolean) ivListHandleTitleSelectAll.getTag()) {
                    for (MusicListModel musicListModel : musicListModels) {
                        if (musicListModel.isSelected()) {
                            musicListModel.setSelected(false);
                        } else {
                            musicListModel.setSelected(true);
                        }
                        recyclerListAdapter.notifyDataSetChanged();
                    }
                    ivListHandleTitleSelectAll.setTag(false);
                    ivListHandleTitleSelectAll.setText("全选");

                } else {
                    for (MusicListModel musicListModel : musicListModels) {
                        musicListModel.setSelected(true);
                        recyclerListAdapter.notifyDataSetChanged();
                    }
                    ivListHandleTitleSelectAll.setTag(true);
                    ivListHandleTitleSelectAll.setText("反选");

                }

                break;
            // 添加到列表
            case R.id.ll_list_handle_add_to_list:
                List<MusicListModel> mlm = new ArrayList<MusicListModel>();
                for (MusicListModel musicListModel : musicListModels) {
                    if (musicListModel.isSelected()) {
                        mlm.add(musicListModel);
                    }
                }
                new AddToListDialog(this, index, mlm).show();
                break;
            case R.id.ll_list_handle_delete:
                blurBGDialog = new BlurBGDialog(this);
                blurBGDialog.show();
                for (int i = musicListModels.size(); i > 0; i--) {
                    if (musicListModels.get(i - 1).isSelected()) {
                        listHandler.preAddDeleteQueueId(musicListModels.get(i - 1).getId());
                        musicListModels.remove(i - 1);
                    }
                }
                recyclerListAdapter.notifyDataSetChanged();
                handler.sendEmptyMessage(2);
                break;
            case R.id.ll_list_handle_submit:
                blurBGDialog = new BlurBGDialog(this);
                blurBGDialog.show();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        listHandler.submit();
                        ContentValues valuesCustom = new ContentValues();
                        valuesCustom.put("sortby", 3);
                        DataSupport.update(CustomMusicListModel.class, valuesCustom, id);// 更新当前歌曲列表排序方式
                        CustomMusicFragment.sortBy = 3;// 以自定义方式排序
                        handler.sendEmptyMessage(1);
                    }
                }).start();

                break;
        }

    }

}
