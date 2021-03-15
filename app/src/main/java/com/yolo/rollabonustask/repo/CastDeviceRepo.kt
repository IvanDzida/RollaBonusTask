package com.yolo.rollabonustask.repo

import androidx.lifecycle.MutableLiveData
import com.yolo.rollabonustask.model.CastDevice

class CastDeviceRepo {

    fun fetchDevices(): MutableLiveData<List<CastDevice>> {
        val devices: MutableLiveData<List<CastDevice>> = MutableLiveData()

        val testList = mutableListOf<CastDevice>()

        testList.add(CastDevice("Device 1"))
        testList.add(CastDevice("Device 2"))
        testList.add(CastDevice("Device 3"))
        testList.add(CastDevice("Device 4"))
        testList.add(CastDevice("Device 5"))

        devices.value = testList

        return devices
    }
}