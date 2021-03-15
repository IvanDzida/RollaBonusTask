package com.yolo.rollabonustask.repo

import androidx.lifecycle.MutableLiveData
import com.yolo.rollabonustask.App
import com.yolo.rollabonustask.model.Video

class VideoRepo {

    fun fetchVideos(): MutableLiveData<List<Video>> {
        val videos: MutableLiveData<List<Video>> = MutableLiveData()
        videos.value = App.getInstance().getVideos()

        return videos
    }
}