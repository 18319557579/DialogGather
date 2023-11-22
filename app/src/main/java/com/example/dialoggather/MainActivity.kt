package com.example.dialoggather

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dialoggather.databinding.ActivityMainBinding
import com.example.dialogpackaged.CommonDialog
import com.example.dialogpackaged.DialogDecorator
import com.example.utilsgather.list_guide.GuideItemEntity
import com.example.utilsgather.list_guide.GuideSettings

class MainActivity : AppCompatActivity() {

    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)

        GuideSettings.set(mainBinding.lvShowDialog, arrayOf(
            GuideItemEntity("基准弹窗") {
                //该布局的宽度为match_parent，因此如果我们不使用DialogDecorator来设置宽度的话，它将会尽可能地收窄
                val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                commonDialog.findView<TextView>(R.id.tv_title).setText("这就是标题")
                val dialogDecorator = DialogDecorator(commonDialog)
                dialogDecorator.display()
            }
        ))


    }
}