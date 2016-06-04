package com.d.dmusic.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;

import com.d.dmusic.R;
import com.d.dmusic.commenadapter.ViewHolder;
import com.d.dmusic.commenadapter.abslistview.CommonAdapter;
import com.d.dmusic.database.models.CustomMusicListModel;

import java.util.List;

/**
 * 添加到列表Dialog适配器
 *
 * @author D
 */
public class AddToListAdapter extends CommonAdapter<CustomMusicListModel> {
    private int lastSelectedPosition;// 最后一次被选中的位置
    private CheckedTextView lastCTVDialogAddToListIsselected;// 上一次操作的CheckedTextView

    public AddToListAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        this.lastSelectedPosition = -1;// 初始化为-1，未选中任何
    }

    /**
     * 返回选中的位置
     *
     * @return
     */
    public int getLastSelectedPostion() {
        return lastSelectedPosition;
    }

    @Override
    public void convert(ViewHolder holder, CustomMusicListModel customMusicListModel, int position) {
        setItemData(holder, customMusicListModel, position);
    }

    private void setItemData(final ViewHolder holder, final CustomMusicListModel data, final int position) {
        holder.setText(R.id.tv_dialog_add_to_list_sub_list_name, data.getListName());
        holder.setOnClickListener(R.id.ll_dialog_add_to_list_sub_isselected, new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastSelectedPosition != position) {
                    if (lastCTVDialogAddToListIsselected != null)
                        lastCTVDialogAddToListIsselected.setChecked(false);
                }
                if (((CheckedTextView) holder.getView(R.id.ctv_dialog_add_to_list_sub_isselected)).isChecked()) {
                    lastSelectedPosition = -1;// 不指向任何位置
                    holder.setChecked(R.id.ctv_dialog_add_to_list_sub_isselected, false);
                    lastCTVDialogAddToListIsselected = null;// 不指向任何对象
                } else {
                    lastSelectedPosition = position;// 指向当前位置
                    holder.setChecked(R.id.ctv_dialog_add_to_list_sub_isselected, true);
                    lastCTVDialogAddToListIsselected = holder.getView(R.id.ctv_dialog_add_to_list_sub_isselected);// 引用当前操作对象

                }
            }
        });
    }
}
