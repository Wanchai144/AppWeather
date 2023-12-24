package com.example.mytestapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rejowan.cutetoast.CuteToast

fun AppCompatActivity.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    name: String? = null,
    backstack: Boolean = true,
    tag: String = fragment::class.java.simpleName,
) = supportFragmentManager.beginTransaction()
    .also {
        if (backstack) {
            /*it.setCustomAnimations(R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out)*/
        }
    }
    .replace(containerId, fragment, tag)
    .also { if (backstack) it.addToBackStack(name) }
    .commit()

fun AppCompatActivity.replaceChildFragment(
    parentFragment: Fragment,
    containerId: Int,
    fragment: Fragment,
    tag: String? = fragment.javaClass.simpleName,
) {
    val childFragmentManager = parentFragment.childFragmentManager
    val transaction = childFragmentManager.beginTransaction()

    transaction.replace(containerId, fragment, tag)
    transaction.commit()
}


fun AppCompatActivity.showSuccessToast(message: String) {
    CuteToast.ct(this, message, CuteToast.LENGTH_SHORT, CuteToast.SUCCESS, true).show()
}

fun AppCompatActivity.showErrorToast(message: String, icon :Boolean = true) {
    CuteToast.ct(this, message, CuteToast.LENGTH_SHORT, CuteToast.ERROR, icon).show()
}
