package com.example.navigation.tv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.navigation.common.ViewPagerClickHandler
import com.example.navigation.databinding.TvItemBinding
import com.example.navigation.storage.db.models.Tv

/**
 * Created by Srikant Karnani on 29/7/19.
 */
class TvAdapter (val list: List<Tv>,

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
        val tvItemBinding = TvItemBinding.inflate(layoutInflater)
        list[position].genreMap = genreMap
        tvItemBinding.tv = list[position]
        tvItemBinding.root.setOnClickListener { viewPagerClick.onClick(list[position].id) }
        container.addView(tvItemBinding.root)
        return tvItemBinding.root
    }


    override fun getCount(): Int {
        return list.size
    }


}





















