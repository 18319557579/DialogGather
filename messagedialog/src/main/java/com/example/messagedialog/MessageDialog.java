package com.example.messagedialog;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public class MessageDialog extends FrameLayout {
    public MessageDialog(@NonNull Context context) {
        super(context);

        addView(getView());

        setClickable(true);
    }

    public void show(FrameLayout frameLayout) {

        frameLayout.addView(this);
    }

    private View getView() {
        return View.inflate(this.getContext(), R.layout.medi_tiny_message_bar, null);
    }
}
