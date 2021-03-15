package com.yolo.rollabonustask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yolo.rollabonustask.model.CastDevice
import com.yolo.rollabonustask.repo.CastDeviceRepo

class CastDeviceViewModel : ViewModel() {

    var deviceRepo: CastDeviceRepo? = null
    var mutableLiveData: MutableLiveData<List<CastDevice>>? = null
    private var selectedDevice: CastDevice? = null

    init {
        deviceRepo = CastDeviceRepo()
    }

    fun getDevices(): LiveData<List<CastDevice>> {
        if (mutableLiveData == null) {
            mutableLiveData = deviceRepo!!.fetchDevices()
        }

        return mutableLiveData!!
    }

    fun setDevice(device: CastDevice) {
        selectedDevice = device
    }

    fun getDevice(): CastDevice? {
        return selectedDevice
    }
}