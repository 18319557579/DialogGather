package com.example.dialoggather

import android.os.Bundle
import android.widget.FrameLayout
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
            GuideItemEntity("相同宿主，随机标识") {
                val config = Config().apply {
                    lengthType = true
                    size = 300F

                    horizontalLocation = Config.HORIZONTAL_CENTER
                    verticalLocation = Config.VERTICAL_CENTER
                }
                FloatLayoutManager.getInstance().show(frameLayoutContent, config, R.layout.medi_tiny_message_bar)
            },
        ))
    }
}