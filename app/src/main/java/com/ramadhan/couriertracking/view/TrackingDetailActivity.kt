package com.ramadhan.couriertracking.view

import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramadhan.couriertracking.CourierTrackingApplication
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.core.extension.invisible
import com.ramadhan.couriertracking.core.extension.visible
import com.ramadhan.couriertracking.core.platform.BaseActivity
import com.ramadhan.couriertracking.data.entity.Courier
import com.ramadhan.couriertracking.data.entity.TrackData
import com.ramadhan.couriertracking.data.entity.Tracking
import com.ramadhan.couriertracking.utils.Message
import com.ramadhan.couriertracking.utils.Utils
import com.ramadhan.couriertracking.view.adapter.TrackingRecyclerViewAdapter
import com.ramadhan.couriertracking.viewmodel.TrackingViewModel
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tracking_detail.*
import javax.inject.Inject

class TrackingDetailActivity : BaseActivity() {
    companion object {
        const val COURIER_NAME = "courier"
        const val AWB_NUMBER = "resi"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: TrackingViewModel
    private lateinit var trackingListAdapter: TrackingRecyclerViewAdapter
    private var courierData: Courier? = null
    private var awbData: String? = null

    override fun layoutId(): Int = R.layout.activity_tracking_detail

    override fun setupLib() {
        (application as CourierTrackingApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TrackingViewModel::class.java)
        courierData = intent.getParcelableExtra(COURIER_NAME)
        awbData = intent.getStringExtra(AWB_NUMBER)

        if (courierData != null && awbData != null) {
            viewModel.getTrackingData(awbData!!, courierData!!.code)
        }

        viewModel.trackingData.observe(this, trackingObserver)
        viewModel.isViewLoading.observe(this, loadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    override fun initView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Tracking from ${courierData?.name}"

        val llManager = LinearLayoutManager(this)
        trackingListAdapter = TrackingRecyclerViewAdapter(this, ArrayList())

        trackingDetailInfo.apply {
            layoutManager = llManager
            adapter = trackingListAdapter
            setHasFixedSize(true)
        }
    }

    override fun onAction() {

    }

    private val trackingObserver = Observer<TrackData> { data ->
        val trackingList: List<Tracking> = data.track.filter { it.desc.isNotEmpty() }
        trackingListAdapter.updateItem(trackingList.sortedByDescending { Utils.stringToTime(it.date) })
        trackingDetailAwb.setValueText(data.summary.awb)
        val courierDetail =
            getString(R.string.courier_value, data.summary.courier, data.summary.service)
        val detailStatus =
            if (data.summary.status.isNullOrEmpty()) "on Delivery" else data.summary.status
        val detailSender = if (data.info.shipper.isNullOrEmpty()) {
            ""
        } else {
            "${data.info.shipper}\n${data.info.origin}"
        }
        val detailReceiver = "${data.info.receiver}\n${data.info.destination}"

        trackingDetailCourierName.setValueText(courierDetail)
        trackingDetailStatus.setValueText(detailStatus)
        if (detailStatus.equals("delivered", true)) {
            trackingDetailStatus.setValueColor(R.color.greenSea)
        }
        trackingDetailSender.setValueText(detailSender)
        trackingDetailDestination.setValueText(detailReceiver)

        if (awbData != null && courierData != null) {
            viewModel.saveAsHistory(awbData!!, courierData!!)
        }

    }

    private val loadingObserver = Observer<Boolean> {
        if (it) loadingLayout.visible() else loadingLayout.invisible()
    }

    private val onMessageErrorObserver = Observer<Any> {
        Message.alert(this, it.toString(),
            DialogInterface.OnClickListener { _, _ -> onBackPressed() })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}