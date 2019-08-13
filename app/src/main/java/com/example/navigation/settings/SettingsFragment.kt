package com.example.navigation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.navigation.R
import com.example.navigation.storage.AppPreferences
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (AppPreferences.darkTheme) {
            switch_theme.isChecked = true
        }
        switch_theme.setOnCheckedChangeListener { _, check ->
            AppPreferences.darkTheme = !AppPreferences.darkTheme
            activity?.recreate()
        }

    }

}
