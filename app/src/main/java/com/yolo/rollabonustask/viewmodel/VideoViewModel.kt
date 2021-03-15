package com.yolo.rollabonustask.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yolo.rollabonustask.model.Video
import com.yolo.rollabonustask.repo.VideoRepo

class VideoViewModel : ViewModel() {

    var videoRepo: VideoRepo? = null
    var mutableLiveData: MutableLiveData<List<Video>>? = null
    private var selectedVideo: Video? = null

    init {
        videoRepo = VideoRepo()
    }

    fun getVideos(): LiveData<List<Video>> {
        if (mutableLiveData == null) {
            mutableLiveData = videoRepo!!.fetchVideos()
        }

        return mutableLiveData!!
    }

    fun setVideo(video: Video) {
        selectedVideo = video
    }

    fun getVideo(): Video? {
        return selectedVideo
    }
}


