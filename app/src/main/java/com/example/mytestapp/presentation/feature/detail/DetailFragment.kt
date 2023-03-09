package com.example.mytestapp.presentation.feature.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.AllDayWeather
import com.example.mytestapp.databinding.FragmentDetailBinding
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.adapter.WeatherAllDayAdapter
import com.example.mytestapp.presentation.feature.base.NoInternetState
import com.example.mytestapp.presentation.feature.base.Success
import com.example.mytestapp.presentation.feature.base.ViewState
import com.example.mytestapp.presentation.feature.base.snackbar
import com.example.mytestapp.presentation.feature.main.HomeMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    val TAG = "DetailFragment"
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModel()
    private val adapter: WeatherAllDayAdapter by lazy {
        WeatherAllDayAdapter()
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
        getCurrentLocation()
        observeViewModel()
        initRecyclerView()
    }

    private fun getCurrentLocation() = with(viewModel) {
        // lat,long in Bangkok
        loadAllDayWeather(13.757791284992372, 100.50672027500647)
    }

    private fun initRecyclerView() = with(binding) {
        rvItem.layoutManager =
            LinearLayoutManager(rvItem.context, RecyclerView.VERTICAL, false)
        rvItem.adapter = adapter
    }

    private fun observeViewModel() = with(viewModel){
        showAllDayWeatherSuccess.observe(viewLifecycleOwner, Observer(this@DetailFragment::handleInitialDataViewState))
    }

    private fun handleInitialDataViewState(viewState: ViewState<AllDayWeather>) {
        when (viewState) {
            is Success -> {
                adapter.data = viewState.data._hourly?: listOf()
            }
            is com.example.mytestapp.presentation.feature.base.Error -> {
                snackbar("Data not found",requireView())
            }
            is NoInternetState -> {
                snackbar("Internet not found",requireView())
            }
            else -> {}
        }
    }


}