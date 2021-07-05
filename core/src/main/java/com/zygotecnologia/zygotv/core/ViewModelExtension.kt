package com.zygotecnologia.zygotv.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun <T> ViewModel.onBackgroundAsync(block: suspend CoroutineScope.() -> T) = viewModelScope.async(Dispatchers.IO, block = block)

fun ViewModel.onBackgroundSync(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(Dispatchers.IO, block = block)