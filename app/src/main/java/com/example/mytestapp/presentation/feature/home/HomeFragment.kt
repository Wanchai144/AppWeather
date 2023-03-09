package com.example.mytestapp.presentation.feature.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mytestapp.databinding.FragmentHomeBinding
import com.example.mytestapp.data.model.Weather
import com.example.mytestapp.presentation.feature.base.NoInternetState
import com.example.mytestapp.presentation.feature.base.Success
import com.example.mytestapp.presentation.feature.base.ViewState
import com.example.mytestapp.presentation.feature.base.snackbar
import com.example.mytestapp.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.util.*

class HomeFragment : Fragment() {
    val TAG = "HomeFragment"
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModel()

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
    }

    private fun setupViews() = with(binding) {}

    private fun observeViewModel() = with(viewModel) {
        showDataSuccess.observe(
            viewLifecycleOwner,
            Observer(this@HomeFragment::handleInitialLoadDataViewState)
        )
    }

    private fun handleInitialLoadDataViewState(viewState: ViewState<Weather>) {
        when (viewState) {
            is Success -> {
                viewState.data.let {
                    showDataDegree(it)
                    Log.d(TAG, "handleInitialLoadDataViewState: ${viewState.data}")

                    btC.setOnClickListener { res ->
                        showDataDegree(it)
                    }

                    btF.setOnClickListener { its ->
                        showDataFahrenheit(it)
                    }
                }
            }
            is com.example.mytestapp.presentation.feature.base.Error -> {
                snackbar("City not found",requireView())
            }
            is NoInternetState -> {
                snackbar("Internet not found",requireView())
            }
            else -> {}
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDataFahrenheit(weather: Weather) {
        val df = DecimalFormat("#.##")
        val temp = weather.temp!! - 273.15
        val temp_min = weather.temp_min!! - 273.15
        val temp_max = weather.temp_max!! - 273.15

        val temp_f = 1.8 * temp + 32
        val temp_min_f = 1.8 * temp_min + 32
        val temp_max_f = 1.8 * temp_max + 32

        tvDate.text = Utils().formatDt(weather.date_time!!.toLong())
        tvCountry.text = "Country ${weather.country}"
        tvHumidity.text = "Humidity ${weather.humidity} %"
        tvTemp.text = "Temp ${df.format(temp_f)} °F"
        tvTempMin.text = "Temp_min ${df.format(temp_min_f)} °F"
        tvTempMax.text = "Temp_max ${df.format(temp_max_f)} °F"
    }

    @SuppressLint("SetTextI18n")
    private fun showDataDegree(weather: Weather) {
        val df = DecimalFormat("#.##")
        val temp = weather.temp!! - 273.15
        val temp_min = weather.temp_min!! - 273.15
        val temp_max = weather.temp_max!! - 273.15
        tvCountry.text = "Country ${weather.country}"
        tvHumidity.text = "Humidity ${weather.humidity} %"
        tvTemp.text = "Temp ${df.format(temp)} °C"
        tvTempMin.text = "Temp_min ${df.format(temp_min)} °C"
        tvTempMax.text = "Temp_max ${df.format(temp_max)} °C"
        tvDate.text = Utils().formatDt(weather.date_time!!.toLong())
    }
}