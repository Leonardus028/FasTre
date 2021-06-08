package com.example.fastre.ui.medicalRecords

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fastre.R
import com.example.fastre.core.data.source.Resource
import com.example.fastre.core.ui.MedicalRecordAdapter
import com.example.fastre.core.ui.ViewModelFactory
import com.example.fastre.databinding.FragmentMedicalRecordsBinding

class MedicalRecordsFragment : Fragment() {
    private lateinit var viewModel: MedicalRecordsViewModel

    private var _binding: FragmentMedicalRecordsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMedicalRecordsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val mrAdapter = MedicalRecordAdapter()
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MedicalRecordsViewModel::class.java]

            viewModel.medicalRecords.observe(viewLifecycleOwner, { medicalRecords ->
                if (medicalRecords != null) {
                    when (medicalRecords) {
                        is Resource.Loading -> {
                            Log.d("resource", "observe medical records: loading" )
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            Log.d("resource", "observe medical records: success" )
                            binding.progressBar.visibility = View.GONE
                            mrAdapter.setData(medicalRecords.data)
                        }
                        is Resource.Error -> {
                            Log.d("resource", "observe medical records: error" )
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
            with(binding.rvMedicalRecord) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = mrAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}