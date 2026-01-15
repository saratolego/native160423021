package com.saradev.satukelompok_anmp_projectuts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saradev.satukelompok_anmp_projectuts.databinding.ItemMeasurementBinding
import com.saradev.satukelompok_anmp_projectuts.model.Measurement

class MeasurementAdapter(private var dataList: List<Measurement>) :
    RecyclerView.Adapter<MeasurementAdapter.MeasurementViewHolder>() {

    class MeasurementViewHolder(var binding: ItemMeasurementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasurementViewHolder {
        val binding = ItemMeasurementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MeasurementViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MeasurementViewHolder, position: Int) {
        val measurement = dataList[position]
        holder.binding.measurement = measurement
        holder.binding.executePendingBindings()
    }

    fun updateData(newData: List<Measurement>) {
        dataList = newData
        notifyDataSetChanged()
    }
}