package com.example.dialoggather;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.floatlayer.FloatLayoutManager;
import com.example.utilsgather.logcat.LogUtil;

public class CustomFrameLayout extends FrameLayout {
    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(@NonNull View v) {
                LogUtil.d("布局 onViewAttachedToWindow");
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull View v) {
                LogUtil.d("布局 onViewDetachedFromWindow");
                FloatLayoutManager.getInstance();
            }
        });

        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                LogUtil.d("布局 onLayoutChange");
            }
        });

        setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                LogUtil.d("布局 onSystemUiVisibilityChange");
            }
        });

        setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsets onApplyWindowInsets(@NonNull View v, @NonNull WindowInsets insets) {
                LogUtil.d("布局 onApplyWindowInsets");
                return null;
            }
        });

        setOnHierarchyChangeListener(new OnHierarchyChangeListener() {
            @Override
            public void onChildViewAdded(View parent, View child) {
                LogUtil.d("布局 onChildViewAdded");
            }

            @Override
            public void onChildViewRemoved(View parent, View child) {
                LogUtil.d("布局 onChildViewRemoved");
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtil.d("布局 onViewDetachedFromWindow222");
        FloatLayoutManager.getInstance().removeHost(this);
    }
}
