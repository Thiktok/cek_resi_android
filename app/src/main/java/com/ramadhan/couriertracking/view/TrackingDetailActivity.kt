package com.ramadhan.couriertracking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.data.entity.Courier
import com.ramadhan.couriertracking.data.entity.Track
import com.ramadhan.couriertracking.data.entity.Tracking
import com.ramadhan.couriertracking.utils.Injector
import com.ramadhan.couriertracking.view.adapter.TrackingRecyclerViewAdapter
import com.ramadhan.couriertracking.viewmodel.TrackingViewModel
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tracking_detail.*

class TrackingDetailActivity : AppCompatActivity() {
    companion object {
        const val COURIER_NAME = "courier"
        const val AWB_NUMBER = "resi"
    }

    private lateinit var viewModel: TrackingViewModel
    private lateinit var trackingListAdapter: TrackingRecyclerViewAdapter
    private var trackingList: List<Tracking>? = null
    private var courierData: Courier? = null
    private var awbData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking_detail)

        setupLib()
        if (savedInstanceState == null) {
            if (courierData != null && awbData != null) {
                viewModel.getTrackingData(awbData!!, courierData!!.code)
            }
        }
        setupUI()
    }

    private fun setupLib() {
        viewModel = ViewModelProvider(
            this,
            Injector.provideViewModelFactory()
        ).get(TrackingViewModel::class.java)

        courierData = intent.getParcelableExtra(COURIER_NAME)
        awbData = intent.getStringExtra(AWB_NUMBER)
    }

    private fun setupUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tracking from ${courierData?.name}"

        viewModel.trackingData.observe(this, trackingObserver)
        viewModel.isViewLoading.observe(this, loadingObserver)
        viewModel.isNoData.observe(this, noDataObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isSuccessful.observe(this, onSuccessObserver)

        val llManager = LinearLayoutManager(this)
        trackingListAdapter = TrackingRecyclerViewAdapter(this, ArrayList())

        trackingDetailInfo.apply {
            layoutManager = llManager
            adapter = trackingListAdapter
        }
    }

    private val trackingObserver = Observer<Track<List<Tracking>>> {
        trackingListAdapter.updateItem(it.tracking)
        trackingDetailAwb.setValueText(it.waybill)
        val courierDetail: String?
        val detailStatus: String?
        val detailSender: String?
        val detailReceiver: String?
        Log.d("tracking", "${it.detail}")
        if (it.detail != null) {
            courierDetail = getString(
                R.string.courier_value,
                it.courier,
                it.detail?.service
            )
            detailStatus = it.detail?.status
            detailSender = "${it.detail?.shipper}\n${it.detail?.origin}"
            detailReceiver = "${it.detail?.receiver}\n${it.detail?.destination}"
        } else {
            courierDetail = getString(
                R.string.courier_value,
                it.courier,
                it.service
            )
            detailStatus = it.status
            detailSender = "${it.shipped?.name}\n${it.shipped?.addr}"
            detailReceiver = "${it.received?.name}\n${it.received?.addr}"
        }

        trackingDetailCourierName.setValueText(courierDetail)
        trackingDetailStatus.setValueText(detailStatus)
        trackingDetailSender.setValueText(detailSender)
        trackingDetailDestination.setValueText(detailReceiver)


    }

    private val onSuccessObserver = Observer<Boolean> {
        if (it) {
            if (awbData != null && courierData != null) {
                viewModel.saveAsHistory(awbData!!, courierData!!)
            }
        }
    }

    private val loadingObserver = Observer<Boolean> {
        val visibility = if (it) View.VISIBLE else View.GONE
        loadingLayout.visibility = visibility
    }

    private val noDataObserver = Observer<Boolean> {

    }

    private val onMessageErrorObserver = Observer<Any> {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle(getString(R.string.alert_title))
        dialogBuilder.setMessage(it.toString())
        dialogBuilder.setCancelable(false)

        dialogBuilder.setPositiveButton(R.string.ok_button) { _, _ ->
            finish()
        }

        dialogBuilder.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}