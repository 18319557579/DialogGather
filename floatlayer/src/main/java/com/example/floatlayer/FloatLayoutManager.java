package com.example.floatlayer;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.AnimRes;
import androidx.annotation.LayoutRes;
import androidx.core.graphics.ColorUtils;

import com.example.floatlayer.layer.FloatLayer;
import com.example.floatlayer.layer.LayerCallback;
import com.example.floatlayer.storage.FloatLayerItem;
import com.example.floatlayer.storage.FloatLayerStorage;
import com.example.utilsgather.handler.HandlerUI;
import com.example.utilsgather.logcat.LogUtil;

import com.example.utilsgather.random.StringRandomUtil;
import com.example.utilsgather.ui.SizeTransferUtil;
import com.example.utilsgather.ui.screen.ScreenSizeUtil;

public class FloatLayoutManager {
    private FloatLayoutManager(){}
    private static class FloatLayoutManagerHolder {
        private static final FloatLayoutManager INSTANCE = new FloatLayoutManager();
    }
    public static FloatLayoutManager getInstance() {
        return FloatLayoutManagerHolder.INSTANCE;
    }

    FloatLayerStorage floatLayerStorage = new FloatLayerStorage();  //用于存储当前展示和队列中的FloatLayer

    public void show(Config config, FloatLayer floatLayer, String label, int priority) {
        HandlerUI.runOnUI(new Runnable() {
            @Override
            public void run() {
                FrameLayout hostLayout = floatLayer.hostLayout;
                FloatLayerItem floatLayerItem = new FloatLayerItem(floatLayer, config);
                floatLayer.setLayerCallback(new MyLayerCallback(hostLayout, floatLayer, config, priority, label));
                floatLayerStorage.putFloatLayerItem(hostLayout, label, priority, floatLayerItem);

                captureAndShow(hostLayout, label);
            }
        });
    }

    //全都主线程
    public void show(Config config, FloatLayer floatLayer) {
        show(config, floatLayer, StringRandomUtil.getRandomString(), 0);
    }

    /**
     * 移除某宿主下的所有
     */
    public void removeHost(FrameLayout frameLayout) {
        LogUtil.d("将该宿主下的所有干掉");
        //todo 实际上当前那个还没有干掉，是不是可以考虑用Handler来干掉。
        //todo 会不会存在内容泄露的情况，因为宿主在Activity onDestroy后还回调
        HandlerUI.runOnUI(new Runnable() {
            @Override
            public void run() {
                floatLayerStorage.removeHost(frameLayout);
            }
        });
    }

    /**
     * 先判断当前是否有正在展示的，如果没有则拿第一个元素进行展示
     */
    public void captureAndShow(FrameLayout frameLayout, String label) {
        if (floatLayerStorage.isShowing(frameLayout, label))
            return;

        FloatLayerItem floatLayerItem = floatLayerStorage.getFloatLayerItem(frameLayout, label);
        if (floatLayerItem == null)  //如果队列里面没有元素了，那么结束
            return;
        managerShow(floatLayerItem.config, floatLayerItem.floatLayer);

        LogUtil.d("又展示了一个");
    }

    private void managerShow(Config config, FloatLayer floatLayer) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = config.verticalLocation | config.horizontalLocation;

        if (config.horizontalLocation == Config.HORIZONTAL_LEFT) {
            layoutParams.leftMargin = SizeTransferUtil.dip2px(config.horizontalMargin, floatLayer.getContext());
        } else if (config.horizontalLocation == Config.HORIZONTAL_RIGHT) {
            layoutParams.rightMargin = SizeTransferUtil.dip2px(config.horizontalMargin, floatLayer.getContext());
        }

        if (config.verticalLocation == Config.VERTICAL_TOP) {
            layoutParams.topMargin = SizeTransferUtil.dip2px(config.verticalMargin, floatLayer.getContext());
        } else if (config.verticalLocation == Config.VERTICAL_BOTTOM) {
            layoutParams.bottomMargin = SizeTransferUtil.dip2px(config.verticalMargin, floatLayer.getContext());
        }

        layoutParams.width = config.lengthType ? SizeTransferUtil.dip2px(config.size, floatLayer.getContext()) :
                (int) (ScreenSizeUtil.getScreenWidth(floatLayer.getContext()) * config.size);
        floatLayer.setLayoutParams(layoutParams);

        if (config.radius != -1) {
            setRadius(floatLayer, config.radius);
        }
        setBackgroundAlpha(floatLayer, config.bgAlpha);
        setSelfAlpha(floatLayer, config.selfAlpha);
        setAnimation(floatLayer, config);
        setDismissAnim(floatLayer, config);

