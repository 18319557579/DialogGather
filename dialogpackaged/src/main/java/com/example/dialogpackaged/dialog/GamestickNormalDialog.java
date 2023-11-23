package com.example.dialogpackaged.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dialogpackaged.R;

/**
 * 封装的游戏管家普通的弹窗，包括标题、内容、确定按钮，取消按钮
 */
public class GamestickNormalDialog extends CommonDialog{
    private String mTitle = "预设标题";
    private String mContent = "预设内容";
    private String mConfirmText = "确定";
    private String mCancelText = "取消";
    private EventCallback mEventCallback = new EventCallback() {
        @Override
        public void onConfirmClick() {
            dismiss();
        }

        @Override
        public void onCancelClick() {
            dismiss();
        }
    };

    public GamestickNormalDialog(@NonNull Context context) {
        //其实这个布局很接近app中的那个布局了，并且该布局中用到drawable，color在内容上其实和app中的一模一样，但是为了标准化，还是复制了一份到dialogpackaged中去
        super(context, R.layout.dialogpackaged_layout_gamestick_normal);
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public void setConfirmText(String mConfirmText) {
        this.mConfirmText = mConfirmText;
    }

    public void setCancelText(String mCancelText) {
        this.mCancelText = mCancelText;
    }

    public void setEventCallback(EventCallback mEventCallback) {
        this.mEventCallback = mEventCallback;
    }

    public interface EventCallback {
        void onConfirmClick();  //点击确认按钮
        void onCancelClick();  //点击取消按钮
        //todo dismiss还没要不要封，封起来怕太难改动
    }

    @Override
    protected void customProperty() {
        ((TextView) findViewById(R.id.dialogpackaged_tv_title)).setText(mTitle);
        ((TextView) findViewById(R.id.dialogpackaged_tv_content)).setText(mContent);

        Button btnConfirm = findViewById(R.id.dialogpackaged_btn_confirm);
        btnConfirm.setText(mConfirmText);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventCallback.onConfirmClick();
            }
        });

        Button btnCancel =  findViewById(R.id.dialogpackaged_btn_cancel);
        btnCancel.setText(mCancelText);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEventCallback.onCancelClick();
            }
        });
    }
}
