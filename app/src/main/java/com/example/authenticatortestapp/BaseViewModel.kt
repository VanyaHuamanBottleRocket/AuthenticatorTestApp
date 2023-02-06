package com.example.authenticatortestapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

open class BaseViewModel : ViewModel() {
    val ioDispatcher = Dispatchers.IO
}
