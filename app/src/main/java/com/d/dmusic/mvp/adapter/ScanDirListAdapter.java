package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.d.dmusic.R;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.fileutils.FileInfo;
import com.d.dmusic.fileutils.FileUtils;

import java.util.List;

public class ScanDirListAdapter extends CommonAdapter<FileInfo> {
    private TextView tvCurrentDir;
    private FileUtils fileUtils;
    private String rootPath;
    private String currentPath;

    public ScanDirListAdapter(Context context, int layoutId, String rootPath, TextView tvCurrentDir) {
        super(context, layoutId, null);
        this.fileUtils = new FileUtils(rootPath);
        mDatas = fileUtils.getCurrentDirLists(rootPath);
        this.rootPath = rootPath;
        this.currentPath = rootPath;
        this.tvCurrentDir = tvCurrentDir;
        this.tvCurrentDir.setText(rootPath);
    }

    public List<FileInfo> getCurrentDirLists() {
        return mDatas;
    }

    public boolean ReturnParentDir() {
        // TODO Auto-generated method stub
        Log.v("return--开始路径", currentPath);
        if (currentPath.length() > rootPath.length()) {
            currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
            mDatas = fileUtils.getCurrentDirLists(currentPath);
            tvCurrentDir.setText(currentPath);
            Log.v("return--返回路径", currentPath);
            notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, FileInfo fileInfo, int position) {
        setItemData(holder, fileInfo, position);
    }

    private void setItemData(final ViewHolder holder, final FileInfo data, final int position) {
        holder.setText(R.id.tv_dir_name, data.getFileName());
        holder.setText(R.id.tv_music_counts, /* currentDirLists.get(position).getMusicCounts() */ "?" + "首");
        holder.setChecked(R.id.ctv_isselected, data.isSelected());
        holder.setOnClickListener(R.id.ll_dir_sub, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log.v("onClick--开始路径", currentPath);
                String str = data.getFileAbsolutePath().toString();
                if (!fileUtils.isEndDir(str)) {
                    currentPath = str.toString();
                    // pathQueue.add(currentPath);
                    mDatas = fileUtils.getCurrentDirLists(currentPath);
                    tvCurrentDir.setText(currentPath);
                    notifyDataSetChanged();
                } else {
                    if (((CheckedTextView) holder.getView(R.id.ctv_isselected)).isChecked()) {
                        holder.setChecked(R.id.ctv_isselected, false);
                        data.setSelected(false);
                    } else {
                        holder.setChecked(R.id.ctv_isselected, true);
                        data.setSelected(true);
                    }
                }
                // Log.v("onClick--结束路径", currentPath);
            }
        });
        holder.setOnClickListener(R.id.ctv_isselected, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckedTextView) holder.getView(R.id.ctv_isselected)).isChecked()) {
                    holder.setChecked(R.id.ctv_isselected, false);
                    data.setSelected(false);
                } else {
                    holder.setChecked(R.id.ctv_isselected, true);
                    data.setSelected(true);
                }
            }
        });
    }
}
