package com.saradev.satukelompok_anmp_projectuts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saradev.satukelompok_anmp_projectuts.databinding.FragmentUkurBinding
import com.saradev.satukelompok_anmp_projectuts.viewmodel.ListViewModel

class FragmentUkur : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentUkurBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUkurBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}
