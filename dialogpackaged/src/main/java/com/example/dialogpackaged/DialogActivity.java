package com.example.dialogpackaged;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        CommonDialog commonDialog = new CommonDialog(this, R.layout.activity_dialog);

        DisplayedDialog dialogDecorator = new DialogDecorator(commonDialog);
        dialogDecorator.display();


    }
}