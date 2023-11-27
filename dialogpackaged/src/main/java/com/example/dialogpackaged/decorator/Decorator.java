package com.example.dialogpackaged.decorator;

import com.example.dialogpackaged.DisplayedDialog;
import com.example.dialogpackaged.dialog.CommonDialog;

public class Decorator implements DisplayedDialog {
    private final DisplayedDialog mDisplayedDialog;  //用于嵌套display()
    protected CommonDialog mDialog;  //装饰器都是需要Dialog的，这里是给装饰器用的

    public Decorator(DisplayedDialog displayedDialog) {
        mDisplayedDialog = displayedDialog;

        //在父类抽象出来做这样一个工作，反正具体的Decorator都是需要做的
        if (displayedDialog instanceof CommonDialog) {
            this.mDialog = (CommonDialog)displayedDialog;
        } else if (displayedDialog instanceof Decorator){
            this.mDialog = ((Decorator)displayedDialog).mDialog;
        }
    }

    @Override
    public void display() {
        mDisplayedDialog.display();
    }
}
