package com.example.dialogpackaged.dialog.radio;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dialogpackaged.R;
import com.example.dialogpackaged.dialog.CommonDialog;

public class GamestickRadioDialog extends CommonDialog {
    private TextView tvTitle;  //标题
    private Button btnCancel, btnConfirm;  //底部两个按钮

    private String titleStr = "请选择";
    private String cancelStr = "取消";
    private String confirmStr = "确定";

    public GamestickRadioDialog(@NonNull Context context, String[] itemCopyArray, int defaultSelected, OnDialogClickListener onDialogClickListener) {
        super(context, R.layout.dialogpackaged_layout_gamestick_confirmation);

        tvTitle = findViewById(R.id.tv_title);
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);

        RadioGroup radioGroup= new RadioGroup(context, itemCopyArray, defaultSelected, new RadioGroup.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int indexSelected) {
                onDialogClickListener.onItemSelected(indexSelected);
            }
        });
        LinearLayout llRatioList = findViewById(R.id.ll_ratio_list);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llRatioList.addView(radioGroup, lp);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClickListener.onLeftButtonClick();
                dismiss();
            }
        });
        //点击确认按钮，将选中的选项进行回调
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogClickListener.onRightButtonClick(radioGroup.selectedIndex);
                dismiss();
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onDialogClickListener.onDisappear();
            }
        });
    }

    public interface OnDialogClickListener {
        void onLeftButtonClick();  //点击取消按钮
        void onRightButtonClick(int index);  //点击确定按钮，回调当前的选项
        void onItemSelected(int index);  //选项发生改变时回调，回调当前的选项
        void onDisappear();  //弹窗消失时回调
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    public void setCancelStr(String cancelStr) {
        this.cancelStr = cancelStr;
    }

    public void setConfirmStr(String confirmStr) {
        this.confirmStr = confirmStr;
    }

    @Override
    protected void customProperty() {
        tvTitle.setText(titleStr);
        btnCancel.setText(cancelStr);
        btnConfirm.setText(confirmStr);
    }
}
