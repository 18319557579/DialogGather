package com.example.dialogpackaged.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.dialogpackaged.DisplayedDialog;

import java.util.Objects;

public class CommonDialog extends Dialog implements DisplayedDialog {
    public final View mContentView;
    public boolean cancelBackEvent = false;

    //contentViewId为必传的参数，用于给Dialog设置内容
    public CommonDialog(@NonNull Context context, @LayoutRes int contentViewId) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mContentView = LayoutInflater.from(context).inflate(contentViewId, null);
        setContentView(mContentView);

        //把原背景干掉，否则圆角效果出不来
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void display() {
        customProperty();
        show();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        //如果设置了取消返回键，那么返回键将会失效
        if (keyCode == KeyEvent.KEYCODE_BACK && cancelBackEvent) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //公开的方法，子类和外部的类都可以通过该方法找到某id的控件
    public <T extends View> T findView(int id) {
        return mContentView.findViewById(id);
    }

    //给子类进行自定义实现
    protected void customProperty() {}
}
