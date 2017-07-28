package com.shequn.liming.mobaidanche.base

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import com.zhy.autolayout.AutoLayoutActivity

/**
 * Created by Liming on 2017/7/25.
 */
open abstract class BaseActivity: AutoLayoutActivity(){


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initData()
        initListener()
    }

    abstract fun initData()

    abstract fun initListener()


    /**
     * 设置透明状态栏与导航栏
     * @param navi true不设置导航栏|false设置导航栏
     */
    fun setStatusBar() {
        //api>21,全透明状态栏和导航栏;api>19,半透明状态栏和导航栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //半透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}