package com.example.dialoggather

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dialoggather.databinding.ActivityMainBinding
import com.example.dialogpackaged.decorator.DialogDecorator
import com.example.dialogpackaged.decorator.TimerDecorator
import com.example.dialogpackaged.dialog.CommonDialog
import com.example.dialogpackaged.dialog.GamestickNormalDialog
import com.example.dialogpackaged.dialog.GamestickNormalDialog.EventCallback
import com.example.dialogpackaged.dialog.SlotsFeedbackDialog
import com.example.dialogpackaged.dialog.radio.GamestickRadioDialog
import com.example.utilsgather.lifecycle_callback.CallbackActivity
import com.example.utilsgather.list_guide.GuideItemEntity
import com.example.utilsgather.list_guide.GuideSettings
import com.example.utilsgather.logcat.LogUtil
import com.example.utilsgather.ui.screen.ScreenFunctionUtils

class MainActivity : CallbackActivity() {

    lateinit var tempCommonDialog: CommonDialog

    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        ScreenFunctionUtils.hideActionBar(this)

        GuideSettings.set(
            mainBinding.lvShowDialog, arrayOf(
                GuideItemEntity("基准弹窗") {
                    //该布局的宽度为match_parent，因此如果我们不使用DialogDecorator来设置宽度的话，它将会尽可能地收窄
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    commonDialog.findView<TextView>(R.id.tv_title).setText("这就是标题")
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.display()
                },
                GuideItemEntity("不使用装饰类") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    commonDialog.display()
                },
                GuideItemEntity("居中，不偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.CENTER,
                        0
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("居中，比例偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.CENTER,
                        0.2F
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("居中，长度偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.CENTER,
                        100
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("顶部，比例偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.TOP,
                        1 / 4F
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("顶部，长度偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.TOP,
                        3
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("底部，比例偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.BOTTOM,
                        0.1F
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("底部，长度偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setVerticalGravityAndBias(
                        DialogDecorator.VerticalPosition.BOTTOM,
                        5
                    )
                    dialogDecorator.display()
                },
                GuideItemEntity("设置水平占比") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setRatioOrSpare(2 / 3F)
                    dialogDecorator.display()
                },
                GuideItemEntity("设置水平留白") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setRatioOrSpare(10)
                    dialogDecorator.display()
                },
                GuideItemEntity("点击外部无反应，点击返回键关闭") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setCancelable(DialogDecorator.DismissResponse.RESPONSE_2)
                    dialogDecorator.display()
                },
                GuideItemEntity("点击外部关闭，点击返回键无反应") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setCancelable(DialogDecorator.DismissResponse.RESPONSE_3)
                    dialogDecorator.display()
                },
                GuideItemEntity("点击外部无反应，点击返回键无反应") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    commonDialog.findView<Button>(R.id.btn_cancel).setOnClickListener {
                        commonDialog.dismiss()
                    }
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setCancelable(DialogDecorator.DismissResponse.RESPONSE_4)
                    dialogDecorator.display()
                },
                GuideItemEntity("外部完全不暗") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setAlphaAndDimAmount(1F, 0F);
                    dialogDecorator.display()
                },
                GuideItemEntity("外部全暗") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setAlphaAndDimAmount(1F, 1F);
                    dialogDecorator.display()
                },
                GuideItemEntity("外部比正常稍亮") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setAlphaAndDimAmount(1F, 0.2F);
                    dialogDecorator.display()
                },
                GuideItemEntity("内部很透明") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setAlphaAndDimAmount(0.3F, 0F);
                    dialogDecorator.display()

                },
                GuideItemEntity("修改GradientDrawable背景:圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    //这里走的是GradientDrawable条件
                    dialogDecorator.setBackgroundCornerRadius(50)
                    dialogDecorator.display()

                    //todo 调用这里后，背景的颜色将会被修改；此时再调用上面的，由于上面不会去设置颜色，因此颜色将被修改
                },
                GuideItemEntity("GradientDrawable背景:圆角半径+背景（半透明背景）") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    //这里走的是GradientDrawable条件
                    dialogDecorator.setBackgroundCornerRadius(35, Color.parseColor("#80C55050"))
                    dialogDecorator.display()
                },

                GuideItemEntity("GradientDrawable背景:分别设置每个圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    //这里走的是GradientDrawable条件
                    dialogDecorator.setBackgroundCornerRadius2(intArrayOf(10, 20, 30, 40))
                    dialogDecorator.display()
                },

                GuideItemEntity("没有背景的:设置圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setBackgroundCornerRadius(10)
                    dialogDecorator.display()
                },
                GuideItemEntity("没有背景的:设置圆角半径+背景") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setBackgroundCornerRadius(20, Color.parseColor("#FFD700"))
                    dialogDecorator.display()
                },
                GuideItemEntity("纯色背景的:设置圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc_bgcolor
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setBackgroundCornerRadius(30)
                    dialogDecorator.display()
                },
                GuideItemEntity("纯色背景的:设置圆角半径+背景") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc_bgcolor
                        )
                    val dialogDecorator =
                        DialogDecorator(
                            commonDialog
                        )
                    dialogDecorator.setBackgroundCornerRadius(30, Color.parseColor("#90EE90"))
                    dialogDecorator.display()
                },

                GuideItemEntity("封装好的游戏管家弹窗，都使用预设内容") {
                    val gamestickNormalDialog = GamestickNormalDialog(this)
                    val dialogDecorator = DialogDecorator(gamestickNormalDialog)
                    dialogDecorator.display()
                },
                GuideItemEntity("封装好的游戏管家弹窗，自定义内容") {
                    val gamestickNormalDialog = GamestickNormalDialog(this)
                    gamestickNormalDialog.setTitle("提示")
                    gamestickNormalDialog.setContent("是否跳转到其他应用")
                    gamestickNormalDialog.setCancelText("不跳转")
                    gamestickNormalDialog.setConfirmText("跳转")
                    gamestickNormalDialog.setEventCallback(object : EventCallback {
                        override fun onConfirmClick() {
                            Toast.makeText(this@MainActivity, "即将跳转到其他应用", Toast.LENGTH_SHORT).show()
                            gamestickNormalDialog.dismiss()
                        }
                        override fun onCancelClick() {
                            gamestickNormalDialog.dismiss()
                        }
                    })
                    val dialogDecorator = DialogDecorator(gamestickNormalDialog)
                    dialogDecorator.display()
                },

                GuideItemEntity("Slots反馈弹窗，横屏时弹出") {
                    val slotsFeedbackDialog = SlotsFeedbackDialog(this).apply {
                        setTvTitleText("FEEDBACK")
                        setTvContentText("Thank you for your comments! We will definitely handle your feedback seriously!")
                        setEventCallback(object : SlotsFeedbackDialog.EventCallback {
                            override fun onClose() {
                                LogUtil.d("执行关闭的埋点上报")
                            }

                            override fun onCommit(input: String?) {
                                LogUtil.d("执行提交的买点上报：$input")
                            }
                        })
                    }
                    //由于是横屏的弹窗效果，因此要配合DialogDecorator进行使用
                    DialogDecorator(slotsFeedbackDialog).apply {
                        setRatioOrSpare(0.5F)
                        setVerticalGravityAndBias(DialogDecorator.VerticalPosition.CENTER, 0)
                        setCancelable(DialogDecorator.DismissResponse.RESPONSE_4)
                        display()
                    }
                },

                GuideItemEntity("Gamestick单选弹窗，4选项") {
                    val gamestickRadioDialog = GamestickRadioDialog(this, arrayOf("燃起斗志", "吃鸡神曲", "王者战歌", "无音乐"),
                        2, object : GamestickRadioDialog.OnDialogClickListener {
                            override fun onLeftButtonClick() {
                                LogUtil.d("按了取消")
                            }

                            override fun onRightButtonClick(index: Int) {
                                LogUtil.d("按了确定: $index")
                            }

                            override fun onItemSelected(index: Int) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "选择了 $index",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onDisappear() {
                                LogUtil.d("弹窗消失埋点上报")
                            }

                        })
                    DialogDecorator(gamestickRadioDialog).apply {
                        setVerticalGravityAndBias(DialogDecorator.VerticalPosition.BOTTOM, 50)
                        setRatioOrSpare(16)
                        setCancelable(DialogDecorator.DismissResponse.RESPONSE_2)
                        display()
                    }
                },
                GuideItemEntity("Gamestick单选弹窗，3选项") {
                    val gamestickRadioDialog = GamestickRadioDialog(this, arrayOf("男", "女", "不愿透露"),
                        2, object : GamestickRadioDialog.OnDialogClickListener {
                            override fun onLeftButtonClick() {
                                LogUtil.d("按了取消")
                            }

                            override fun onRightButtonClick(index: Int) {
                                LogUtil.d("按了确定: $index")
                            }

                            override fun onItemSelected(index: Int) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "选择了 $index",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onDisappear() {
                                LogUtil.d("弹窗消失埋点上报")
                            }

                        })
                    gamestickRadioDialog.setTitleStr("修改性别")
                    gamestickRadioDialog.setCancelStr("不修改了")
                    gamestickRadioDialog.setConfirmStr("确定修改")
                    DialogDecorator(gamestickRadioDialog).apply {
                        setVerticalGravityAndBias(DialogDecorator.VerticalPosition.TOP, 0.1F)
                        setRatioOrSpare(50)
                        setCancelable(DialogDecorator.DismissResponse.RESPONSE_4)
                        display()
                    }
                },

                GuideItemEntity("自定义动画") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    DialogDecorator(commonDialog).apply {
                        setAnimation(R.style.dialogWindowAnim)
                        display()
                    }
                },
                GuideItemEntity("使用android.R.style.Animation_Dialog动画。但是和不设置还是有区别，后者进入时会有向上，退出时向下的效果") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    DialogDecorator(commonDialog).apply {
                        setAnimation(android.R.style.Animation_Dialog)
                        display()
                    }
                },

                GuideItemEntity("3.0 仅创建弹窗，但是不展示(啥都不会回调)") {
                    tempCommonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                },
                //(第二次不回调onCreate)
                GuideItemEntity("3.1 展示之前创建的弹窗") {
                    tempCommonDialog.display()
                },
                GuideItemEntity("3.2 展示之前创建的弹窗，取消按钮用dismiss进行关闭") {
                    tempCommonDialog.findView<Button>(R.id.btn_cancel).setOnClickListener {
                        tempCommonDialog.dismiss()
                    }
                    DialogDecorator(tempCommonDialog).apply {
                        display()
                    }
                },
                GuideItemEntity("3.3 展示之前创建的弹窗，取消按钮用cancel进行关闭") {
                    tempCommonDialog.findView<Button>(R.id.btn_cancel).setOnClickListener {
                        tempCommonDialog.cancel()
                    }
                    DialogDecorator(tempCommonDialog).apply {
                        display()
                    }
                },

                GuideItemEntity("4.0 定时关闭的弹窗") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation).apply {
                            findView<Button>(R.id.btn_cancel).setOnClickListener { dismiss() }
                        }
                    /*val dialogDecorator = DialogDecorator(commonDialog).apply {
                        display()
                    }*/
                    TimerDecorator(commonDialog, 3000).display()
                },
            )
        )


    }
}