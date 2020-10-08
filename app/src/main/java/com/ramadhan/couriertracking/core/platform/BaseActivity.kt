package com.ramadhan.couriertracking.core.platform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        setupLib()
        initView()
        onAction()
    }

    protected abstract fun layoutId(): Int
    protected abstract fun setupLib()
    protected abstract fun initView()
    protected abstract fun onAction()

}