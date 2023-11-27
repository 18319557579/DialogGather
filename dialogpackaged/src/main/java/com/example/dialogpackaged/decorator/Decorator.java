package com.example.dialogpackaged.decorator;

import com.example.dialogpackaged.DisplayedDialog;
import com.example.dialogpackaged.dialog.CommonDialog;

public class Decorator implements DisplayedDialog {
    private CommonDialog commonDialog;

    public Decorator(CommonDialog commonDialog) {
        this.commonDialog = commonDialog;
    }


    @Override
    public void display() {

    }
}
