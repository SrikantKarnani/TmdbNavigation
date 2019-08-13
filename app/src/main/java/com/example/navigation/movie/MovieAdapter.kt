package com.example.navigation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.navigation.common.ViewPagerClickHandler
import com.example.navigation.databinding.MovieItemBinding
import com.example.navigation.storage.db.models.Movie


/**
 * Created by Srikant Karnani on 26/7/19.
 */
class MovieAdapter(
    val list: List<Movie>,

    val genreMap: Map<Int, String>,

    private val viewPagerClick: ViewPagerClickHandler
) : PagerAdapter() {

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val movieBinding = MovieItemBinding.inflate(layoutInflater)
        list[position].genreMap = genreMap
        movieBinding.movie = list[position]
        movieBinding.root.setOnClickListener { viewPagerClick.onClick(list[position].id) }
        container.addView(movieBinding.root)
        return movieBinding.root
    }


    override fun getCount(): Int {
        return list.size
    }


}






























































































































































































































