package com.app.fastprint.utills;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.app.fastprint.R;

public class UtilsAlertDialog {

    public static Dialog ShowDialog(Context context) {
        Dialog dialog = null;
        try {
            dialog = new Dialog(context, R.style.Theme_Dialog);
            dialog.setContentView(R.layout.layout_custom_dialog);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;

    }

    public static Dialog progressDialog(Context context) {
        Dialog dialog = null;
        try {
            dialog = new Dialog(context, R.style.Theme_Dialog);
            dialog.setContentView(R.layout.layout_custom_dialog);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }
}
