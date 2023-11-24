package com.example.dialogpackaged.dialog.radio;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class RadioGroup extends LinearLayout {
    private final RadioItem[] radioItems;  //存放item
    public int selectedIndex = 0;  //选择的item顺序

    public RadioGroup(Context context, String[] itemCopyArray, int defaultSelected, OnItemSelectedListener onItemSelectedListener) {
        super(context);

        setOrientation(LinearLayout.VERTICAL);  //垂直分布

        radioItems = new RadioItem[itemCopyArray.length];
        for (int i = 0; i < itemCopyArray.length; i++) {
            RadioItem radioItem = new RadioItem(context, i, itemCopyArray[i], new RadioItem.OnClickItemListener() {
                @Override
                public void onClickItem(int index) {
                    selectOrDeselect(index);
                    onItemSelectedListener.onItemSelected(index);
                }
            });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addView(radioItem, lp);

            radioItems[i] = radioItem;
        }

        selectOrDeselect(defaultSelected);
    }

    private void selectOrDeselect(int index) {
        radioItems[selectedIndex].selectRatio(false);
        selectedIndex = index;
        radioItems[selectedIndex].selectRatio(true);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(int indexSelected);
    }
}
