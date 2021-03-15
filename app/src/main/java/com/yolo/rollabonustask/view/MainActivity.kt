package com.yolo.rollabonustask.view

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yolo.rollabonustask.App
import com.yolo.rollabonustask.R
import com.yolo.rollabonustask.databinding.ActivityMainBinding
import com.yolo.rollabonustask.model.CastDevice
import com.yolo.rollabonustask.model.Video
import com.yolo.rollabonustask.utils.*
import com.yolo.rollabonustask.utils.Constants.STORAGE_PERMISSION_REQUEST_CODE
import com.yolo.rollabonustask.viewmodel.CastDeviceViewModel
import com.yolo.rollabonustask.viewmodel.VideoViewModel


class MainActivity : AppCompatActivity(), ItemSelectedListener {

    private var localVideos = mutableListOf<Video>()
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var deviceAdapter: DeviceAdapter
    private val videoViewModel = VideoViewModel()
    private val castDeviceViewModel = CastDeviceViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

        castDeviceViewModel.getDevices().observe(this,
            { t ->
                deviceAdapter.addDevices(t!!)
                videoAdapter.notifyDataSetChanged()
            })

        observeVideos()
    }

    private fun initViews() {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        videoAdapter = VideoAdapter(this)
        deviceAdapter = DeviceAdapter(this)

        binding.videoRecycler.layoutManager = LinearLayoutManager(this)
        binding.videoRecycler.adapter = videoAdapter

        binding.deviceRecycler.layoutManager = LinearLayoutManager(this)
        binding.deviceRecycler.adapter = deviceAdapter

        binding.play.setOnClickListener {
            val device = castDeviceViewModel.getDevice()
            val video = videoViewModel.getVideo()

            when {
                device == null -> {
                    Toast.makeText(this, getString(R.string.select_device), Toast.LENGTH_SHORT).show()
                }
                video == null -> {
                    Toast.makeText(this, getString(R.string.select_video), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    playVideo()
                }
            }
        }
    }

    private fun playVideo() {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.play_video_title))
            .setMessage(resources.getString(R.string.play_video_message))
            .setNeutralButton(resources.getString(R.string.ok)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun observeVideos() {
        if (Helper.hasReadStoragePermissionGranted(this)) {
            loadVideos()
            videoViewModel.getVideos().observe(this,
                { t ->
                    loadVideos()
                    videoAdapter.addData(t!!)
                    videoAdapter.notifyDataSetChanged()
                })
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        val permissions = mutableListOf<String>()
        if (!Helper.hasReadStoragePermissionGranted(this)) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (permissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissions.toTypedArray(),
                STORAGE_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun loadVideos() {
        localVideos.addAll(Helper.getLocalVideoFiles(this))

        App.getInstance().setVideos(localVideos)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_REQUEST_CODE) {

            observeVideos()
        }
    }

    override fun onItemSelected(type: Int, position: Int) {
        if (type == 0) {
            castDeviceViewModel.setDevice(CastDevice("device 1"))
        } else {
            videoViewModel.setVideo(localVideos[0])
        }
    }
}