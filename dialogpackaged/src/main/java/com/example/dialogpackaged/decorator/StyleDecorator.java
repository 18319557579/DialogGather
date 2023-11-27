package com.example.dialogpackaged.decorator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.StyleRes;

import com.example.dialogpackaged.DisplayedDialog;
import com.example.dialogpackaged.dialog.CommonDialog;
import com.example.utilsgather.ui.SizeTransferUtil;
import com.example.utilsgather.ui.screen.ScreenSizeUtil;

import java.lang.reflect.Field;

public class DialogDecorator extends Decorator {


    //Dialog返回事件的响应方式
    public enum DismissResponse {
        RESPONSE_1,  //点击外部关闭，点击返回键关闭
        RESPONSE_2,  //点击外部无反应，点击返回键关闭
        RESPONSE_3,  //点击外部关闭，点击返回键无反应
        RESPONSE_4;  //点击外部无反应，点击返回键无反应
    }

    //目前只设定垂直方向的位置，因为基本都是水平居中的
    public enum VerticalPosition {
        TOP(Gravity.TOP),
        CENTER(Gravity.CENTER),
        BOTTOM(Gravity.BOTTOM);
        public final int value;
        VerticalPosition(int value) {
            this.value = value;
        }
    }

//    private final CommonDialog mDialog;  //被装饰的Dialog本体
    private Window mDialogWindow;  //Dialog所在的window
    private Context mDialogContext;  //从Dialog中获得的context

    //这里的偏离是相对于某Gravity而言的，例如为TOP，那么就是以屏幕顶部为基准开始向下偏离；BOTTOM就是以屏幕底部为基准开始向上偏离
    //CENTER就是以屏幕中间为基准开始向下偏离
    private boolean mVerticalBiasTypeRatio = true;  //在垂直方向上偏移类型，比例/长度，默认比例
    private float mVerticalBiasRatio = 0;  //垂直偏移比例
    private int mVerticalBiasLength = 0;  //垂直偏移量（单位为dp，后面会转换为px）

    private boolean mWidthTypeRatio = true;  //宽度的类型，水平占比/水平方向留白，默认水平占比
    private float mWidthRatio = 4 / 5F;  //水平占比
    private int mWidthSpare = 30;  //水平留白（实际为x2）

    /*public DialogDecorator(CommonDialog mDialog) {
        super(mDialog);
//        this.mDialog = mDialog;
        mDialogWindow = mDialog.getWindow();
        mDialogContext = mDialog.getContext();
    }

    public DialogDecorator(Decorator decorator) {
        super(decorator);
    }*/

    public DialogDecorator(DisplayedDialog displayedDialog) {
        super(displayedDialog);
        init();
    }

    private void init() {
        mDialogWindow = mDialog.getWindow();
        mDialogContext = mDialog.getContext();
    }

    /**
     * 设置垂直的位置，以及偏移比例
     * todo gravity没有存在一个字段中，而是直接设置，是因为考虑到gravity一般不会再改变了。后续研究这样是否好
     */
    public void setVerticalGravityAndBias(VerticalPosition gravity, float biasRatio) {
        mVerticalBiasTypeRatio = true;
        mDialogWindow.setGravity(gravity.value);
        mVerticalBiasRatio = biasRatio;
    }

    /**
     * 设置垂直的位置，以及偏移长度
     */
    public void setVerticalGravityAndBias(VerticalPosition gravity, int biasLength) {
        mVerticalBiasTypeRatio = false;
        mDialogWindow.setGravity(gravity.value);
        mVerticalBiasLength = biasLength;
    }

    /**
     * 设置弹窗宽度的水平占比
     */
    public void setRatioOrSpare(float ratio) {
        mWidthTypeRatio = true;
        mWidthRatio = ratio;
    }

    /**
     * 设置水平方向的左右留白
     */
    public void setRatioOrSpare(int spare) {
        mWidthTypeRatio = false;
        mWidthSpare = spare;
    }

    /**
     * 设置弹窗的关闭方式
     */
    public void setCancelable(DismissResponse response) {
        switch (response) {
            case RESPONSE_1:
                break;
            case RESPONSE_2:
                //setCanceledOnTouchOutside()设置点击弹窗外部是否关闭
                mDialog.setCanceledOnTouchOutside(false);
                break;
            case RESPONSE_3:
                //利用屏蔽返回键的方式来实现返回键无法关闭弹窗
                mDialog.cancelBackEvent = true;
                break;
            case RESPONSE_4:
                //setCancelable()设置是否可以被关闭，如果为false那么点击dialog外，或者按返回键都无法关闭。但是调用dismiss还是可以关闭的
                mDialog.setCancelable(false);
                break;
        }
    }

    /**
     * @param alpha dialog的透明度（整体透明度，并不只是背景的），0-1，越大透明度就越高，1为完全不透明，0为全透明
     * @param dimAmount dialog外的调暗程度，0-1，越大就越暗，1为全黑，0为完全不暗
     * 因他们设置后几乎不会改变，因此使用直设式
     */
    public void setAlphaAndDimAmount(float alpha, float dimAmount) {
        WindowManager.LayoutParams params = mDialogWindow.getAttributes();
        params.alpha = alpha;
        params.dimAmount = dimAmount;
    }

