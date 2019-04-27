package com.splendid.util;

import android.app.Activity;

import com.splendid.listener.DialogConfirmListener;
import com.splendid.view.CommonTipsDialog;

public class DialogUtil {


    public static void showSelectionDialog(Activity activity, String title, String message,
                                                  String cancelTxt, String confirmTxt, DialogConfirmListener confirmListener) {
        if (activity != null && !activity.isFinishing()) {
            CommonTipsDialog dialog = (CommonTipsDialog) CommonTipsDialog.newBuilder()
                    .setTitle(title)
                    .setMessageContent(message)
                    .setCancelTxt(cancelTxt)
                    .setConfirmTxt(confirmTxt)
                    .setDialogConfirmListener(confirmListener)
                    .setCanceledOnTouchOutside(false)
                    .buildDefaultInstance();
            dialog.show(activity);
        }
    }

    public static void showSingleSelectionDialog(Activity activity,
                                                  String title, String message, String confirmTxt,
                                                  DialogConfirmListener confirmListener) {
        if (activity != null && !activity.isFinishing()) {
            CommonTipsDialog dialog = (CommonTipsDialog) CommonTipsDialog.newBuilder()
                    .setTitle(title)
                    .setMessageContent(message)
                    .setConfirmTxt(confirmTxt)
                    .setDialogConfirmListener(confirmListener)
                    .setCanceledOnTouchOutside(false)
                    .buildDefaultInstance();
            dialog.show(activity);
        }
    }
}
