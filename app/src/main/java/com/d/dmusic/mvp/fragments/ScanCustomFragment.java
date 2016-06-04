package com.d.dmusic.mvp.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.dialog.BlurBGDialog;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.mvp.adapter.ScanDirListAdapter;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.utils.TaskManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * 自定义扫描页
 * 
 * */
public class ScanCustomFragment extends BaseFragment<MvpBasePresenter> implements MvpView {

    @Bind(R.id.ll_return_parent_dir)
    LinearLayout llReturnParentDir;
    @Bind(R.id.ll_scan_now)
    LinearLayout llScanNow;
    @Bind(R.id.tv_current_dir)
    TextView tvCurrentDir;
    @Bind(R.id.lv_scan_list)
    ListView lvScanList;

    private List<FileInfo> currentDirLists;
    private String rootPath;
    public static ScanDirListAdapter scanDirListAdapter;
    private BlurBGDialog blurBGDialog;// 进度提示dialog

    Handler handler = new MyHandler(this);

    static class MyHandler extends Handler {
        WeakReference<ScanCustomFragment> mFragment;

        MyHandler(ScanCustomFragment fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ScanCustomFragment theFragment = mFragment.get();
            if (theFragment == null || theFragment.getActivity() == null || theFragment.getActivity().isFinishing()) {
                return;
            }
            if (msg.what == 1) {
                ScanActivity.isScanOver = true;// 扫描过，返回时需要刷新数据
                theFragment.blurBGDialog.dismiss();
                theFragment.getActivity().finish();
            }
        }
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_scan_dir;
    }

    @Override
    public MvpBasePresenter getPresenter() {
        return new MvpBasePresenter(this.getActivity().getApplicationContext());
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void init() {
        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        ScanActivity.currentFragment = 2;
//        LitePalApplication.initialize(getActivity().getApplicationContext());
//        SQLiteDatabase db = Connector.getDatabase();
        scanDirListAdapter = new ScanDirListAdapter(getActivity(), R.layout.activity_scan_dir_sub, rootPath, tvCurrentDir);
        lvScanList.setAdapter(scanDirListAdapter);
    }


    @OnClick({R.id.ll_return_parent_dir, R.id.ll_scan_now})
    public void OnClickLister(View view) {
        switch (view.getId()) {
            case R.id.ll_return_parent_dir:
                if (scanDirListAdapter.ReturnParentDir()) {
                }
                break;
            case R.id.ll_scan_now:
                blurBGDialog = new BlurBGDialog(getActivity());
                blurBGDialog.show();
                TaskManager.getIns().executeTask(new Runnable() {
                    @Override
                    public void run() {
                        List<String> stringList = new ArrayList<String>();
                        currentDirLists = scanDirListAdapter.getCurrentDirLists();
                        for (FileInfo fileInfo : currentDirLists) {
                            if (fileInfo.isSelected())
                                stringList.add(fileInfo.getFileAbsolutePath());
                        }
                        if (stringList.size() > 0) {
                            DataBaseUtils.insertMusicListIntoDataBase(getActivity(), ScanActivity.index, stringList);
                            getActivity().finish();
                        }
                        if (handler != null) {
                            handler.sendEmptyMessage(1);
                        }
                    }
                });
                break;
        }
    }

}
