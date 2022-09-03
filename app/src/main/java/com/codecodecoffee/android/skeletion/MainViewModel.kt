package com.codecodecoffee.android.skeletion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codecodecoffee.android.core.account.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sessionManager: SessionManager) : ViewModel() {


    fun test() = viewModelScope.launch{
        sessionManager.logout()
    }
}