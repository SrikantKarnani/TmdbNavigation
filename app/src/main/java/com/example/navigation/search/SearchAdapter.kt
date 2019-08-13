package com.example.navigation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigation.databinding.LayoutHeaderBinding
import com.example.navigation.databinding.LayoutMediaBinding
import com.example.navigation.storage.db.models.Movie
import com.example.navigation.storage.db.models.Tv

/**
 * Created by Srikant Karnani on 7/8/19.
 */
class SearchAdapter(private val searchViewModel: SearchViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mSet = listOf<Any>()
    val HEADER_VIEW_TYPE: Int = 1
    val MEDIA_VIEW_TYPE: Int = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MediaViewHolder(LayoutMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return mSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val any = mSet[position]
        if (any is Tv) {
            (holder as MediaViewHolder).bind(
                SearchItem(
                    any.posterPath,
                    any.title,
                    any.releaseDate,
                    any.voteAverage.toFloat() / 2,
                    "Tv"
                ), searchViewModel, position
            )
        } else if (any is Movie) {
            (holder as MediaViewHolder).bind(
                SearchItem(
                    any.posterPath,
                    any.title,
                    any.releaseDate,
                    any.voteAverage.toFloat() / 2,
                    "Movie"
                ), searchViewModel, position
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    fun setList(list: List<Any>) {
        mSet = list
    }

    inner class MediaViewHolder(private val binding: LayoutMediaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(searchItem: SearchItem, viewModel: SearchViewModel, position: Int) {
            binding.vm = viewModel
            binding.searchItem = searchItem
            binding.position = position
        }
    }

    inner class HeaderViewHolder(val binding: LayoutHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    data class SearchItem(
        val posterPath: String?,
        val title: String,
        val releaseData: String,
        val ratings: Float,
        val type: String
    )
}