package com.ramadhan.couriertracking.view

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
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

        private const val BARCODE_PATTERN = "^[a-zA-Z0-9]*\$"
    }

    private lateinit var captureManager: CaptureManager

    override fun setupPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    101
                )
            }
        }
    }

    override fun processBundle(savedInstanceState: Bundle?) {
        captureManager = CaptureManager(this, barcodeScanner)
        captureManager.initializeFromIntent(intent, savedInstanceState)
        barcodeScanner.decodeContinuous(barcodeCallback)
    }

    override fun layoutId(): Int = R.layout.activity_barcode_scan

    override fun setupLib() {
    }

    override fun initView() {
    }

    override fun onAction() {
    }

    private var barcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            result?.let { scanned ->
                if (scanned.text.matches(BARCODE_PATTERN.toRegex())) {
                    barcodeScanTextResult.text = scanned.text
                    barcodeScanTextResult.setBackgroundResource(R.color.emerald)
                    barcodeScanButtonApply.setBackgroundResource(R.drawable.rounded_button)
                    barcodeScanButtonApply.isEnabled = true
                } else {
                    emptyResult()
                }
            }
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        }

    }

    private fun emptyResult() {
        barcodeScanTextResult.text = ""
        barcodeScanTextResult.setBackgroundColor(Color.TRANSPARENT)
        barcodeScanButtonApply.setBackgroundResource(R.drawable.rounded_button_disabled)
        barcodeScanButtonApply.isEnabled = false
    }

    override fun onResume() {
        super.onResume()
        captureManager.onResume()
    }

    override fun onPause() {
        super.onPause()
        captureManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        captureManager.onDestroy()
    }
}