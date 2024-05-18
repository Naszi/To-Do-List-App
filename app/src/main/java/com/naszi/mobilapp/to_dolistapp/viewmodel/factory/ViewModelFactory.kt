package com.naszi.mobilapp.to_dolistapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val creators: Map<Class<out ViewModel>, () -> ViewModel>
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull() {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException("Unknown ViewModel class: $modelClass")

        @Suppress("UNCHECKED_CAST")
        return creator() as T
    }
}
