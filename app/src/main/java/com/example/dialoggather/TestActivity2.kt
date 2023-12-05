package com.example.dialoggather

import android.os.Bundle
import android.widget.Toast
import com.example.dialoggather.databinding.ActivityTest2Binding
import com.example.messagedialog.MessageDialogManager
import com.example.messagedialog.float_layer.Config
import com.example.messagedialog.float_layer.FloatLayoutManager
import com.example.messagedialog.float_layer.layer.JumpFloatLayer
import com.example.utilsgather.lifecycle_callback.CallbackActivity
import com.example.utilsgather.list_guide.GuideItemEntity
import com.example.utilsgather.list_guide.GuideSettings

class TestActivity2 : CallbackActivity() {

    private val mBinding: ActivityTest2Binding by lazy {
        ActivityTest2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        val frameLayoutContent = mBinding.flContent
        frameLayoutContent.setOnClickListener {
            Toast.makeText(this, "点击到了内容", Toast.LENGTH_SHORT).show()
        }
        
        GuideSettings.set(
            mBinding.lvShowMessage, arrayOf(
                GuideItemEntity("弹出MessageDialog") {
                    MessageDialogManager.getInstance().tryToShow(frameLayoutContent, "百度")
                },
                GuideItemEntity("长度类型，size 300") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("比例类型，size 0.7") {
                    val config = Config().apply {
                        lengthType = false
                        size = 0.7F
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("左上") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_LEFT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("右上") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_RIGHT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("中上") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_TOP
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("中左") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_LEFT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("中右") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_RIGHT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("中中") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("左下") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_LEFT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("右下") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_RIGHT
                        horizontalMargin = 5

                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("中下") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_BOTTOM
                        verticalMargin = 10
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("用LayoutId进行展示") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
                GuideItemEntity("用FloatLayout进行展示") {
                    val config = Config().apply {
                        lengthType = true
                        size = 300F

                        horizontalLocation = Config.HORIZONTAL_CENTER
                        horizontalMargin = 5  //无效

                        verticalLocation = Config.VERTICAL_CENTER
                        verticalMargin = 10  //无效
                    }
                    val jumpFloatLayer = JumpFloatLayer(this@TestActivity2)
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, jumpFloatLayer)
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
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
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
                    FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
                },
            )
        )
    }
}