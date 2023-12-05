package com.example.messagedialog.float_layer;

import android.view.Gravity;

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


}
