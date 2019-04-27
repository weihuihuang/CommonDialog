package com.splendid.model;

import android.text.TextUtils;
import android.view.Gravity;

import com.splendid.commondialog.R;
import com.splendid.listener.DialogCancelListener;
import com.splendid.listener.DialogConfirmListener;


import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by Weihh on 2019/1/17.
 */
public class DialogParams {

    public static final int INVALID_LAYOUT = -1;

    public final static int MIN_WIDTH_REDUCE_PARAM = 100;

    // layout Params
    public int width = WRAP_CONTENT;
    public int height = WRAP_CONTENT;
    public int gravity = Gravity.CENTER;
    public int style = R.style.dialog_default_style;
    public boolean canceledOnTouchOutside = true;
    public int layout = INVALID_LAYOUT;

    //text values Params
    public String title;

    public String messageContent;

    public String cancelTxt;

    public String confirmTxt;

    public DialogConfirmListener dialogConfirmListener;

    public DialogCancelListener dialogCancelListener;

    //上边文本和底部按钮之间间隔线的显示与否
    public boolean horizontalLineShow = true;

    //两个按钮之间间隔线显示与否
    public boolean verticalLineShow = true;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public boolean isCanceledOnTouchOutside() {
        return canceledOnTouchOutside;
    }

    public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        this.canceledOnTouchOutside = canceledOnTouchOutside;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getCancelTxt() {
        return cancelTxt;
    }

    public void setCancelTxt(String cancelTxt) {
        this.cancelTxt = cancelTxt;
    }

    public String getConfirmTxt() {
        return confirmTxt;
    }

    public void setConfirmTxt(String confirmTxt) {
        this.confirmTxt = confirmTxt;
    }

    public boolean isHorizontalLineShow() {
        return horizontalLineShow;
    }

    public void setHorizontalLineShow(boolean horizontalLineShow) {
        this.horizontalLineShow = horizontalLineShow;
    }

    public boolean isVerticalLineShow() {
        return verticalLineShow;
    }

    public void setVerticalLineShow(boolean verticalLineShow) {
        this.verticalLineShow = verticalLineShow;
    }

    public DialogConfirmListener getDialogConfirmListener() {
        return dialogConfirmListener;
    }

    public void setDialogConfirmListener(DialogConfirmListener dialogConfirmListener) {
        this.dialogConfirmListener = dialogConfirmListener;
    }

    public DialogCancelListener getDialogCancelListener() {
        return dialogCancelListener;
    }

    public void setDialogCancelListener(DialogCancelListener dialogCancelListener) {
        this.dialogCancelListener = dialogCancelListener;
    }

    public boolean doubleButton(){
        return !TextUtils.isEmpty(cancelTxt) && !TextUtils.isEmpty(confirmTxt);
    }
}
