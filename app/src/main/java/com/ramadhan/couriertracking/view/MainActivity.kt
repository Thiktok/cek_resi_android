package com.ramadhan.couriertracking.view

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ramadhan.couriertracking.CourierTrackingApplication
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.core.extension.hideKeyboard
import com.ramadhan.couriertracking.core.platform.BaseActivity
import com.ramadhan.couriertracking.customview.DialogEditTitle
import com.ramadhan.couriertracking.data.entity.Courier
import com.ramadhan.couriertracking.data.entity.HistoryEntity
import com.ramadhan.couriertracking.utils.Message
import com.ramadhan.couriertracking.view.TrackingDetailActivity.Companion.AWB_NUMBER
import com.ramadhan.couriertracking.view.TrackingDetailActivity.Companion.COURIER_NAME
import com.ramadhan.couriertracking.view.adapter.CourierSpinnerAdapter
import com.ramadhan.couriertracking.view.adapter.HistoryAdapter
import com.ramadhan.couriertracking.viewmodel.MainViewModel
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var courierAdapter: CourierSpinnerAdapter
    private lateinit var courierList: List<Courier>
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var spinner: Spinner

    private var courierData: Courier? = null

    override fun layoutId(): Int = R.layout.activity_main

    override fun setupLib() {
        (application as CourierTrackingApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MainViewModel::class.java)

        viewModel.historiesData.observe(this, historyObserver)
        viewModel.isChanged.observe(this, onTitleChange)
    }

    override fun initView() {
        courierList = readFromAsset()
        courierAdapter = CourierSpinnerAdapter(this, courierList)
        mainCourierList.adapter = courierAdapter
        spinner = findViewById(R.id.mainCourierList)
        spinner.onItemSelectedListener = this

        val llManager = LinearLayoutManager(this)

        historyAdapter = HistoryAdapter(ArrayList())
        mainHistories.apply {
            layoutManager = llManager
            adapter = historyAdapter
        }
            .addItemDecoration(DividerItemDecoration(this, llManager.orientation))
    }

    override fun onAction() {
        mainButtonSearch.setOnClickListener {
            if (mainAWBInput.text.isNotEmpty()) {
                courierData?.let { it1 -> goToTracking(mainAWBInput.text.toString(), it1) }
            } else {
                Message.alert(this, getString(R.string.empty_awb), null)
            }
        }

        historyAdapter.setOnItemClickListener(object : HistoryAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int) {
                goToTracking(
                    historyAdapter.getData(position).awb,
                    historyAdapter.getData(position).courier
                )
            }

            override fun onDeleteMenuClick(position: Int) {
                val item = historyAdapter.getData(position)
                viewModel.deleteHistory(item.awb)
                Message.notify(mainCoordinatorLayout, "${item.title ?: item.awb} Deleted")
            }

            override fun onEditMenuClick(position: Int) {
                Message.alertEditText(
                    supportFragmentManager,
                    object : DialogEditTitle.DialogListener {
                        override fun onPositiveDialog(text: String?) {
                            hideKeyboard()
                            historyAdapter.getData(position).apply {
                                viewModel.editHistoryTitle(awb, title)
                            }
                        }
                    })
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteAllHistory -> {
                deleteAllHistory()
                true
            }
            else -> false
        }
    }

    private fun deleteAllHistory() {
        viewModel.clearHistory()
    }

    private fun readFromAsset(): List<Courier> {
        val fileName = "courier_list.json"

        val bufferReader = application.assets.open(fileName).bufferedReader()

        val jsonString = bufferReader.use {
            it.readText()
        }
        val gson = Gson()

        return gson.fromJson(jsonString, Array<Courier>::class.java).toList().filter {
            it.available
        }
    }

    private fun goToTracking(awb: String, courier: Courier) {
        val intent = Intent(this, TrackingDetailActivity::class.java)
        intent.putExtra(COURIER_NAME, courier)
        intent.putExtra(AWB_NUMBER, awb)
        startActivity(intent)
    }

    private val historyObserver = Observer<List<HistoryEntity>> {
        historyAdapter.addList(it.reversed())
    }

    private val onTitleChange = Observer<Boolean> {
        if (it) {
            Message.notify(mainCoordinatorLayout, getString(R.string.title_changed))
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        courierData = parent?.getItemAtPosition(position) as Courier
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}