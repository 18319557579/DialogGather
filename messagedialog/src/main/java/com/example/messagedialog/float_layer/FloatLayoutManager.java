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
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        layoutParams.bottomMargin = (int) frameLayout.getContext().getResources().getDimension(R.dimen.medi_messagebox_bottom_margin);

        int realSize = config.lengthType ? SizeTransferUtil.dip2px(config.size, frameLayout.getContext()) :
                (int) (ScreenSizeUtil.getScreenWidth(frameLayout.getContext()) * config.size);
        layoutParams.width = config.contentType ? realSize : ScreenSizeUtil.getScreenWidth(frameLayout.getContext()) - realSize;

        floatLayer.setLayoutParams(layoutParams);

        floatLayer.show(frameLayout);
    }
}
