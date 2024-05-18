package com.naszi.mobilapp.to_dolistapp.model.user

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
}