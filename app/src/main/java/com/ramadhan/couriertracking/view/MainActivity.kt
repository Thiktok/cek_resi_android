package com.ramadhan.couriertracking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.data.response.entity.Courier
import com.ramadhan.couriertracking.view.adapter.CourierSpinnerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCourierList.adapter = CourierSpinnerAdapter(
            this,
            readFromAsset()
        )
    }

    private fun readFromAsset(): List<Courier>{
        val fileName = "courier_list.json"

        val bufferReader = application.assets.open(fileName).bufferedReader()

        val jsonString = bufferReader.use {
            it.readText()
        }
        val gson = Gson()
        val courierList = gson.fromJson(jsonString, Array<Courier>::class.java).toList()

        return courierList
    }
}