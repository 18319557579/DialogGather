package com.example.dialogpackaged;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import java.util.Objects;

public class CommonDialog extends Dialog implements DisplayedDialog{
    protected final View mContentView;
    protected boolean cancelBackEvent = false;
    public CommonDialog(@NonNull Context context, int contentViewId) {
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

    //给子类进行实现的
    protected void customProperty() {}
}
