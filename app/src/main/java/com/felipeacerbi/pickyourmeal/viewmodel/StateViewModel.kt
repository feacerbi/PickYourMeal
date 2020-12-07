package com.felipeacerbi.pickyourmeal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipeacerbi.pickyourmeal.util.SingleLiveEvent
import com.felipeacerbi.pickyourmeal.viewstate.ViewEffect
import com.felipeacerbi.pickyourmeal.viewstate.ViewState
import com.felipeacerbi.pickyourmeal.viewstate.ViewStateReducer

abstract class StateViewModel<VS : ViewState, VE : ViewEffect>(
    private val initialState: VS
) : ViewModel() {

    private val viewStateMutable = MutableLiveData(initialState)
    private val viewEffectMutable = SingleLiveEvent<VE>()

    protected val viewState: LiveData<VS> = viewStateMutable
    protected val viewEffect: LiveData<VE> = viewEffectMutable

    protected fun updateState(reducer: ViewStateReducer<VS>) {
        viewStateMutable.value = reducer.reduce(viewStateMutable.value ?: initialState)
    }

    protected fun updateEffect(effect: VE) {
        viewEffectMutable.value = effect
    }
}