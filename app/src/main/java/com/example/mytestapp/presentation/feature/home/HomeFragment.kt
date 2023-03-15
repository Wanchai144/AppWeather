package com.example.mytestapp.presentation.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.data.model.Coin
import com.example.mytestapp.databinding.FragmentHomeBinding
import com.example.mytestapp.presentation.feature.adapter.CoinDeskAdapter
import com.example.mytestapp.presentation.feature.base.NoInternetState
import com.example.mytestapp.presentation.feature.base.Success
import com.example.mytestapp.presentation.feature.base.ViewState
import com.example.mytestapp.presentation.feature.base.snackbar
import com.example.mytestapp.presentation.feature.viewmodel.base.BaseFragment
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL
import java.util.*

class HomeFragment : BaseFragment() {
    var TAG = "HomeFragmentBaseFragment"
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModel()

    private val noDelay = 0L
    private val everyFiveSeconds = 600000L
    private lateinit var timer: Timer

    private lateinit var bpi : Coin
    private  var bpiList = ArrayList<Coin>()

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
        binding.dataViewModel = viewModel
        setupViews()
        observeViewModel()
        initRecyclerView()
    }

    private fun setupViews() = with(binding) {
        with(viewModel){
            btnSaveData.setOnClickListener {
              saveDataCoin(bpi)
            }
        }
    }

    private fun initRecyclerView() = with(binding) {
        rvItemCoin.layoutManager =
            LinearLayoutManager(rvItemCoin.context, RecyclerView.VERTICAL, false)
        rvItemCoin.adapter = adapter
    }

    private fun observeViewModel() = with(viewModel) {
        showDataSuccess.observe(
            viewLifecycleOwner,
            Observer(this@HomeFragment::handleInitialLoadDataViewState)
        )
    }


    private fun handleInitialLoadDataViewState(viewState: ViewState<Coin>) {
        when (viewState) {
            is Success -> {
                viewState.data.let {
                    bpi = Coin(time = it.time, chartName = it.chartName,bpi = it.bpi)
                    adapter.data = it.bpi?: listOf()
                    Log.d(TAG, "handleInitialLoadDataViewState: $it")
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
        forceUpdate()
    }

    private fun forceUpdate() {
        val timerTask = object : TimerTask() {
            override fun run() {
                requireActivity().runOnUiThread {
                    viewModel.loadData()
                }
            }
        }
        timer = Timer()
        timer.schedule(timerTask, noDelay, everyFiveSeconds)
    }

    override fun onPause() {
        timer.cancel()
        timer.purge()
        super.onPause()
    }
}