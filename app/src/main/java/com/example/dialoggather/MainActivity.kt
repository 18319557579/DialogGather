package com.example.dialoggather

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.RoundedCorner
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dialoggather.databinding.ActivityMainBinding
import com.example.dialogpackaged.decorator.StyleDecorator
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
import com.example.utilsgather.ui.SizeTransferUtil
import com.example.utilsgather.ui.screen.ScreenFunctionUtils

class MainActivity : CallbackActivity() {

    lateinit var tempCommonDialog: CommonDialog  //临时存储，用于测试CommonDialog实例的复用

    lateinit var tempTimerDialog: TimerDecorator  //临时存储用于测试TimerDecorator实例的复用

    lateinit var tempStyleDialog: StyleDecorator  //临时存储用于测试TimerDecorator实例的复用

    private val mainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        ScreenFunctionUtils.hideActionBar(this)

        GuideSettings.set(
            mainBinding.lvShowDialog, arrayOf(
                GuideItemEntity("去FloatLayer测试界面") {
                    startActivity(Intent(this, FloatLayerTestActivity::class.java))
                },
                GuideItemEntity("去FloatLayer测试界面，带队列") {
                    startActivity(Intent(this, FloatLayerTest2Activity::class.java))
                },
                GuideItemEntity("基准弹窗") {
                    //该布局的宽度为match_parent，因此如果我们不使用DialogDecorator来设置宽度的话，它将会尽可能地收窄
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    commonDialog.findView<TextView>(R.id.tv_title).setText("这就是标题")
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.display()
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
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.CENTER,
                        0
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("居中，比例偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.CENTER,
                        0.2F
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("居中，长度偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.CENTER,
                        100
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("顶部，比例偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.TOP,
                        1 / 4F
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("顶部，长度偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.TOP,
                        3
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("底部，比例偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.BOTTOM,
                        0.1F
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("底部，长度偏离") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setVerticalGravityAndBias(
                        StyleDecorator.VerticalPosition.BOTTOM,
                        5
                    )
                    styleDecorator.display()
                },
                GuideItemEntity("设置水平占比") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setRatioOrSpare(2 / 3F)
                    styleDecorator.display()
                },
                GuideItemEntity("设置水平留白") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setRatioOrSpare(10)
                    styleDecorator.display()
                },
                GuideItemEntity("点击外部无反应，点击返回键关闭") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setCancelable(StyleDecorator.DismissResponse.RESPONSE_2)
                    styleDecorator.display()
                },
                GuideItemEntity("点击外部关闭，点击返回键无反应") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setCancelable(StyleDecorator.DismissResponse.RESPONSE_3)
                    styleDecorator.display()
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
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setCancelable(StyleDecorator.DismissResponse.RESPONSE_4)
                    styleDecorator.display()
                },
                GuideItemEntity("外部完全不暗") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setAlphaAndDimAmount(1F, 0F);
                    styleDecorator.display()
                },
                GuideItemEntity("外部全暗") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setAlphaAndDimAmount(1F, 1F);
                    styleDecorator.display()
                },
                GuideItemEntity("外部比正常稍亮") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setAlphaAndDimAmount(1F, 0.2F);
                    styleDecorator.display()
                },
                GuideItemEntity("内部很透明") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setAlphaAndDimAmount(0.3F, 0F);
                    styleDecorator.display()

                },
                GuideItemEntity("修改GradientDrawable背景:圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    //这里走的是GradientDrawable条件
                    styleDecorator.setBackgroundCornerRadius(50)
                    styleDecorator.display()

                    //todo 调用这里后，背景的颜色将会被修改；此时再调用上面的，由于上面不会去设置颜色，因此颜色将被修改
                },
                GuideItemEntity("GradientDrawable背景:圆角半径+背景（半透明背景）") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    //这里走的是GradientDrawable条件
                    styleDecorator.setBackgroundCornerRadius(35, Color.parseColor("#80C55050"))
                    styleDecorator.display()
                },

                GuideItemEntity("GradientDrawable背景:分别设置每个圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    //这里走的是GradientDrawable条件
                    styleDecorator.setBackgroundCornerRadius2(intArrayOf(10, 20, 30, 40))
                    styleDecorator.display()
                },

                GuideItemEntity("没有背景的:设置圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setBackgroundCornerRadius(10)
                    styleDecorator.display()
                },
                GuideItemEntity("没有背景的:设置圆角半径+背景") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setBackgroundCornerRadius(20, Color.parseColor("#FFD700"))
                    styleDecorator.display()
                },
                GuideItemEntity("纯色背景的:设置圆角半径") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc_bgcolor
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setBackgroundCornerRadius(30)
                    styleDecorator.display()
                },
                GuideItemEntity("纯色背景的:设置圆角半径+背景") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.layout_txc_bgcolor
                        )
                    val styleDecorator =
                        StyleDecorator(
                            commonDialog
                        )
                    styleDecorator.setBackgroundCornerRadius(30, Color.parseColor("#90EE90"))
                    styleDecorator.display()
                },

                GuideItemEntity("封装好的游戏管家弹窗，都使用预设内容") {
                    val gamestickNormalDialog = GamestickNormalDialog(this)
                    val styleDecorator =
                        StyleDecorator(
                            gamestickNormalDialog
                        )
                    styleDecorator.display()
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
                    val styleDecorator =
                        StyleDecorator(
                            gamestickNormalDialog
                        )
                    styleDecorator.display()
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
                    StyleDecorator(
                        slotsFeedbackDialog
                    ).apply {
                        setRatioOrSpare(0.5F)
                        setVerticalGravityAndBias(StyleDecorator.VerticalPosition.CENTER, 0)
                        setCancelable(StyleDecorator.DismissResponse.RESPONSE_4)
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
                    StyleDecorator(
                        gamestickRadioDialog
                    ).apply {
                        setVerticalGravityAndBias(StyleDecorator.VerticalPosition.BOTTOM, 50)
                        setRatioOrSpare(16)
                        setCancelable(StyleDecorator.DismissResponse.RESPONSE_2)
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
                    StyleDecorator(
                        gamestickRadioDialog
                    ).apply {
                        setVerticalGravityAndBias(StyleDecorator.VerticalPosition.TOP, 0.1F)
                        setRatioOrSpare(50)
                        setCancelable(StyleDecorator.DismissResponse.RESPONSE_4)
                        display()
                    }
                },

                GuideItemEntity("自定义动画") {
                    val commonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                    StyleDecorator(
                        commonDialog
                    ).apply {
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
                    StyleDecorator(
                        commonDialog
                    ).apply {
                        setAnimation(android.R.style.Animation_Dialog)
                        display()
                    }
                },

                //(啥都不会回调)
                GuideItemEntity("3 仅创建弹窗，保存到内存") {
                    tempCommonDialog =
                        CommonDialog(
                            this,
                            R.layout.dialogpackaged_layout_gamestick_confirmation
                        )
                },
                //(第二次不回调onCreate)
                GuideItemEntity("展示之前创建的弹窗") {
                    tempCommonDialog.display()
                },
                GuideItemEntity("展示之前创建的弹窗，取消按钮用dismiss进行关闭") {
                    tempCommonDialog.findView<Button>(R.id.btn_cancel).setOnClickListener {
                        tempCommonDialog.dismiss()
                    }
                    StyleDecorator(
                        tempCommonDialog
                    ).apply {
                        display()
                    }
                },
                GuideItemEntity("展示之前创建的弹窗，取消按钮用cancel进行关闭") {
                    tempCommonDialog.findView<Button>(R.id.btn_cancel).setOnClickListener {
                        tempCommonDialog.cancel()
                    }
                    StyleDecorator(
                        tempCommonDialog
                    ).apply {
                        display()
                    }
                },

                GuideItemEntity("4 只用样式装饰器，DialogDecorator(CommonDialog)") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation);
                    StyleDecorator(
                        commonDialog
                    ).display()
                },
                GuideItemEntity("只用定时装饰器，TimerDecorator(CommonDialog)") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation).apply {
                            findView<Button>(R.id.btn_cancel).setOnClickListener { dismiss() }
                        }
                    TimerDecorator(commonDialog).apply {
                        setDismissDelay(6000)
                    }.display()
                },
                //其实下面这两效果一样
                GuideItemEntity("定时装饰器+样式装饰器，DialogDecorator(TimerDecorator(CommonDialog))") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation).apply {
                        findView<Button>(R.id.btn_cancel).setOnClickListener { dismiss() }
                    }
                    StyleDecorator(
                        TimerDecorator(commonDialog)
                    ).display()
                },
                GuideItemEntity("样式装饰器+定时装饰器，TimerDecorator(DialogDecorator(CommonDialog))") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation).apply {
                        findView<Button>(R.id.btn_cancel).setOnClickListener { dismiss() }
                    }
                    TimerDecorator(
                        StyleDecorator(
                            commonDialog
                        )
                    ).display()
                },

                //这里是为了测试TimerDecorator装饰的弹窗可以进行实例复用
                GuideItemEntity("5 创建TimerDecorator装饰的弹窗，并保存到内存") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation)
                    tempTimerDialog = TimerDecorator(commonDialog)
                },
                GuideItemEntity("复用TimerDecorator实例") {
                    tempTimerDialog.display()
                },

                //这里是为了测试StyleDecorator装饰的弹窗可以进行实例复用
                GuideItemEntity("6 创建StyleDecorator装饰的弹窗，并保存到内存") {
                    val commonDialog = CommonDialog(this, R.layout.dialogpackaged_layout_gamestick_confirmation)
                    tempStyleDialog = StyleDecorator(commonDialog)
                },


                GuideItemEntity("测试一个原始弹窗，是SSL证书校验错误的弹窗") {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("提示")
                        .setMessage("当前网站安全证书已过期或不可信\n是否继续浏览?")
                        .setPositiveButton("继续浏览") { dialog, which ->
//                            handler.proceed()
                        }
                        .setNegativeButton("返回上一页") { dialog, which ->
//                            handler.cancel()
                        }
                        .create()
                        .show()
                },

                GuideItemEntity("带Gif的弹窗") {
                    val commonDialog = CommonDialog(this, R.layout.layout_loading_gif).apply {
                        val ivLoading = findView<ImageView>(R.id.iv_loading)

                        //用Glide实现圆角
                        val options = RequestOptions().transform(RoundedCorners(SizeTransferUtil.dp2px(2, this@MainActivity)))
                        //用Glide加载Gif
                        Glide.with(this@MainActivity).load(R.drawable.loading_juhua).apply(options).into(ivLoading)
                    }
                    val tempStyleDialog = StyleDecorator(commonDialog).apply {
                        setVerticalGravityAndBias(StyleDecorator.VerticalPosition.CENTER, 0)
                        setAlphaAndDimAmount(0.9F, 0F)
//                        setRatioOrSpare(0.1F)
                        setCancelable(StyleDecorator.DismissResponse.RESPONSE_4)

//                        setBackgroundCornerRadius(50)
                        setAnimation(R.style.loadingjuhuaAnim)
                    }

                    val timerDecorator = TimerDecorator(tempStyleDialog).apply {
                        setDismissDelay(5000)
                    }

                    timerDecorator.display()
                },
            )
        )


    }
}