package com.example.messagedialog.float_layer;

import android.view.Gravity;

import androidx.annotation.AnimRes;

public class Config {
    /*public enum WidthType {
        SET_CONTENT,
        SET_PADDING;

        private float length;

        public void setLength(float length) {
            this.length = length;
        }
    }

    public WidthType widthType;*/

    //1 宽度
    public boolean lengthType;
    public float size;

    //2.1 定位/水平
    public static final int HORIZONTAL_LEFT = Gravity.LEFT;
    public static final int HORIZONTAL_RIGHT = Gravity.RIGHT;
    public static final int HORIZONTAL_CENTER = Gravity.CENTER_HORIZONTAL;
    public int horizontalLocation;
    public int horizontalMargin;

    //2.2 定位/垂直
    public static final int VERTICAL_TOP = Gravity.TOP;
    public static final int VERTICAL_BOTTOM = Gravity.BOTTOM;
    public static final int VERTICAL_CENTER = Gravity.CENTER_VERTICAL;
    public int verticalLocation;
    public int verticalMargin;

    //3 背景
    //3.1 背景/圆角弧度
    public float radius = -1;

    //3.2 背景/透明度
    public float bgAlpha = -1;  //0代表完全透明，1代表完全不透明

    //3.3 设置自身的透明度（注意背景透明度和自身透明度是两码事，是分开的）
    public float selfAlpha = -1;

    //动画
    public @AnimRes int showAnimRes = -1;  //入场动画
    public @AnimRes int dismissAnimRes = -1;  //出场动画
}