        floatLayer.show();
        setDelayAutoDismiss(floatLayer, config);
    }

    private void setRadius(FloatLayer floatLayer, float cornerRadius) {
        Drawable background = floatLayer.soleChildView.getBackground();  //先拿到dialog的背景
        //如果dialog的资源view是没有背景的，那就设置一个白色的背景
        if (background == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(Color.BLACK);
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, floatLayer.getContext()));
            floatLayer.soleChildView.setBackground(gradientDrawable);

            //如果为GradientDrawable，则修改其拐角半径
        } else if (background instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) background;
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, floatLayer.getContext()));

            //发现如果背景为纯色，那么获取到的背景将会是ColorDrawable类型的
        } else if (background instanceof ColorDrawable) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(((ColorDrawable)background).getColor());
            gradientDrawable.setCornerRadius(SizeTransferUtil.dip2px(cornerRadius, floatLayer.getContext()));
            floatLayer.soleChildView.setBackground(gradientDrawable);
        }
    }

    private void setBackgroundAlpha(FloatLayer floatLayer, float alpha) {
        if (alpha == -1) return;

        Drawable background = floatLayer.soleChildView.getBackground();  //先拿到dialog的背景
        //如果dialog的资源view是没有背景的，那就设置一个白色的背景
        if (background == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setColor(ColorUtils.setAlphaComponent(Color.BLACK, (int) (alpha * 255)));
            floatLayer.setBackground(gradientDrawable);

            //如果为GradientDrawable，则修改其拐角半径
        } else if (background instanceof GradientDrawable) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                int currentBgColor = ((GradientDrawable)background).getColor().getDefaultColor();
                int modifiedBgColor = ColorUtils.setAlphaComponent(currentBgColor, (int) (alpha * 255));
                ((GradientDrawable) background).setColor(modifiedBgColor);
            }

            //发现如果背景为纯色，那么获取到的背景将会是ColorDrawable类型的
        } else if (background instanceof ColorDrawable) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                int currentBgColor = ((ColorDrawable)background).getColor();
                int modifiedBgColor = ColorUtils.setAlphaComponent(currentBgColor, (int) (alpha * 255));
                ((ColorDrawable) background).setColor(modifiedBgColor);
            }
        }
    }

    private void setSelfAlpha(FloatLayer floatLayer, float alpha) {
        if (alpha == -1) return;

        View childView = floatLayer.soleChildView;
        childView.setAlpha(alpha);
    }

    private void setAnimation(FloatLayer floatLayer, Config config) {
        if (config.showAnimRes == -1) return;

        Animation animation = AnimationUtils.loadAnimation(floatLayer.getContext(), config.showAnimRes);
        floatLayer.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                LogUtil.d("测试 " + "onAnimationStart 回调");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                LogUtil.d("测试 " + "onAnimationEnd 回调");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                LogUtil.d("测试 " + "onAnimationRepeat 回调");
            }
        });
    }

    private void setDismissAnim(FloatLayer floatLayer, Config config) {
        if (config.dismissAnimRes == -1) return;
        floatLayer.dismissAnimRes = config.dismissAnimRes;
    }

    private void setDelayAutoDismiss(FloatLayer floatLayer, Config config) {
        if (config.delayMillis == -1) return;
        //todo 这样不好，传了2个并不是关联的参数，后期考虑用装饰器模式优化
        new Handler(Looper.getMainLooper()).postDelayed(new DelayRunnable(floatLayer, config.dismissAnimRes), config.delayMillis);
    }

    public static class DelayRunnable implements Runnable {
        private final FloatLayer floatLayer;
        @AnimRes
        public int dismissAnimRes;

        public DelayRunnable(FloatLayer floatLayer, @AnimRes int dismissAnimRes) {
            this.floatLayer = floatLayer;
            this.dismissAnimRes = dismissAnimRes;
        }

        @Override
        public void run() {
            if (! floatLayer.exist()) {
                LogUtil.d("FloatLayer已经提前消失");
                return;
            }

            if (dismissAnimRes == -1) {
                floatLayer.realDismiss();  //下面将会应用出场动画，所以要直接调用真实的dismiss
                return;
            }

            Animation animation = AnimationUtils.loadAnimation(floatLayer.getContext(), dismissAnimRes);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //todo 这里为什么一定要用Handler，否则会闪退
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            floatLayer.realDismiss();
                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            floatLayer.startAnimation(animation);
        }
    }

    class MyLayerCallback implements LayerCallback {
        private final FrameLayout frameLayout;
        private final FloatLayer floatLayer;
        private final Config config;
        private int priority = 0;
        private String label = "DEFAULT_LABEL";

        public MyLayerCallback(FrameLayout frameLayout, FloatLayer floatLayer, Config config) {
            this.frameLayout = frameLayout;
            this.floatLayer = floatLayer;
            this.config = config;
        }

        public MyLayerCallback(FrameLayout frameLayout, FloatLayer floatLayer, Config config, int priority) {
            this.frameLayout = frameLayout;
            this.floatLayer = floatLayer;
            this.config = config;
            this.priority = priority;
        }

        public MyLayerCallback(FrameLayout frameLayout, FloatLayer floatLayer, Config config, String label) {
            this.frameLayout = frameLayout;
            this.floatLayer = floatLayer;
            this.config = config;
            this.label = label;
        }

        public MyLayerCallback(FrameLayout frameLayout, FloatLayer floatLayer, Config config, int priority, String label) {
            this.frameLayout = frameLayout;
            this.floatLayer = floatLayer;
            this.config = config;
            this.priority = priority;
            this.label = label;
        }

        @Override
        public void onShow() {

        }

        @Override
        public void onDismiss() {
            floatLayerStorage.setNoShow(frameLayout, label);
            captureAndShow(frameLayout, label);
        }
    }
}
