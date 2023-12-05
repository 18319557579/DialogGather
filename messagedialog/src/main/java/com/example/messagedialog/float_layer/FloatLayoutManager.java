package com.example.messagedialog.float_layer;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;

import com.example.messagedialog.R;
import com.example.messagedialog.float_layer.layer.FloatLayer;
import com.example.utilsgather.ui.SizeTransferUtil;
import com.example.utilsgather.ui.screen.ScreenSizeUtil;

public class FloatLayoutManager {
    private FloatLayoutManager(){}
    private static class FloatLayoutManagerHolder {
        private static final FloatLayoutManager INSTANCE = new FloatLayoutManager();
    }
    public static FloatLayoutManager getInstance() {
        return FloatLayoutManagerHolder.INSTANCE;
    }

    public void show(FrameLayout frameLayout, Config config, @LayoutRes int layoutRes) {
        show(frameLayout, config, new FloatLayer(frameLayout.getContext(), layoutRes));
    }

    public void show(FrameLayout frameLayout, Config config, FloatLayer floatLayer) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = config.verticalLocation | config.horizontalLocation;

        if (config.horizontalLocation == Config.HORIZONTAL_LEFT) {
            layoutParams.leftMargin = SizeTransferUtil.dip2px(config.horizontalMargin, frameLayout.getContext());
        } else if (config.horizontalLocation == Config.HORIZONTAL_RIGHT) {
            layoutParams.rightMargin = SizeTransferUtil.dip2px(config.horizontalMargin, frameLayout.getContext());
        }

        if (config.verticalLocation == Config.VERTICAL_TOP) {
            layoutParams.topMargin = SizeTransferUtil.dip2px(config.verticalMargin, frameLayout.getContext());
        } else if (config.horizontalLocation == Config.VERTICAL_BOTTOM) {
            layoutParams.bottomMargin = SizeTransferUtil.dip2px(config.verticalMargin, frameLayout.getContext());
        }

        layoutParams.width = config.lengthType ? SizeTransferUtil.dip2px(config.size, frameLayout.getContext()) :
                (int) (ScreenSizeUtil.getScreenWidth(frameLayout.getContext()) * config.size);

        if (config.radius != -1) {
            setRadius(floatLayer, config.radius);
        }


        floatLayer.setLayoutParams(layoutParams);
        floatLayer.show(frameLayout);
    }

    private void setRadius(FloatLayer floatLayer, float cornerRadius) {
        Drawable background = floatLayer.soleChildView.getBackground();  //先拿到dialog的背景
        //如果dialog的资源view是没有背景的，那就设置一个白色的背景
        if (background == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(Color.parseColor("#FFFFFF"));
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, floatLayer.getContext()));
            floatLayer.setBackground(gradientDrawable);

            //如果为GradientDrawable，则修改其拐角半径
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, floatLayer.getContext()));

            //发现如果背景为纯色，那么获取到的背景将会是ColorDrawable类型的
        } else if (background instanceof ColorDrawable) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(((ColorDrawable)background).getColor());
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, floatLayer.getContext()));
            floatLayer.setBackground(gradientDrawable);
        }
    }
}
