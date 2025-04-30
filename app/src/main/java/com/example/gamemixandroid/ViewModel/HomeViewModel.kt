package com.example.ui.home

import androidx.lifecycle.ViewModel
import com.example.model.Greeting

class HomeViewModel : ViewModel() {
    private var _greeting = Greeting.defaultMessage
    val greeting: String
        get() = _greeting
    fun updateGreeting() {
        _greeting = if (_greeting == Greeting.defaultMessage)
            Greeting.alternateMessage
        else
            Greeting.defaultMessage
        print("Here")
    }
}
