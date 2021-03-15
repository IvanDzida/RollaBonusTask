package com.yolo.rollabonustask.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yolo.rollabonustask.BR
import com.yolo.rollabonustask.R
import com.yolo.rollabonustask.databinding.ItemVideoBinding
import com.yolo.rollabonustask.model.Video
import com.yolo.rollabonustask.utils.ItemSelectedListener

class VideoAdapter(private val itemSelectedListener: ItemSelectedListener) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    var videos: List<Video>

    init {
        videos = ArrayList()
    }

    fun addData(videoList: List<Video>) {
        this.videos = videoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemVideoBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_video,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videos[position])

        holder.binding.root.setOnClickListener{
            itemSelectedListener.onItemSelected(1, position)
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class ViewHolder(val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.model, data)
            binding.executePendingBindings()
        }
    }
}