package com.example.navigation.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.LayoutImageBinding

/**
 * Created by Srikant Karnani on 5/8/19.
 */
class ImagesAdapter(private val movieDetailsViewModel: MovieDetailViewModel) :
    RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {
    private var listOfPath = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun setImages(list: ArrayList<String>) {
        listOfPath = list
    }

    override fun getItemCount(): Int {
        return listOfPath.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(movieDetailsViewModel, position)
    }

    class ImageViewHolder(private val binding: LayoutImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: MovieDetailViewModel, position: Int) {
            binding.viewModel = viewModel
            binding.position = position
            binding.executePendingBindings()
        }
    }

}