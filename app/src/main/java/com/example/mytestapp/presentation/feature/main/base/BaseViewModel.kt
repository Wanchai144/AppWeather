package com.example.mytestapp.presentation.feature.main.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), KoinComponent, Disposer {


    var screenName: String = ""

    override val disposeBag: CompositeDisposable = CompositeDisposable()

    internal fun addDisposableInternal(d: Disposable) {
        this.disposeBag.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        this.disposeBag.clear()
    }

    inline fun ViewModel.launch(
        coroutineContext: CoroutineContext = CoroutineContextProvider().main,
        crossinline block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(coroutineContext) { block() }
    }
}

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

interface Disposer {
    val disposeBag: CompositeDisposable
}



