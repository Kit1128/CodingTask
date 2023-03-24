package com.example.codingtest.dialog

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.codingtest.R
import com.example.codingtest.statusbar.StatusBarUtil

open class MyProgressDialog(
    val act: AppCompatActivity,
    theme: Int
) : Dialog(act, theme) {

    var isCancelableSwitch: Boolean = false

    override fun onStart() {

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)
        animationView.setAnimation("loading.json")

        setCancelable(isCancelableSwitch)
        setCanceledOnTouchOutside(false)
        val window = window
        val d = ColorDrawable(ContextCompat.getColor(context, android.R.color.transparent))
        window!!.setBackgroundDrawable(d)
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        StatusBarUtil.statusBarDarkTransparentMode(window)

        super.onStart()
    }

    companion object {

        fun createDialog(act: AppCompatActivity?): MyProgressDialog? {
            return if (act != null && !act.isFinishing) {
                val customMyProgressDialog =
                    MyProgressDialog(
                        act,
                        R.style.AppTheme_Dialog
                    )
                customMyProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                customMyProgressDialog.setContentView(R.layout.dialog_loading)
                customMyProgressDialog
            } else {
                null
            }
        }
    }

}