package com.example.navigation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.navigation.MainActivity
import com.example.navigation.R
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {
    private lateinit var parentActivity: MainActivity
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = activity as MainActivity
    }

    fun updateStatusBarColor() {
        val window = activity!!.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity!!, R.color.blue_grey_900)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.loginFragment, true)
            .build()
        viewModel.getLoginStatus().observe(this, Observer<Boolean> {
            if (it) {
                parentActivity.changeStart()
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null, navOptions)
            }
        })
        if (viewModel.checkAlreadyLogged()) {
            parentActivity.changeStart()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, null, navOptions)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setOnClickListener { viewModel.login("as", "as") }
    }

}