    /**
     * 设置圆角半径
     */
    public void setBackgroundCornerRadius(int cornerRadius) {
        Drawable background = mDialog.mContentView.getBackground();  //先拿到dialog的背景
        //如果dialog的资源view是没有背景的，那就设置一个白色的背景
        if (background == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(Color.parseColor("#FFFFFF"));
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, mDialogContext));
            mDialog.mContentView.setBackground(gradientDrawable);

            //如果为GradientDrawable，则修改其拐角半径
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, mDialogContext));

            //发现如果背景为纯色，那么获取到的背景将会是ColorDrawable类型的
        } else if (background instanceof ColorDrawable) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(((ColorDrawable)background).getColor());
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, mDialogContext));
            mDialog.mContentView.setBackground(gradientDrawable);
        }
        //todo 如果背景为Selector的话，将为StateListDrawable类，不知道怎么弄
    }

    /**
     * 设置圆角半径，分为为左上，右上，左下，右下
     */
    public void setBackgroundCornerRadius2(int[] cornerRadii) {
        Drawable background = mDialog.mContentView.getBackground();

        //如果没背景，那就设置默认设置一个白色的背景
        if (background == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(Color.parseColor("#FFFFFF"));
            gradientDrawable.setCornerRadii(new float[]{
                    SizeTransferUtil.dip2px(cornerRadii[0], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[0], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[1], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[1], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[2], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[2], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[3], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[3], mDialogContext)});
            mDialog.mContentView.setBackground(gradientDrawable);

            //如果为GradientDrawable（<shape>），则修改其拐角半径
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setCornerRadii(new float[]{
                    SizeTransferUtil.dip2px(cornerRadii[0], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[0], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[1], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[1], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[2], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[2], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[3], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[3], mDialogContext)});

            //发现如果背景为纯色，那么获取到的背景将会是ColorDrawable类型的
        } else if (background instanceof ColorDrawable){
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(((ColorDrawable) background).getColor());
            gradientDrawable.setCornerRadii(new float[]{
                    SizeTransferUtil.dip2px(cornerRadii[0], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[0], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[1], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[1], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[2], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[2], mDialogContext),
                    SizeTransferUtil.dip2px(cornerRadii[3], mDialogContext),SizeTransferUtil.dip2px(cornerRadii[3], mDialogContext)});
            mDialog.mContentView.setBackground(gradientDrawable);
        }
    }

    /**
     * 设置圆角半径的重载，用于将圆角半径和背景颜色一起设置
     */
    public void setBackgroundCornerRadius(int cornerRadius, int backgroundColor) {
        Drawable background = mDialog.mContentView.getBackground();  //先拿到dialog的背景
        //如果dialog的资源view是没有背景的，那就设置一个白色的背景
        if (background == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(backgroundColor);
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, mDialogContext));
            mDialog.mContentView.setBackground(gradientDrawable);

            //如果为GradientDrawable，则修改其拐角半径
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setColor(backgroundColor);
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, mDialogContext));

            //发现如果背景为纯色，那么获取到的背景将会是ColorDrawable类型的
        } else if (background instanceof ColorDrawable) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(backgroundColor);
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, mDialogContext));
            mDialog.mContentView.setBackground(gradientDrawable);
        }
    }

    public enum DismissSituation {
        NONE,
        NAVIGATION_BAR,  //隐藏导航栏，并可嵌入进去（感觉上这个不好看，因为它底下的Activity其实此时也是没有导航栏的了）
        STATUS_BAR,   //隐藏状态栏，并可嵌入进去
        ALL  //隐藏导航栏和状态栏，并都可嵌入进去
    }

    /**
     * 设置沉浸式
     */
    public void setImmersion(DismissSituation situation) {
        if (situation.equals(DismissSituation.NONE)) {
            return;
        }

        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        try {
            Field field = lp.getClass().getField("layoutInDisplayCutoutMode");
            Field constValue = lp.getClass().getDeclaredField("LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES");
            field.setInt(lp, constValue.getInt(null));

            int flag = 0;
            if (situation.equals(DismissSituation.NAVIGATION_BAR)) {
                flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

            } else if (situation.equals(DismissSituation.STATUS_BAR)) {
                flag = View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            } else if (situation.equals(DismissSituation.ALL)) {
                flag = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            }

            flag |= View.class.getDeclaredField("SYSTEM_UI_FLAG_IMMERSIVE_STICKY").getInt(null);
            View view = mDialogWindow.getDecorView();
            view.setSystemUiVisibility(flag);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置动画（可以包括进入和退出）
     */
    public void setAnimation(@StyleRes int styleId) {
        mDialogWindow.setWindowAnimations(styleId);
    }

    @Override
    public void display() {
        WindowManager.LayoutParams params = mDialogWindow.getAttributes();
        if (mVerticalBiasTypeRatio) {
            params.y = (int) (ScreenSizeUtil.getScreenHeight(mDialogContext) * mVerticalBiasRatio);
        } else {
            params.y = SizeTransferUtil.dip2px(mVerticalBiasLength, mDialogContext);
        }

        if (mWidthTypeRatio) {
            params.width = (int) (ScreenSizeUtil.getScreenWidth(mDialogContext) * mWidthRatio);
        } else {
            params.width = ScreenSizeUtil.getScreenWidth(mDialogContext) - SizeTransferUtil.dip2px(mWidthSpare, mDialogContext) * 2;
        }

        mDialogWindow.setAttributes(params);

        super.display();
    }
}
