package com.example.dialogpackaged.dialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dialogpackaged.R;

public class SlotsFeedbackDialog extends CommonDialog{
    TextView tvTitle;
    TextView tvContent;
    ImageView ivClose;
    Button btnCommit;
    TextView tvCurrent;
    EditText edtInput;

    public SlotsFeedbackDialog(@NonNull Context context) {
        super(context, R.layout.dialogpackaged_layout_slots_feedback);
        tvTitle = findView(R.id.dialogpackaged_slots_tv_title);
        tvContent = findView(R.id.dialogpackaged_slots_tv_content);
        ivClose = findView(R.id.dialogpackaged_slots_iv_close);
        btnCommit = findView(R.id.dialogpackaged_slots_btn_commit);
        tvCurrent = findView(R.id.dialogpackaged_slots_tv_current);
        edtInput = findView(R.id.dialogpackaged_slots_edt_input);

        initView();
    }

    private void initView() {
        btnCommit.setEnabled(false);
        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Daisy", "变化后的内容：" + s + ", 长度：" + s.length());
                tvCurrent.setText(String.valueOf(s.length()));
                btnCommit.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //SlotsFeedbackDialog在set方法中，用直接给控件设置属性的方式
    public void setTvTitleText(String title) {
        tvTitle.setText(title);
    }

    public void setTvContentText(String content) {
        tvContent.setText(content);
    }

    public void setEventCallback(EventCallback eventCallback){
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventCallback.onClose();
                dismiss();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = edtInput.getText().toString();
                Log.d("Daisy", "用户输入的内容：" + input);
                if (input.length() < 10) {
                    Toast.makeText(getContext(), "The number of characters entered must be >=10", Toast.LENGTH_SHORT).show();
                    return;
                }
                eventCallback.onCommit(input);
                dismiss();
            }
        });
    }

    public interface EventCallback {
        void onClose();
        void onCommit(String input);
    }

    @Override
    protected void customProperty() {
        super.customProperty();
    }
}
