package com.example.dialoggather

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
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

        GuideSettings.set(
            mainBinding.lvShowDialog, arrayOf(
                GuideItemEntity("基准弹窗") {
                    //该布局的宽度为match_parent，因此如果我们不使用DialogDecorator来设置宽度的话，它将会尽可能地收窄
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    commonDialog.findView<TextView>(R.id.tv_title).setText("这就是标题")
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.display()
                },
                GuideItemEntity("不使用装饰类") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    commonDialog.display()
                },
                GuideItemEntity("居中，不偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.CENTER,
                        0
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("居中，比例偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.CENTER,
                        0.2F
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("居中，长度偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.CENTER,
                        100
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("顶部，比例偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.TOP,
                        1 / 4F
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("顶部，长度偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.TOP,
                        3
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("底部，比例偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.BOTTOM,
                        0.1F
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("底部，长度偏离") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.BOTTOM,
                        5
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("设置水平占比") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setRatioOrSpare(2 / 3F)
                    dialogDecorator.display()
                },
                GuideItemEntity("设置水平留白") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setRatioOrSpare(10)
                    dialogDecorator.display()
                },
                GuideItemEntity("点击外部无反应，点击返回键关闭") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setCancelable(DialogDecorator.DismissResponse.RESPONSE_2)
                    dialogDecorator.display()
                },
                GuideItemEntity("点击外部关闭，点击返回键无反应") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setCancelable(DialogDecorator.DismissResponse.RESPONSE_3)
                    dialogDecorator.display()
                },
                GuideItemEntity("点击外部无反应，点击返回键无反应") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    commonDialog.findView<Button>(R.id.btn_cancel).setOnClickListener {
                        commonDialog.dismiss()
                    }
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setCancelable(DialogDecorator.DismissResponse.RESPONSE_4)
                    dialogDecorator.display()
                },
                GuideItemEntity("外部完全不暗") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setAlphaAndDimAmount(1F, 0F);
                    dialogDecorator.display()
                },
                GuideItemEntity("外部全暗") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setAlphaAndDimAmount(1F, 1F);
                    dialogDecorator.display()
                },
                GuideItemEntity("外部比正常稍亮") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setAlphaAndDimAmount(1F, 0.2F);
                    dialogDecorator.display()
                },
                GuideItemEntity("内部很透明") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setAlphaAndDimAmount(0.3F, 0F);
                    dialogDecorator.display()

                },
                GuideItemEntity("修改GradientDrawable背景:圆角半径") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    //这里走的是GradientDrawable条件
                    dialogDecorator.setBackgroundCornerRadius(50)
                    dialogDecorator.display()

                    //todo 调用这里后，背景的颜色将会被修改；此时再调用上面的，由于上面不会去设置颜色，因此颜色将被修改
                },
                GuideItemEntity("修改GradientDrawable背景:圆角半径+背景（半透明背景）") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    //这里走的是GradientDrawable条件
                    dialogDecorator.setBackgroundCornerRadius(35, Color.parseColor("#80C55050"))
                    dialogDecorator.display()
                },

                GuideItemEntity("修改GradientDrawable背景:分别设置每个圆角半径") {
                    val commonDialog = CommonDialog(this, R.layout.layout_gamestick_selector)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    //这里走的是GradientDrawable条件
                    dialogDecorator.setBackgroundCornerRadius2(intArrayOf(10, 20, 30, 40))
                    dialogDecorator.display()
                },

                GuideItemEntity("没有背景的:设置圆角半径") {
                    val commonDialog = CommonDialog(this, R.layout.layout_txc)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setBackgroundCornerRadius(10)
                    dialogDecorator.display()
                },
                GuideItemEntity("没有背景的:设置圆角半径+背景") {
                    val commonDialog = CommonDialog(this, R.layout.layout_txc)
                    val dialogDecorator = DialogDecorator(commonDialog)
                    dialogDecorator.setBackgroundCornerRadius(20, Color.parseColor("#FFD700"))
                    dialogDecorator.display()
                },
            )
        )


    }
}