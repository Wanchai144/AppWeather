package com.example.mytestapp.presentation.feature.data_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.MainActivity
import com.example.mytestapp.databinding.FragmentDataAnimalBinding
import com.example.mytestapp.presentation.feature.adapter.AdapterUser
import com.example.mytestapp.shared_preferences.SharedPrefHelper
import com.example.mytestapp.shared_preferences.SharedPrefHelper.SharPrefKeys.Companion.USER_LOGIN
import com.example.mytestapp.utils.navigateToHome
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DataAnimalFragment  : Fragment() {
    private val binding by lazy { FragmentDataAnimalBinding.inflate(layoutInflater) }
    private val viewModel: DataAnimalViewModel by viewModel()
    private val sharedPrefHelper: SharedPrefHelper by inject()
    private val adapter: AdapterUser by lazy {
        AdapterUser()
    }

    private val activity by lazy {
        (requireActivity() as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickAdapter()
        setAdapter()
        observerViewModel()
    }

    private fun onClickAdapter() {
        adapter.onSelectItem = {
            logoutToLoginFragment(it)
        }
    }

    private fun logoutToLoginFragment(it: String) {
        sharedPrefHelper.clearSharedPreference()
        activity.navigateToHome(it)
    }

    private fun observerViewModel()  = with(viewModel){
        showList.observe(viewLifecycleOwner){
            it.forEachIndexed { index, userEntity ->
                adapter.loadData(userEntity.name?: listOf())
            }
        }
    }


    private fun setAdapter() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }
}