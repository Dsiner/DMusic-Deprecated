package com.d.dmusic.mvp.fragments;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.d.dmusic.R;
import com.d.dmusic.activity.ScanActivity;
import com.d.dmusic.database.DataBaseUtils;
import com.d.dmusic.dialog.BlurBGDialog;
import com.d.dmusic.mvpcomment.BaseFragment;
import com.d.dmusic.mvpcomment.MvpBasePresenter;
import com.d.dmusic.mvpcomment.MvpView;
import com.d.dmusic.utils.TaskManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/*
 * 扫描Fragment
 * 
 * */
public class ScanMainFragment extends BaseFragment<MvpBasePresenter> implements MvpView {

    @Bind(R.id.btn_scan_main_full_scan)
    Button btnScanMainFullScan;
    @Bind(R.id.btn_scan_main_custom_scan)
    Button btnScanMainCustomScan;
    // 进度提示dialog
    private BlurBGDialog blurBGDialog;

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                ScanActivity.isScanOver = true;// 扫描过，返回时需要刷新数据
                blurBGDialog.dismiss();
                getActivity().finish();
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_scan_main;
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
        ScanActivity.currentFragment = 1;
    }

    @OnClick({R.id.btn_scan_main_full_scan, R.id.btn_scan_main_custom_scan})
    public void OnClickLister(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_main_full_scan:
                blurBGDialog = new BlurBGDialog(getActivity());
                blurBGDialog.show();
                TaskManager.getIns().executeTask(new Runnable() {
                    @Override
                    public void run() {
                        List<String> stringList = new ArrayList<String>();
                        stringList.add(Environment.getExternalStorageDirectory().getAbsolutePath());
                        DataBaseUtils.insertMusicListIntoDataBase(getActivity(), ScanActivity.index, stringList);
                        if (handler != null) {
                            handler.sendEmptyMessage(1);
                        }
                    }
                });
                break;
            case R.id.btn_scan_main_custom_scan:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.scan_framelayout, new ScanCustomFragment())
                        .addToBackStack(null).commit();
                break;
        }
    }

}
