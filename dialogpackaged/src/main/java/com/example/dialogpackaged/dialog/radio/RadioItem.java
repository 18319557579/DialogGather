package com.example.dialogpackaged.dialog.radio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dialogpackaged.R;

public class RadioItem extends LinearLayout {
    private TextView tvItem;  //放置选项文案
    private ImageView ivRadio;  //选项的那个图片

    public RadioItem(Context context, int itemIndex, String itemCopy, OnClickItemListener onClickItemListener) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.dialogpackaged_layout_item, this, true);
        tvItem = view.findViewById(R.id.dialogpackaged_item_tv);
        ivRadio = view.findViewById(R.id.dialogpackaged_item_iv);
        tvItem.setText(itemCopy);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItemListener.onClickItem(itemIndex);
            }
        });
    }

    /**
     * 对该选项进行切换
     */
    public void selectRatio(boolean isSelect) {
        ivRadio.setImageResource(isSelect ? R.drawable.dialogpackaged_img_item_selected : R.drawable.dialogpackaged_img_item_unselected);
    }

    public interface OnClickItemListener {
        void onClickItem(int index);
    }
}
