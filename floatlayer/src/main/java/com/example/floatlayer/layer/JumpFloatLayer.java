package com.example.floatlayer.layer;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.floatlayer.R;

public class JumpFloatLayer extends FloatLayer{
    private final OnClickConfirmListener onClickConfirmListener;

    public JumpFloatLayer(@NonNull FrameLayout hostLayout, @NonNull OnClickConfirmListener onClickConfirmListener) {
        super(hostLayout, R.layout.medi_tiny_message_bar);
        this.onClickConfirmListener = onClickConfirmListener;
    }

    public void setAppName(String appName) {
        ((TextView)findView(R.id.flla_jump_title_tv)).setText(String.format("允许网站打开 %s 吗？", appName));
    }

    @Override
    protected void windup() {
//        super.windup();  //先不要那个通用的点击，浮层消失

        setOnClickListener(null);  //可以让它点击时不被穿透

        findView(R.id.fila_jump_confirm_tv).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                realDismiss();  //这里不适用出场动画
                onClickConfirmListener.onClick();
            }
        });
    }

    public interface OnClickConfirmListener {
        void onClick();
    }
}
