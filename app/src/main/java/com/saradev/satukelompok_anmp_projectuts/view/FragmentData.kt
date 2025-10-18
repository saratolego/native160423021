package com.saradev.satukelompok_anmp_projectuts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
// Hapus R
// import com.saradev.satukelompok_anmp_projectuts.R
// Import Binding, ViewModel, Adapter, dan Model
import com.saradev.satukelompok_anmp_projectuts.databinding.FragmentDataBinding
import com.saradev.satukelompok_anmp_projectuts.viewmodel.ListViewModel
import com.saradev.satukelompok_anmp_projectuts.model.Measurement

class FragmentData : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentDataBinding
    private val measurementAdapter = MeasurementAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.rvData.layoutManager = LinearLayoutManager(context)
        binding.rvData.adapter = measurementAdapter

        viewModel.loadMeasurementData()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.measurements.observe(viewLifecycleOwner) { dataList: List<Measurement> ->
            measurementAdapter.updateData(dataList)
        }
    }

}