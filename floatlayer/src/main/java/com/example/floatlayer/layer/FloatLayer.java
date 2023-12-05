package com.example.floatlayer.layer;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.AnimRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.example.utilsgather.logcat.LogUtil;

public class FloatLayer extends FrameLayout {

    private FrameLayout hostLayout;  //宿主布局
    public View soleChildView;  //唯一的子View

    public @AnimRes int dismissAnimRes = -1;  //出场动画

    public FloatLayer(@NonNull Context context, @LayoutRes int layoutRes) {
        super(context);
        addView(soleChildView = getContentView(layoutRes));

        addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
            //动画一开始时就会回调
            @Override
            public void onViewAttachedToWindow(@NonNull View v) {
                LogUtil.d("测试 " + "onViewAttachedToWindow 回调" + v);
            }

            //在动画结束后才会回调该方法，意思就是动画时间越长，该方法回调得越晚
            @Override
            public void onViewDetachedFromWindow(@NonNull View v) {
                LogUtil.d("测试 " + "onViewDetachedFromWindow 回调" + v);
            }
        });
    }

    private View getContentView(@LayoutRes int layoutRes) {
        return View.inflate(this.getContext(), layoutRes, null);
    }

    public void show(FrameLayout hostLayout) {
        this.hostLayout = hostLayout;
        windup();
        hostLayout.addView(this);
    }

    /**
     * 最后执行的一些操作
     */
    private void windup() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dismissAnimRes != -1) {
                    setAnimation(AnimationUtils.loadAnimation(getContext(), dismissAnimRes));
                }
                hostLayout.removeView(FloatLayer.this);
            }
        });
    }
}
