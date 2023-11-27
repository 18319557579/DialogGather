package com.example.dialogpackaged.dialog_lifecycle;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

public class CallbackDialog extends Dialog {

    private final String canonicalName = getClass().getCanonicalName();

    public CallbackDialog(@NonNull Context context) {
        super(context);
        Log.d("Daisy", canonicalName + " 构造函数");
    }

    /**
     * 调用构造方法的时候不会回调onCreate，在show的时候才会。并且重复使用一个实例时，第二次show不会回调onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Daisy", canonicalName + " 回调onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Daisy", canonicalName + " 回调onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Daisy", canonicalName + " 回调onStop");
    }

}
