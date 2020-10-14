package com.ramadhan.couriertracking.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.CaptureManager
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_barcode_scan.*

class BarcodeScanActivity : BaseActivity() {

    companion object {
        fun callIntent(context: Context): Intent {
            val intent = Intent(context, BarcodeScanActivity::class.java)
            return intent
        }
    }

    private lateinit var captureManager: CaptureManager
    private var lastResult: BarcodeResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        captureManager = CaptureManager(this, barcodeScanner)
        captureManager.initializeFromIntent(intent, savedInstanceState)
    }

    override fun layoutId(): Int = R.layout.activity_barcode_scan

    override fun setupLib() {
    }

    override fun initView() {
        barcodeScanner.decodeContinuous(barcodeCallback)
    }

    override fun onAction() {
    }

    private var barcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            result?.let {
                if (result != lastResult) {
                    barcodeScanTextResult.text = it.text
                }
            }
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        }

    }
}