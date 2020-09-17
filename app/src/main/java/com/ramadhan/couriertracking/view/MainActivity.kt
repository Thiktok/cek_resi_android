package com.ramadhan.couriertracking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.data.response.entity.Courier
import com.ramadhan.couriertracking.view.adapter.CourierSpinnerAdapter
import com.ramadhan.couriertracking.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var courierAdapter: CourierSpinnerAdapter
    private lateinit var courierList: List<Courier>
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLib()

        setupUI()

    }

    private fun setupUI() {
        courierList = readFromAsset()
        courierAdapter = CourierSpinnerAdapter(this, courierList)
        mainCourierList.adapter = courierAdapter

        val spinner: Spinner = findViewById(R.id.mainCourierList)
        spinner.onItemSelectedListener = this
    }

    private fun setupLib() {
    }

    private fun readFromAsset(): List<Courier> {
        val fileName = "courier_list.json"

        val bufferReader = application.assets.open(fileName).bufferedReader()

        val jsonString = bufferReader.use {
            it.readText()
        }
        val gson = Gson()

        return gson.fromJson(jsonString, Array<Courier>::class.java).toList()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = parent?.getItemAtPosition(position) as Courier
        Toast.makeText(
            this,
            "Selected item is ${selectedItem.name}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}