package com.yolo.rollabonustask

import android.app.Application
import com.yolo.rollabonustask.model.Video

class App: Application() {

    private val localVideos = mutableListOf<Video>()

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun setVideos(videos: List<Video>){
        localVideos.addAll(videos)
    }

    fun getVideos(): List<Video>{
        return localVideos
    }

    companion object {
        private lateinit var INSTANCE: App

        fun getInstance(): App {
            return INSTANCE
        }
    }
}