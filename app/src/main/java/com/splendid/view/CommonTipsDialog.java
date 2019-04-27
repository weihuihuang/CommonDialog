package com.splendid.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.splendid.commondialog.R;
import com.splendid.listener.DialogCancelListener;
import com.splendid.listener.DialogConfirmListener;
import com.splendid.model.DialogParams;
import com.splendid.util.CommonUtil;

import static com.splendid.model.DialogParams.INVALID_LAYOUT;

@SuppressWarnings("unused")
public class CommonTipsDialog extends DialogFragment {

    protected TextView mTitle;

    protected TextView mMessageContent;

    protected TextView mCancleBtn;

    //显示文本和底部按钮之间的间隔线,默认显示
    protected View separatedHorizontalLine;

    //两个按钮之间的间隔线,只有两个按钮同时存在时才默认显示
    protected View separatedVerticalLine;

    protected TextView mConfirmBtn;

    protected DialogParams dialogParams;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //宿主activity被异常回收时，DialogFragment也会被回收，如有需要可在fragment的onSaveInstanceState方法保存需要的信息，
        //如果不保存，在尝试恢复activity时，同时尝试恢复DialogFragment，因为没有保存数据，dialogParams为null,此时会导致空指针，
        //造成二次crash,考虑到此时已经处于异常状态，所以dialogParams为空时直接dimss异常状态的dialog,使用不保留活动可以模拟此场景
        //关于activity和fragment状态保存https://inthecheesefactory.com/blog/fragment-state-saving-best-practices/en
        if (dialogParams == null) {
            dismiss();
            return null;
        }
        setDialogAttributes();
        return setLayout(inflater, container);
    }

    public void setDialogAttributes() {
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        params.gravity = dialogParams.gravity;
        params.width = dialogParams.width;
        params.height = dialogParams.height;
        window.setAttributes(params);
        window.setWindowAnimations(dialogParams.style);
        getDialog().setCanceledOnTouchOutside(dialogParams.canceledOnTouchOutside);
    }

    public View setLayout(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(isDefaultLayout() ? R.layout.common_dialog : dialogParams.getLayout(), container, false);

        initTitle(view);
        initMessage(view);
        initCancelBtn(view);
        initConfirmBtn(view);
        initHorizontalSeparatedLine(view);
        initVerticalSeparatedLine(view);

        return view;
    }

    public void show(Activity activity) {
        if (!isAdded()) {
            //activity异常销毁,保存状态的时间段内show会导致如下异常
            //DialogFragment Fatal Exception: java.lang.IllegalStateException:Can not perform this action after onSaveInstanceState
            //也可以考虑copy源码的showAllowingStateLoss方法调用避免这个异常,但是需要使用反射给相应变量赋值，考虑到异常属于极少数情况，反射影响效率
            //所以直接使用try catch.
            try {
                show(activity.getFragmentManager(), "");
            } catch (IllegalStateException ignore) {

            }
        }
    }

    protected void initTitle(View view) {
        if (TextUtils.isEmpty(dialogParams.title)) {
            return;
        }
        mTitle = view.findViewById(R.id.title);
        if (mTitle != null) {
            mTitle.setText(dialogParams.title);
            mTitle.setVisibility(View.VISIBLE);
        }
    }

    protected void initMessage(View view) {
        if (TextUtils.isEmpty(dialogParams.messageContent)) {
            return;
        }
        mMessageContent = view.findViewById(R.id.message);
        if (mMessageContent != null) {
            mMessageContent.setText(dialogParams.messageContent);
            mMessageContent.setVisibility(View.VISIBLE);
        }
    }

    protected void initCancelBtn(View view) {
        if (TextUtils.isEmpty(dialogParams.cancelTxt)) {
            if (dialogParams.dialogCancelListener != null) {
                throw new IllegalArgumentException(getString(R.string.dialog_cancel_txt_unset));
            }
            return;
        }
        mCancleBtn = view.findViewById(R.id.cancel_btn);
        if (mCancleBtn != null) {
            mCancleBtn.setText(dialogParams.cancelTxt);
            mCancleBtn.setVisibility(View.VISIBLE);
            mCancleBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogParams.dialogCancelListener != null) {
                        dialogParams.dialogCancelListener.cancel();
                    }
                    dismiss();
                }
            });

        }
    }

    protected void initConfirmBtn(View view) {
        if (TextUtils.isEmpty(dialogParams.confirmTxt)) {
            if (dialogParams.dialogConfirmListener != null) {
                throw new IllegalArgumentException(getString(R.string.dialog_confirm_txt_unset));
            }
            return;
        }
        mConfirmBtn = view.findViewById(R.id.confirm_btn);
        if (mConfirmBtn != null) {
            mConfirmBtn.setText(dialogParams.confirmTxt);
            mConfirmBtn.setVisibility(TextUtils.isEmpty(dialogParams.confirmTxt) ? View.GONE : View.VISIBLE);
            mConfirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogParams.dialogConfirmListener != null) {
                        dialogParams.dialogConfirmListener.confirm();
                    }
                    dismiss();
                }
            });
        }
    }

    protected void initHorizontalSeparatedLine(View view) {
        if (dialogParams.horizontalLineShow) {
            separatedHorizontalLine = view.findViewById(R.id.separated_horizontal_line);
            if (separatedHorizontalLine != null) {
                separatedHorizontalLine.setVisibility(View.VISIBLE);
            }
        }
    }

    protected void initVerticalSeparatedLine(View view) {
        if (dialogParams.verticalLineShow && dialogParams.doubleButton()) {
            separatedVerticalLine = view.findViewById(R.id.separated_vertical_line);
            if (separatedVerticalLine != null) {
                separatedVerticalLine.setVisibility(View.VISIBLE);
            }
        }
    }

    public boolean isShowing() {
        return getDialog() != null && getDialog().isShowing();
    }

    public static DialogFragment newInstance(DialogBuilder builder) {
        CommonTipsDialog dialog = new CommonTipsDialog();
        dialog.dialogParams = builder.getDialogParams();
        return dialog;
    }

    public static DialogFragment newDefaultInstance(DialogBuilder builder) {
        return newInstance(getDefaultWidthBuilder(builder));
    }

    public static DialogBuilder newBuilder() {
        return new DialogBuilder();
    }


    public static DialogBuilder getDefaultWidthBuilder(DialogBuilder builder) {
        DisplayMetrics metrics = CommonUtil.getDisplayMetrics();
        int min = Math.min(metrics.widthPixels, metrics.heightPixels);
        int width = (int) (min - DialogParams.MIN_WIDTH_REDUCE_PARAM * metrics.density);
        builder.setWidth(width);
        return builder;

    }

    /**
     * 布局结构差别不大的，但是UI差别较大的只要重新设置布局即可，详情看setLayout(inflater, container)方法
     */
    protected boolean isDefaultLayout() {
        return dialogParams != null && dialogParams.getLayout() == INVALID_LAYOUT;
    }

    /**
     * 设置dialog的属性
     */
    public static class DialogBuilder {

        protected final DialogParams dialogParams;

        public DialogBuilder() {
            this.dialogParams = new DialogParams();
        }

        public DialogParams getDialogParams() {
            return dialogParams;
        }

        public DialogBuilder setWidth(int width) {
            dialogParams.width = width;
            return this;
        }

        public DialogBuilder setHeight(int height) {
            dialogParams.height = height;
            return this;
        }


        public DialogBuilder setGravity(int gravity) {
            dialogParams.gravity = gravity;
            return this;
        }

        public DialogBuilder setStyle(int style) {
            dialogParams.style = style;
            return this;
        }

        public DialogBuilder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            dialogParams.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public DialogBuilder setLayout(int layout) {
            dialogParams.layout = layout;
            return this;
        }

        public DialogBuilder setTitle(String title) {
            dialogParams.title = title;
            return this;
        }

        public DialogBuilder setMessageContent(String messageContent) {
            dialogParams.messageContent = messageContent;
            return this;
        }

        public DialogBuilder setCancelTxt(String cancelTxt) {
            dialogParams.cancelTxt = cancelTxt;
            return this;
        }

        public DialogBuilder setDialogCancelListener(DialogCancelListener dialogCancelListener) {
            dialogParams.dialogCancelListener = dialogCancelListener;
            return this;
        }


        public DialogBuilder setConfirmTxt(String confirmTxt) {
            dialogParams.confirmTxt = confirmTxt;
            return this;
        }


        public DialogBuilder setDialogConfirmListener(DialogConfirmListener dialogConfirmListener) {
            dialogParams.dialogConfirmListener = dialogConfirmListener;
            return this;
        }

        public DialogBuilder setHorizontalLineShow(boolean horizontalLineShow) {
            dialogParams.horizontalLineShow = horizontalLineShow;
            return this;
        }

        public DialogBuilder setVerticalLineShow(boolean verticalLineShow) {
            dialogParams.verticalLineShow = verticalLineShow;
            return this;
        }

        public DialogFragment build() {
            return newInstance(this);
        }

        public DialogFragment buildDefaultInstance() {
            return newDefaultInstance(this);
        }
    }

}
