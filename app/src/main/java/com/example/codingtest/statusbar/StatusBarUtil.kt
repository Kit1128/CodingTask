package com.example.codingtest.statusbar

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi


/**
 * Created by 赵晨璞
 */
object StatusBarUtil {

    private const val TAG = "StatusBarUtil"

    fun getStatusBarHeight(activity: Activity?): Int {
        val resources = activity?.resources
        val resourceId = resources?.getIdentifier("status_bar_height",
            "dimen", "android")
        //获取StatusBar的高度
        return resources?.getDimensionPixelSize(resourceId ?: 0) ?: 0
    }


    /**
     * 修改状态栏颜色，支持4.4以上版本
     *
     * @param activity Activity
     * @param color  Color Int
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        val window = activity.window
        window.statusBarColor = color
    }

    /**
     * 设置状态栏黑色字体图标，
     * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
     *
     * @param activity Activity
     */
    @RequiresApi( Build.VERSION_CODES.M)
    fun statusBarLightMode(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    @RequiresApi( Build.VERSION_CODES.M)
    fun statusBarLightTransparentMode(activity: Activity) {
        statusBarLightTransparentMode(activity.window)
    }

    @RequiresApi( Build.VERSION_CODES.M)
    fun statusBarLightTransparentMode(window: Window) {

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }

        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)


    }

    @RequiresApi( Build.VERSION_CODES.M)
    fun statusBarDarkMode(activity: Activity) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE


    }

    @RequiresApi( Build.VERSION_CODES.M)
    fun statusBarDarkTransparentMode(activity: Activity) {
        statusBarDarkTransparentMode(activity.window)
    }

    @RequiresApi( Build.VERSION_CODES.M)
    fun statusBarDarkTransparentMode(window: Window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE


    }



}