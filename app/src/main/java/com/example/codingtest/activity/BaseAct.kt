package com.example.codingtest.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.codingtest.dialog.MyProgressDialog

abstract class BaseAct<VB: ViewBinding>: AppCompatActivity() {

    protected lateinit var binding: VB

    protected var myMyProgressDialog: MyProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    open fun startLoading() {
        if (!isFinishing) {
            if (myMyProgressDialog?.isShowing != true) {
                myMyProgressDialog?.dismiss()
                myMyProgressDialog = MyProgressDialog.createDialog(this)
                myMyProgressDialog?.show()
            }
        }
    }

    fun stopLoading() {
        if (!isFinishing) {
            myMyProgressDialog?.dismiss()
            myMyProgressDialog = null
        }
    }

    fun setBtnListener(listener: View.OnClickListener, vararg views: View?){
        views.forEach {
            it?.setOnClickListener(listener)
        }
    }

    fun setActionBarBtnBookmark(onClick:() -> Unit) {}
    abstract fun inflateLayout(layoutInflater: LayoutInflater) : VB
}