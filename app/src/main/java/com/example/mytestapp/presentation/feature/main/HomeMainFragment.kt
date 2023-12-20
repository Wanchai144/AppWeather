package com.example.mytestapp.presentation.feature.main

import HomeMainViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.adapter.AdapterAttentWork
import com.example.mytestapp.presentation.feature.main.winget.AlertDialog
import kotlinx.android.synthetic.main.fragment_home_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeMainFragment : Fragment() {
    private val binding by lazy { FragmentHomeMainBinding.inflate(layoutInflater) }
    private val viewModel: HomeMainViewModel by viewModel()
    private val adapter: AdapterAttentWork by lazy {
        AdapterAttentWork()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setAdapter()
        observeViewModel()
    }

    private fun initView() = with(binding) {
        button.setOnClickListener {
            val employeeId = editText.text.toString().trim()
            if (employeeId.isNotEmpty()) {
                val isDuplicate = viewModel.isEmployeeIdDuplicateInRealm(employeeId)
                if (isDuplicate) {
                    messageError.visibility = View.VISIBLE
                } else {
                    viewModel.showDialog(childFragmentManager, editText = employeeId)
                    messageError.visibility = View.INVISIBLE
                }
            } else {
            }
        }
    }

    private fun observeViewModel() = with(viewModel) {
        showList.observe(viewLifecycleOwner) {
            adapter.loadData(it)
        }

        onSuccess.observe(viewLifecycleOwner) {
            getUser()
        }

    }

    private fun setAdapter() = with(binding) {
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}