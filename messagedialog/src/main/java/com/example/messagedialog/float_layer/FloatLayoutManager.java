package com.example.messagedialog.float_layer;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.messagedialog.MessageDialog;
import com.example.messagedialog.R;
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

    public void show(FrameLayout frameLayout, Config config) {
        FloatLayer floatLayer = new FloatLayer(frameLayout.getContext(), R.layout.medi_tiny_message_bar);

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

        floatLayer.setLayoutParams(layoutParams);
        floatLayer.show(frameLayout);
    }
}
