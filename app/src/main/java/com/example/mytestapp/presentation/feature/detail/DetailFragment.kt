package com.example.mytestapp.presentation.feature.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.data.model.Coin
import com.example.mytestapp.data.model.Section
import com.example.mytestapp.data.model.SectionModel
import com.example.mytestapp.databinding.FragmentDetailBinding
import com.example.mytestapp.presentation.feature.adapter.CoinDeskAdapter
import com.example.mytestapp.presentation.feature.adapter.HistoryDetailAdapter
import com.example.mytestapp.presentation.feature.base.NoInternetState
import com.example.mytestapp.presentation.feature.base.Success
import com.example.mytestapp.presentation.feature.base.ViewState
import com.example.mytestapp.presentation.feature.base.snackbar
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    val TAG = "DetailFragment"
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModel()
    private val adapter: CoinDeskAdapter by lazy {
        CoinDeskAdapter()
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
        observeViewModel()
        initRecyclerView()
    }

    private fun initView() = with(viewModel) {

    }

    private fun initRecyclerView() = with(binding) {
        rvItem.layoutManager =
            LinearLayoutManager(rvItem.context, RecyclerView.VERTICAL, false)
        rvItem.adapter = adapter
    }

    private fun observeViewModel() = with(viewModel) {
        showRetrospectSuccess.observe(
            viewLifecycleOwner,
            Observer(this@DetailFragment::handleInitialDataViewState)
        )
    }

    private fun handleInitialDataViewState(viewState: ViewState<List<Coin>>) {
        when (viewState) {
            is Success -> {
                viewState.data.forEach { coin ->
                    adapter.data = coin.bpi?: listOf()
                }
            }
            is com.example.mytestapp.presentation.feature.base.Error -> {
                snackbar("Data not found", requireView())
            }
            is NoInternetState -> {
                snackbar("Internet not found", requireView())
            }
            else -> {}
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadDataRetrospect()
    }
}