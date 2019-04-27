package com.splendid.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.splendid.commondialog.MyApplication;
import com.splendid.commondialog.R;

public class CommonUtil {

    private static final int DIALOG_DIVIDE_VALUE = 12;

    private static final int DIALOG_MULTIPLY_VALUE = 30;

    private static final int DIALOG_DIFFENERCE_VALUE = 100;

    public static DisplayMetrics getDisplayMetrics() {
        WindowManager windowManager = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static int getDialogHeightByMessageContent(String message) {
        if (TextUtils.isEmpty(message)) {
            throw new IllegalArgumentException(MyApplication.getContext().getResources().getString(R.string.dialog_message_txt_unset));
        }
        DisplayMetrics metrics = getDisplayMetrics();
        return (int) (metrics.density * (message.length() / DIALOG_DIVIDE_VALUE * DIALOG_MULTIPLY_VALUE + DIALOG_DIFFENERCE_VALUE));
    }
}
