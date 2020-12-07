package com.felipeacerbi.pickyourmeal.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.felipeacerbi.pickyourmeal.R
import com.felipeacerbi.pickyourmeal.repository.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.CoroutineContext

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(this, { action.invoke(it) })
}

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

fun CoroutineScope.safeLaunch(
    error: (Exception) -> Unit = {},
    context: CoroutineContext = coroutineContext,
    block: suspend CoroutineScope.() -> Unit
) = launch(context) {
    try {
        block.invoke(this)
    } catch (exception: Exception) {
        error.invoke(exception)
    }
}

suspend inline fun <T> FlowCollector<Result>.emitAllResults(flow: Flow<T>) =
    flow.collect { emit(Result.Success(it)) }

fun Flow<Result>.onFailResult(
    action: (Result.Fail) -> Unit
): Flow<Result> = onEach { if (it is Result.Fail) action.invoke(it) }

suspend fun <T> Flow<Result>.collectLatestSuccess(
    action: suspend (value: T) -> Unit
) = filterIsInstance<Result.Success<T>>()
    .collectLatest { action.invoke(it.data) }

fun Exception.handleError() =
    when (this) {
        is HttpException -> R.string.network_error
        is IOException -> R.string.network_error
        else -> R.string.unknown_error
    }