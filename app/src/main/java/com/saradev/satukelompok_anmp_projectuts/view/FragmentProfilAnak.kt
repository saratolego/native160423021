package com.saradev.satukelompok_anmp_projectuts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saradev.satukelompok_anmp_projectuts.databinding.FragmentProfilAnakBinding
import com.saradev.satukelompok_anmp_projectuts.viewmodel.ListViewModel

class FragmentProfilAnak : Fragment() {

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentProfilAnakBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilAnakBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            viewModel.jenisKelaminId.value = checkedId
        }

        viewModel.jenisKelaminId.observe(viewLifecycleOwner) { id ->
            if (id != -1) binding.radioGroup.check(id)
        }
    }

    private fun observeViewModel() {
        viewModel.namaProfil.observe(viewLifecycleOwner) { nama ->
            binding.editTxtNama.setText(nama)
        }
        viewModel.tglLahirProfil.observe(viewLifecycleOwner) { tglLahir ->
            binding.editTxtDob.setText(tglLahir)
        }
        viewModel.jenisKelaminId.observe(viewLifecycleOwner) { idJenisKelamin ->
            if (idJenisKelamin != -1) {
                val radioButtonView = binding.radioGroup.findViewById<View>(idJenisKelamin)
                if (radioButtonView != null) {
                    binding.radioGroup.check(idJenisKelamin)
                }
            }
        }
    }
}
