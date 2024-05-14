package com.naszi.mobilapp.to_dolistapp

import android.app.Application
import com.naszi.mobilapp.to_dolistapp.model.Graph

class TodoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provider(context = this)
    }
}
