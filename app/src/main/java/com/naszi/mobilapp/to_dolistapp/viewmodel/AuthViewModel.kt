package com.naszi.mobilapp.to_dolistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naszi.mobilapp.to_dolistapp.model.user.Result
import com.naszi.mobilapp.to_dolistapp.repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private var userRepository: UserRepository
): ViewModel() {
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(
                email,
                password,
                firstName,
                lastName
            )
        }
    }

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }
}