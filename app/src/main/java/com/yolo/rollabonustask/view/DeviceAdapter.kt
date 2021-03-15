package com.yolo.rollabonustask.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yolo.rollabonustask.BR
import com.yolo.rollabonustask.R
import com.yolo.rollabonustask.databinding.ItemDeviceBinding
import com.yolo.rollabonustask.model.CastDevice
import com.yolo.rollabonustask.utils.ItemSelectedListener

class DeviceAdapter(private val itemSelectedListener: ItemSelectedListener) :
    RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    private var devices: List<CastDevice>

    init {
        devices = ArrayList()
    }

    fun addDevices(deviceList: List<CastDevice>) {
        this.devices = deviceList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDeviceBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_device,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(devices[position])
        holder.binding.root.setOnClickListener {
            itemSelectedListener.onItemSelected(0, position)
        }
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    class ViewHolder(val binding: ItemDeviceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.model, data)
            binding.executePendingBindings()
        }
    }
}