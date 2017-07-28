package com.shequn.liming.shiji_newproject.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import java.lang.reflect.Field

object DensityUtil {

    fun dip2px(c: Context, dpValue: Float): Int {
        val scale = c.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(c: Context, pxValue: Float): Int {
        val scale = c.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    fun px2sp(c: Context, pxValue: Float): Int {
        val fontScale = c.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }


    fun sp2px(c: Context, spValue: Float): Int {
        val fontScale = c.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun getScreenW(c: Context): Int {
        return c.resources.displayMetrics.widthPixels
    }

    fun getScreenH(c: Context): Int {
        return c.resources.displayMetrics.heightPixels
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun getScreenRealH(context: Context): Int {
        var h: Int
        val winMgr = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = winMgr.defaultDisplay
        val dm = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(dm)
            h = dm.heightPixels
        } else {
            try {
                val method = Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics::class.java)
                method.invoke(display, dm)
                h = dm.heightPixels
            } catch (e: Exception) {
                display.getMetrics(dm)
                h = dm.heightPixels
            }

        }
        return h
    }

    fun getStatusBarH(context: Context): Int {
        val c: Class<*>
        val obj: Any
        val field: Field
        var statusBarHeight = 0
        try {
            c = Class.forName("com.android.internal.R\$dimen")
            obj = c.newInstance()
            field = c.getField("status_bar_height")
            val x = Integer.parseInt(field.get(obj).toString())
            statusBarHeight = context.resources.getDimensionPixelSize(x)
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

        return statusBarHeight
    }

    fun getNavigationBarrH(c: Context): Int {
        val resources = c.resources
        val identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelOffset(identifier)
    }
}
