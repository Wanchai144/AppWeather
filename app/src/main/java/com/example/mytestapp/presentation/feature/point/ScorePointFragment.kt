package com.example.mytestapp.presentation.feature.point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mytestapp.databinding.FragmentScorePointBinding
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_score_point.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScorePointFragment : BaseFragment() {
    val TAG = "DetailFragment"
    private val binding by lazy { FragmentScorePointBinding.inflate(layoutInflater) }
    private val viewModel: ScorePointViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dataViewModel = viewModel
        initView()
        observeViewModel()
    }

    private fun initView() = with(viewModel) {

    }


    private fun observeViewModel() = with(viewModel) {
        showFibonacci.observe(viewLifecycleOwner) {
            tvShowFibonacci.text = it
        }

        showPrime.observe(viewLifecycleOwner) {
           tvShowPrimeNumber.text = it
        }

        showFilterList.observe(viewLifecycleOwner){
            tvShowFilter.text =  it
        }
    }


}