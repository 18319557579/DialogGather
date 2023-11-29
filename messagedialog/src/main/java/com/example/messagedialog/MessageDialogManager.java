package com.example.messagedialog;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.utilsgather.ui.screen.ScreenSizeUtil;

public class MessageDialogManager {
    private MessageDialogManager(){}

    private static class MessageDialogManagerHolder {
        private static final MessageDialogManager INSTANCE = new MessageDialogManager();
    }

    public static MessageDialogManager getInstance() {
        return MessageDialogManagerHolder.INSTANCE;
    }

    public void tryToShow(FrameLayout frameLayout, String appName) {
        MessageDialog messageDialog = new MessageDialog(frameLayout.getContext());

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        layoutParams.bottomMargin = (int) frameLayout.getContext().getResources().getDimension(R.dimen.medi_messagebox_bottom_margin);
        layoutParams.width = (int) (ScreenSizeUtil.getScreenWidth(frameLayout.getContext()) * 0.8F);
        messageDialog.setLayoutParams(layoutParams);

        messageDialog.show(frameLayout);
    }


}
