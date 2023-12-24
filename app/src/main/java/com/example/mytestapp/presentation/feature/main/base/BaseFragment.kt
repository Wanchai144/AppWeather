package com.example.mytestapp.presentation.feature.main.base

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mytestapp.utils.RequestPermissions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject


abstract class BaseFragment : Fragment(), CoroutineScope {

    protected open var isTransparent: Boolean = false

    val requestPermissions: RequestPermissions by inject()

    protected open var screenName: String = ""

//    override val disposeBag by lazy { CompositeDisposable() }

    private val job = Job()

    override val coroutineContext = job + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //screenName = this::class.java.simpleName
    }

    /*    override fun onStart() {
            super.onStart()
            requireActivity().statusBarTransparentManager(isTransparent)
        }

        override fun onStop() {
            super.onStop()
            if (isTransparent) {
                requireActivity().statusBarTransparentManager(!isTransparent)
            }

    }*/
}

fun Activity.statusBarTransparentManager(isTransparent: Boolean) {
    if (isTransparent) {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT
    } else {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
}