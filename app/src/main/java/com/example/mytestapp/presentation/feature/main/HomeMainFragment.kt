package com.example.mytestapp.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytestapp.MainActivity
import com.example.mytestapp.R
import com.example.mytestapp.data.model.UserLogin
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.map.MapFragment
import com.example.mytestapp.shared_preferences.SharedPrefHelper
import com.example.mytestapp.utils.replaceFragment
import com.example.mytestapp.utils.showSuccessToast
import org.koin.android.ext.android.inject


class HomeMainFragment : Fragment() {
    private val binding by lazy { FragmentHomeMainBinding.inflate(layoutInflater) }
    private val sharedPrefHelper: SharedPrefHelper by inject()

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
        super.onViewCreated(view, savedInstanceState)
        initView()
        checkAutoLogin()
        receivedData()
    }

    private fun receivedData() = with(binding) {
        if (!activity.receivedData.isNullOrEmpty())  edtEmail.setText(activity.receivedData)
    }

    private fun checkAutoLogin() {
        val userPref = sharedPrefHelper.get(SharedPrefHelper.SharPrefKeys.USER_LOGIN, UserLogin())
        if (!userPref.email.isNullOrEmpty() && !userPref.password.isNullOrEmpty()) goViewMap()
    }

    private fun initView() = with(binding) {
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString().trim()
            val password = edtPassWord.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty() && email.contains("DTCGPS") && password.contains("test")) {
                if (switch1.isChecked){
                    val userLoginModel = UserLogin(email = email, password = password)
                    sharedPrefHelper.put(SharedPrefHelper.SharPrefKeys.USER_LOGIN, userLoginModel)
                    goViewMap()
                }else{
                    goViewMap()
                }
            }else{
                activity.showSuccessToast(getString(R.string.error_input_text))
            }
        }
    }

    private fun goViewMap() {
        activity.replaceFragment(R.id.container, MapFragment())
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}