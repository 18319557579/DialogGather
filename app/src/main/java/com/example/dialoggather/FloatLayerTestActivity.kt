package com.example.dialoggather

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.floatlayer.Config
import com.example.floatlayer.FloatLayoutManager
import com.example.floatlayer.layer.FloatLayer
import com.example.floatlayer.layer.JumpFloatLayer
import com.example.utilsgather.lifecycle_callback.CallbackActivity
import com.example.utilsgather.list_guide.GuideItemEntity
import com.example.utilsgather.list_guide.GuideSettings
import com.example.utilsgather.ui.screen.ScreenFunctionUtils
import com.example.dialoggather.databinding.ActivityFloatLayerTestBinding

class FloatLayerTestActivity : CallbackActivity() {

    private val mBinding: ActivityFloatLayerTestBinding by lazy {
        ActivityFloatLayerTestBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        ScreenFunctionUtils.hideActionBar(this)

        val frameLayoutContent = mBinding.flContent
        frameLayoutContent.setOnClickListener {
            Toast.makeText(this, "点击到了内容", Toast.LENGTH_SHORT).show()
        }
        
        GuideSettings.set(
            mBinding.lvShowMessage, arrayOf(
                GuideItemEntity("长度类型，size 300") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("比例类型，size 0.7") {
                    val config = Config().apply {
                        lengthType = false
                        size = 0.7F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，左上") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_LEFT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，右上") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_RIGHT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，中上") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，中左") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_LEFT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，中右") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_RIGHT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，中中") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，左下") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_LEFT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，右下") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_RIGHT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定位，中下") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },

                GuideItemEntity("设置圆角弧度，为5dp") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效

                        radius = 5F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("设置圆角弧度，无穷大") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效

                        radius = Float.MAX_VALUE
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },

                GuideItemEntity("设置透明度0.3，给没有背景的") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        bgAlpha = 0.3F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar_nobg))
                },
                GuideItemEntity("设置透明度0.5，给背景类型为<shape>的，即GradientDrawable") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        bgAlpha = 0.5F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("设置透明度0.8，给背景类型为纯色的，即ColorDrawable") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        bgAlpha = 0.8F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar_colorbg))
                },

                GuideItemEntity("设置自身透明度0.5") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        selfAlpha = 0.5F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("设置自身透明度1") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        selfAlpha = 1F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("设置自身透明度0") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        selfAlpha = 0F
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },

                GuideItemEntity("第一种入场动画，位移 + 透明度") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_1
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("第二种入场动画，旋转") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_2
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("第三种入场动画，缩放") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_3
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },

                GuideItemEntity("第一种出场动画，位移") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_1
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("第一种出场动画，位移。+ 第一种入场效果") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 100

                        showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_1
                        dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_1
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },

                GuideItemEntity("定时3000毫秒后消失，无出场动画") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        delayMillis = 3000
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定时3000毫秒后消失，第一种出场动画") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_1
                        delayMillis = 3000
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },
                GuideItemEntity("定时3000毫秒后消失，第一种入场动画 + 第二种出场动画") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_CENTER

                        showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_1
                        dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_2
                        delayMillis = 3000
                    }
                    FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
                },

                GuideItemEntity("喜马拉雅，啥都没") {
                    val config = Config().apply {
                        lengthType = false
                        size = 0.8F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 100

                        radius = Float.MAX_VALUE
                        bgAlpha = 0.9F
                    }
                    val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.flla_layout_ad)
                    FloatLayoutManager.getInstance().show(config, floatLayer)
                },
                GuideItemEntity("喜马拉雅，点击确定浮层消失并Toast") {
                    val config = Config().apply {
                        lengthType = false
                        size = 0.8F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 100

                        radius = Float.MAX_VALUE
                        bgAlpha = 0.9F
                    }
                    val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.flla_layout_ad)
                    floatLayer.findView<TextView>(com.example.floatlayer.R.id.flla_ad_tv_ok).setOnClickListener {
                        floatLayer.dismissMaybeAnim()
                        Toast.makeText(this, "成功订阅！", Toast.LENGTH_SHORT).show()
                    }
                    FloatLayoutManager.getInstance().show(config, floatLayer)
                },
                GuideItemEntity("喜马拉雅，点击确定浮层消失并Toast，带出场动画") {
                    val config = Config().apply {
                        lengthType = false
                        size = 0.8F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 100

                        radius = Float.MAX_VALUE
                        bgAlpha = 0.9F

                        dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_1
                    }
                    val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.flla_layout_ad)
                    floatLayer.findView<TextView>(com.example.floatlayer.R.id.flla_ad_tv_ok).setOnClickListener {
                        floatLayer.dismissMaybeAnim()
                        Toast.makeText(this, "成功订阅！", Toast.LENGTH_SHORT).show()
                    }
                    FloatLayoutManager.getInstance().show(config, floatLayer)
                },

                GuideItemEntity("模仿X浏览器的弹窗。3500毫秒后自动消失，自动消失有动画。手动消失无动画") {
                    val config = Config().apply {
                        lengthType = false
                        size = 0.75F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 100

                        radius = Float.MAX_VALUE
                        delayMillis = 3500

                        showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_1
                        dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_2
                    }
                    val jumpFloatLayer = JumpFloatLayer(frameLayoutContent) {
                        Toast.makeText(this, "跳转到第三方App", Toast.LENGTH_SHORT).show()
                    }
                    jumpFloatLayer.setAppName("小红书")

                    FloatLayoutManager.getInstance().show(config, jumpFloatLayer)
                },
            )
        )
    }
}