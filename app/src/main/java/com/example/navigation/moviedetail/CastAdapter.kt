package com.example.navigation.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.LayoutCastBinding
import com.example.navigation.databinding.LayoutImageBinding
import com.example.navigation.storage.db.models.Cast

/**
 * Created by Srikant Karnani on 5/8/19.
 */
class CastAdapter(private val movieDetailsViewModel: MovieDetailViewModel) :
    RecyclerView.Adapter<CastAdapter.ImageViewHolder>() {
    private var listOfPath = ArrayList<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutCastBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    fun setCast(list: ArrayList<Cast>) {
        listOfPath = list
    }

    override fun getItemCount(): Int {
        return listOfPath.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(movieDetailsViewModel, position)
    }

    class ImageViewHolder(private val binding: LayoutCastBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: MovieDetailViewModel, position: Int) {
            binding.viewModel = viewModel
            binding.position = position
            binding.executePendingBindings()
        }
    }

}