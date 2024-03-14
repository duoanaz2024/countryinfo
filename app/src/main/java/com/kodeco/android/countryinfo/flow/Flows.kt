package com.kodeco.android.countryinfo.flow

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
object Flows {

    val tapFlow: StateFlow<Int>
        get() = _tapFlow

    val backFlow: StateFlow<Int>
        get() = _backFlow

    val counterFlow: StateFlow<Int>
        get() = _counterFlow

    private val _tapFlow = MutableStateFlow(0)
    private val _backFlow = MutableStateFlow(0)
    private val _counterFlow = MutableStateFlow(0)

    init {

        GlobalScope.launch {
            var timer = 0
            while (true){
                _counterFlow.value = timer++
                delay(1000)
            }
        }

    }

    fun tap(){
        _tapFlow.value = _tapFlow.value +  1
    }

    fun tapBack(){
        _backFlow.value = _backFlow.value + 1
    }

}
