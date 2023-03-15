package com.example.mytestapp.presentation.feature.point

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mytestapp.presentation.feature.base.BaseViewModel
import com.example.mytestapp.presentation.feature.base.ViewEffect
import com.example.mytestapp.utils.TextWatcherAdapter

class ScorePointViewModel() : BaseViewModel<Any, ViewEffect>() {


    val showFibonacci = MutableLiveData<String>()

    var showPrime = MutableLiveData<String>()

    val showFilterList = MutableLiveData<String>()

    val edtNumber = MutableLiveData<String>()


    val edtPrime = MutableLiveData<String>()

    var onFibonacci = mutableListOf<Int>()
    var primeNumbers = mutableListOf<Int>()


    val onNumberTextChanged = TextWatcherAdapter { s ->
        edtNumber.value = s
    }

    val onPrimeTextChanged = TextWatcherAdapter { s ->
        edtPrime.value = s
    }


    fun onBtnClick() {
        onFibonacci = arrayListOf()
        primeNumbers = arrayListOf()
        edtNumber.value?.let {
            if (it.isNotEmpty()) mainFibonacci(it.toInt())
        }

        edtPrime.value?.let {
            if (it.isNotEmpty()) showPrime.value = findPrimeNumbers(it.toInt()).toString()
        }

        mainFilter()
    }

    private fun mainFibonacci(number: Int) {
        var t1 = 0
        var t2 = 1
        for (i in 1..number) {
            onFibonacci.add(t1)
            showFibonacci.value = "$onFibonacci"
            val sum = t1 + t2
            t1 = t2
            t2 = sum
        }
    }

    private fun findPrimeNumbers(number: Int): List<Int> {
        for (i in 2..number) {
            var isPrime = true

            for (j in 2 until i) {
                if (i % j == 0) {
                    isPrime = false
                    break
                }
            }

            if (isPrime) {
                primeNumbers.add(i)
            }
        }
        return primeNumbers
    }

//    @SuppressLint("SuspiciousIndentation")
//    private fun mainPrime(number: Int) {
//
//        var low = 2
//        while (low < number) {
//            if (checkPrimeNumber(low))
//            onPrimeList.add(low)
//            showPrime.value = "$onPrimeList "
//            ++low
//        }
//    }
//
//    private fun checkPrimeNumber(num: Int): Boolean {
//        var flag = true
//        for (i in 2..num / 2) {
//            if (num % i == 0) {
//                flag = false
//                break
//            }
//        }
//        return flag
//    }

    private fun mainFilter() {
        val filterList = ArrayList<Int>()
        if (onFibonacci.isNotEmpty() && primeNumbers.isNotEmpty()) {
            onFibonacci.forEach { fibonacci ->
                primeNumbers.forEach { prime ->
                    if (fibonacci == prime) {
                        filterList.add(fibonacci)
                    }
                }
            }
            showFilterList.value = "$filterList "
        }else{
            showFilterList.value = "ต้องมีข้อมูลที่ตรงกัน"
        }
    }


}