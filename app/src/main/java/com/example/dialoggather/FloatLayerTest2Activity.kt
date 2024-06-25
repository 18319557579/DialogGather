package com.example.dialoggather

import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.example.floatlayer.Config
import com.example.floatlayer.FloatLayoutManager
import com.example.floatlayer.layer.FloatLayer
import com.example.utilsgather.lifecycle_callback.CallbackActivity
import com.example.utilsgather.list_guide.GuideItemEntity
import com.example.utilsgather.list_guide.GuideSettings
import com.example.utilsgather.random.RandomUtil
import com.example.utilsgather.ui.screen.ScreenFunctionUtils


class FloatLayerTest2Activity : CallbackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_float_layer_test_2)
        ScreenFunctionUtils.hideActionBar(this)

        val frameLayoutContent = findViewById<FrameLayout>(R.id.fl_content_2)
        val frameLayoutContentSecond = findViewById<FrameLayout>(R.id.fl_content_2_second)

        frameLayoutContent.setOnClickListener {
            Toast.makeText(this, "点击到了内容", Toast.LENGTH_SHORT).show()
        }
        frameLayoutContentSecond.setOnClickListener {
            Toast.makeText(this, "点击到了内容", Toast.LENGTH_SHORT).show()
        }

        GuideSettings.set(findViewById(R.id.lv_show_message_2), arrayOf(
            GuideItemEntity("相同宿主，随机标识。随机底部边距，避免重叠") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300);
                }

                FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
            },
            GuideItemEntity("相同宿主，随机标识。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar))
            },

            GuideItemEntity("相同宿主，相同标识。随机底部边距，避免重叠。") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)
                }
                FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar),
                    "SAME_LABEL", 0)
            },
            GuideItemEntity("相同宿主，相同标识。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar),
                    "SAME_LABEL", 0)
            },

            //下面这两个用于测试不同标识，互不干扰
            GuideItemEntity("相同宿主，标识one。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar)
                floatLayer.findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                    setText("我的标识是one")
                    setTextColor(Color.parseColor("#FF0000"))
                }
                FloatLayoutManager.getInstance().show(config, floatLayer,"one", 0)
            },
            GuideItemEntity("相同宿主，标识two。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar)
                floatLayer.findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                    setText("我的标识是two")
                    setTextColor(Color.parseColor("#00FF00"))
                }
                FloatLayoutManager.getInstance().show(config, floatLayer,"two", 0)
            },

            GuideItemEntity("相同宿主，相同标识SAME_LABEL，优先级0。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("优先级 0")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer,"SAME_LABEL", 0)
            },
            GuideItemEntity("相同宿主，相同标识SAME_LABEL，优先级1。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("优先级 1")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer,"SAME_LABEL", 1)
            },
            GuideItemEntity("相同宿主，相同标识SAME_LABEL，优先级3。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 300)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("优先级 3")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer,"SAME_LABEL", 3)
            },

            GuideItemEntity("上方宿主，随机标识。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 150)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContentSecond, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("上方宿主的")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer)
            },

            GuideItemEntity("下方宿主，随机标识。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 150)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("下方宿主的")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer)
            },
            //下面这两个用于测试，虽然标识相同，不同宿主间也不会干扰
            GuideItemEntity("上方宿主，相同标识APPLE。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 150)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContentSecond, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("上方宿主的")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer, "APPLE", 0)
            },
            GuideItemEntity("下方宿主，相同标识APPLE。随机底部边距，避免重叠。3000毫秒后自动消失") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_BOTTOM
                    verticalMargin = RandomUtil.getRandomInt(0, 150)

                    delayMillis = 3000
                }
                val floatLayer = FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar).apply {
                    findView<TextView>(com.example.floatlayer.R.id.flla_jump_title_tv).apply {
                        setText("下方宿主的")
                    }
                }
                FloatLayoutManager.getInstance().show(config, floatLayer, "APPLE", 0)
            },

            //测试动画效果是否可以兼容队列
            GuideItemEntity("相同宿主，相同标识。定时3000毫秒后消失，有入场和出场动画。") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_CENTER

                    showAnimRes = com.example.floatlayer.R.anim.flla_layer_show_anim_1
                    dismissAnimRes = com.example.floatlayer.R.anim.flla_layer_dismiss_anim_2
                    delayMillis = 3000
                }
                FloatLayoutManager.getInstance().show(config, FloatLayer(frameLayoutContent, com.example.floatlayer.R.layout.medi_tiny_message_bar),
                    "SHOW_IN", 0)
            },
        ))
    }
}