package com.splendid.commondialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.splendid.listener.DialogCancelListener;
import com.splendid.listener.DialogConfirmListener;
import com.splendid.util.CommonUtil;
import com.splendid.view.CommonTipsDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_dialog_one).setOnClickListener(this);
        findViewById(R.id.test_dialog_two).setOnClickListener(this);
        findViewById(R.id.test_dialog_three).setOnClickListener(this);
        findViewById(R.id.test_dialog_four).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_dialog_one:
                showDialogOne();
                break;
            case R.id.test_dialog_two:
                showDialogTwo();
                break;
            case R.id.test_dialog_three:
                showDialogThree();
                break;
            case R.id.test_dialog_four:
                showDialogFour();
                break;
        }
    }

    public void showDialogOne() {
        CommonTipsDialog dialog = (CommonTipsDialog) CommonTipsDialog.newBuilder()
                .setTitle("LOL重要提示")
                .setMessageContent("设计师你看看瑞文，这伤害，不如我们虚弱艾瑞莉娅吧...")
                .setCancelTxt("削弱")
                .setConfirmTxt("重做")
                .setDialogCancelListener(new DialogCancelListener() {
                    @Override
                    public void cancel() {
                        showToast("太无情了");
                    }
                })
                .setDialogConfirmListener(new DialogConfirmListener() {
                    @Override
                    public void confirm() {
                        showToast("毫无人性");
                    }
                })
                .setCanceledOnTouchOutside(false)
                .buildDefaultInstance();
        dialog.show(MainActivity.this);
    }

    public void showDialogTwo() {
        CommonTipsDialog dialog = (CommonTipsDialog) CommonTipsDialog.newBuilder()
                .setTitle("成就达成")
                .setMessageContent("恭喜获得火影劫称号")
                .setConfirmTxt("领取")
                .setDialogConfirmListener(new DialogConfirmListener() {
                    @Override
                    public void confirm() {
                        showToast("儿童劫别玩了");
                    }
                })
                .setCanceledOnTouchOutside(false)
                .buildDefaultInstance();
        dialog.show(MainActivity.this);
    }

    public void showDialogThree() {
        String msg = "是否删除疾风剑豪--亚索";
        CommonTipsDialog dialog = (CommonTipsDialog) CommonTipsDialog.newBuilder()
                .setMessageContent(msg)
                .setCancelTxt("保留")
                .setConfirmTxt("删除")
                .setDialogCancelListener(new DialogCancelListener() {
                    @Override
                    public void cancel() {
                        showToast("开黑吗，我玩亚索");
                    }
                })
                .setDialogConfirmListener(new DialogConfirmListener() {
                    @Override
                    public void confirm() {
                        showToast("你一点都不快乐");
                    }
                })
                .setHeight(CommonUtil.getDialogHeightByMessageContent(msg))
                .setCanceledOnTouchOutside(false)
                .setLayout(R.layout.dialog_confirm)
                .buildDefaultInstance();
        dialog.show(MainActivity.this);
    }

    public void showDialogFour() {
        CommonTipsDialog dialog = (CommonTipsDialog) CommonTipsDialog.newBuilder()
                .setMessageContent("生亦我所欲也，E亦我所欲也，舍生而取者也")
                .setCancelTxt("删除")
                .setDialogCancelListener(new DialogCancelListener() {
                    @Override
                    public void cancel() {
                        showToast("干得好");
                    }
                })
                .setCanceledOnTouchOutside(false)
                .buildDefaultInstance();
        dialog.show(MainActivity.this);
    }


    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
