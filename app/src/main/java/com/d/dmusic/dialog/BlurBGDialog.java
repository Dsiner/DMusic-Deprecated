package com.d.dmusic.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.d.dmusic.R;

/**
 * 自定义圆形Progress进度提示对话框-背景模糊
 *
 * @author D
 */
public class BlurBGDialog {
    private Context context;
    // private DisplayMetrics dm;
    private View dialogView;
    private Dialog dialog;

    /*
     *
     * @param context
     *
     * @param dm
     */
    public BlurBGDialog(
            Context context/* , DisplayMetrics dm */) {
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

    public boolean isShow() {
        if (dialog != null)
            if (dialog.isShowing())
                return true;
        return false;
    }

    private void initDialog() {
        dialog = new Dialog(context, R.style.dialog_style/*MyDialogStyleProgressWithBackgroundBlur*/);
        initDialogView();
        dialog.setContentView(dialogView);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    private void initDialogView() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        dialogView = inflater.inflate(R.layout.progress_with_background_blur, null);

    }
}
