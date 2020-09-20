package com.ramadhan.couriertracking.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramadhan.couriertracking.R
import com.ramadhan.couriertracking.data.entity.History
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val histories: MutableList<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        val item = histories[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return histories.size
    }

    fun addList(list: List<History>){
        histories.clear()
        histories.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(history: History) {
            itemView.historyTitle.text = history.title ?: history.awb
            itemView.historyAWB.text = history.awb
            itemView.historyCourier.text = history.courier.name
        }
    }

}