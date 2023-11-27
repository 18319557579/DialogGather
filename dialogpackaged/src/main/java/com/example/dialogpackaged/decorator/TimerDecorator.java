package com.example.dialogpackaged.decorator;

import android.content.DialogInterface;

import com.example.dialogpackaged.DisplayedDialog;
import com.example.dialogpackaged.dialog.CommonDialog;
import com.example.utilsgather.logcat.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDecorator extends Decorator {

    private Timer timer;
    private long dismissDelay = 3000L;  //默认的延迟时间

    public TimerDecorator(DisplayedDialog displayedDialog) {
        super(displayedDialog);
        init();
    }

    private void init() {
        timer = new Timer();
    }

    public void setDismissDelay(long dismissDelay) {
        this.dismissDelay = dismissDelay;
    }

    @Override
    public void display() {
        mDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        LogUtil.d("定时器结束");

                        if (mDialog.isShowing()) {  //这里的判断其实已经无关紧要，毕竟如果还能回调到这里，就说明定时器没被取消，也就是onDismiss还没被回调，那么dialog也必定还在展示
                            mDialog.dismiss();
                        } else {  //这里的判断其实也已经无关紧要，理论上没有可能走到
                            LogUtil.d("定时器结束前Dialog就已经消失了");
                        }
                    }
                }, dismissDelay);
            }
        });

        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //取消定时器，那么TimerTask.run()就不会回调了
                LogUtil.d("定时器已被取消");
                timer.cancel();
            }
        });

        super.display();
    }
}
