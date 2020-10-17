package com.inbedroom.couriertracking.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setupPermission()
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        processBundle(savedInstanceState)
        setupLib()
        initView()
        onAction()
    }

    open fun setupPermission(){}
    open fun processBundle(savedInstanceState: Bundle?) {}
    protected abstract fun layoutId(): Int
    protected abstract fun setupLib()
    protected abstract fun initView()
    protected abstract fun onAction()

}