package com.example.messagedialog.float_layer.layer;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public class FloatLayer extends FrameLayout {

    private FrameLayout hostLayout;  //宿主布局
    public View soleChildView;  //唯一的子View

    public FloatLayer(@NonNull Context context, @LayoutRes int layoutRes) {
        super(context);
        addView(soleChildView = getContentView(layoutRes));

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hostLayout.removeView(FloatLayer.this);
            }
        });
    }

    private View getContentView(@LayoutRes int layoutRes) {
        return View.inflate(this.getContext(), layoutRes, null);
    }

    public void show(FrameLayout hostLayout) {
        this.hostLayout = hostLayout;
        hostLayout.addView(this);
    }
}
