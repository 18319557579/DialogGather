package com.example.floatlayer.layer;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.floatlayer.R;

public class JumpFloatLayer extends FloatLayer{
    public JumpFloatLayer(@NonNull FrameLayout hostLayout) {
        super(hostLayout, R.layout.medi_tiny_message_bar);
    }
}
