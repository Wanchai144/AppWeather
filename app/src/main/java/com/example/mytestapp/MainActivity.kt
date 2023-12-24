package com.example.mytestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mytestapp.databinding.ActivityMainBinding
import com.example.mytestapp.presentation.feature.main.HomeMainFragment
import com.example.mytestapp.utils.replaceFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class MainActivity: AppCompatActivity(){
    val TAG = "MainActivity"

    val receivedData : String?
        get() =  intent.getStringExtra("key")


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setUpFragmentCenter()
        }
    }

    private fun setUpFragmentCenter() {
        replaceFragment(R.id.container, HomeMainFragment())
    }

    override fun onBackPressed() {
        val fragmentCount = supportFragmentManager.backStackEntryCount
        if (fragmentCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            lifecycleScope.launch {
                delay(100) // รอเวลา 100 milliseconds
                finish()
            }
        }
    }

    private var forceStopRunnable = Runnable {
        exitProcess(0)
    }
}