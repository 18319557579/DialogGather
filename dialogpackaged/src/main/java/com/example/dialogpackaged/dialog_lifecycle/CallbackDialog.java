package com.example.dialogpackaged.dialog_lifecycle;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

public class CallbackDialog extends Dialog {

    private final String canonicalName = getClass().getCanonicalName();

    public CallbackDialog(@NonNull Context context) {
        super(context);
//        Log.d("Daisy", canonicalName + " 构造函数");

        /*setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Log.d("Daisy", canonicalName + " 回调OnShowListener.onShow");
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Log.d("Daisy", canonicalName + " 回调OnDismissListener.onDismiss");
            }
        });
        //调用Dialog.dismiss()和Dialog.cancel()没有本质区别，不过点击屏幕外以及返回键关闭的弹窗官方调用的都是cancel()
        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d("Daisy", canonicalName + " 回调OnCancelListener.onCancel");
            }
        });*/
    }

    /**
     * 调用构造方法的时候不会回调onCreate，在show的时候才会。并且重复使用一个实例时，第二次show不会回调onCreate
     *
     * 官方注释说类似于Activity.onCreate，但实际上在官方注释说类似于Activity中setContentView都是在onCreate的，
     * 但是在Dialog，构造函数也是可以setContentView的（todo 但是目前没试过Activity的构造函数中是否可以setContentView和findViewById）
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
