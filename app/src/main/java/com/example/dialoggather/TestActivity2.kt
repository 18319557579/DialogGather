package com.example.dialoggather

import android.os.Bundle
import android.widget.Toast
import com.example.dialoggather.databinding.ActivityTest2Binding
import com.example.messagedialog.MessageDialogManager
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
                GuideItemEntity("1") {
                    
                },
                GuideItemEntity("2") {
                    
                },
                GuideItemEntity("3") {
                    
                },
                GuideItemEntity("4") {
                    
                },
                GuideItemEntity("5") {
                    
                },
                GuideItemEntity("6") {
                    
                },
                GuideItemEntity("7") {
                    
                },
                GuideItemEntity("8") {
                    
                },
                GuideItemEntity("9") {
                    
                },
                GuideItemEntity("10") {
                    
                },
            )
        )
    }
}