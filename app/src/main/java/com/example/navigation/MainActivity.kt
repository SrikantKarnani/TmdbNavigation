package com.example.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.navigation.storage.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppPreferences.darkTheme)
            setTheme(R.style.AppThemeDark)
        else
            setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
    }

    fun changeStart() {
        val navHost = mainFragment as NavHostFragment
        val graph = navHost.navController
            .navInflater.inflate(R.navigation.main_nav_graph)
        graph.startDestination = R.id.homeFragment
    }
}
