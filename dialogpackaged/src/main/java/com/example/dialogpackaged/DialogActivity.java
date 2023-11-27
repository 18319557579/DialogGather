package com.example.dialogpackaged;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dialogpackaged.decorator.StyleDecorator;
import com.example.dialogpackaged.dialog.CommonDialog;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        CommonDialog commonDialog = new CommonDialog(this, R.layout.activity_dialog);

        DisplayedDialog dialogDecorator = new StyleDecorator(commonDialog);
        dialogDecorator.display();


    }
}