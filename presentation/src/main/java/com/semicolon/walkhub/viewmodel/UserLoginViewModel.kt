package com.semicolon.walkhub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semicolon.walkhub.util.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun getLoginData() {
        viewModelScope.launch {
            dataStoreManager.getId.collect {
                id.value = it
            }
        }

        viewModelScope.launch {
            dataStoreManager.getPw.collect {
                pw.value = it
            }
        }
    }

    fun setLoginData(id: String, pw: String) {
        viewModelScope.launch {
            dataStoreManager.setData(id, pw)
        }
    }
}