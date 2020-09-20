package com.ramadhan.couriertracking.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.data.entity.Courier
import com.ramadhan.couriertracking.utils.Injector
import com.ramadhan.couriertracking.view.TrackingDetailActivity.Companion.AWB_NUMBER
import com.ramadhan.couriertracking.view.TrackingDetailActivity.Companion.COURIER_NAME
import com.ramadhan.couriertracking.view.adapter.CourierSpinnerAdapter
import com.ramadhan.couriertracking.viewmodel.MainViewModel
import com.ramadhan.couriertracking.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var courierAdapter: CourierSpinnerAdapter
    private lateinit var courierList: List<Courier>
    private lateinit var spinner: Spinner

    private var courierData: Courier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLib()

        setupUI()

        onAction()
    }

    private fun setupUI() {
        courierList = readFromAsset()
        courierAdapter = CourierSpinnerAdapter(this, courierList)
        mainCourierList.adapter = courierAdapter
        spinner = findViewById(R.id.mainCourierList)
        spinner.onItemSelectedListener = this
    }

    private fun setupLib() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                Injector.provideRemoteRepository(),
                Injector.provideHistoryRepository()
            )
        ).get(MainViewModel::class.java)
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        courierData = parent?.getItemAtPosition(position) as Courier
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun onAction() {
        mainButtonSearch.setOnClickListener {
            if (mainAWBInput.text.isNotEmpty()) {
                val intent = Intent(this, TrackingDetailActivity::class.java)
                intent.putExtra(COURIER_NAME, courierData)
                intent.putExtra(AWB_NUMBER, mainAWBInput.text.toString())
                startActivity(intent)
            } else {
                showAlertDialog("Masukkan resi terlebih dahulu")
            }
        }
    }

    private fun showAlertDialog(msg: String) {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setTitle(getString(R.string.alert_title))
        dialogBuilder.setMessage(msg)

        dialogBuilder.setPositiveButton(R.string.ok_button) { _, _ ->
        }

        dialogBuilder.show()
    }
}