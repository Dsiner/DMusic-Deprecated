package com.d.dmusic.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import com.d.dmusic.R;

/**
 * 自定义圆形Progress进度提示对话框
 *
 * @author D
 */
public class CircleDialog {
    private Context context;
    private DisplayMetrics dm;
    private View dialogView;
    private Dialog dialog;

    /*
     *
     * @param context
     *
     * @param dm
     */
    public CircleDialog(Context context/* , DisplayMetrics dm */) {
        this.context = context;
        // this.dm = dm;
        initDialog();
    }

    public void show() {
        if (dialog != null)
            dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

    }

    private void initDialog() {
        dialog = new Dialog(context, R.style.MyDialogStyleProgress);
        initDialogView();
        dialog.setContentView(dialogView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
    }

    private void initDialogView() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        dialogView = inflater.inflate(R.layout.progress, null);

    }
}
