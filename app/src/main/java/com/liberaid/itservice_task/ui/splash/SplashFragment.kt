package com.liberaid.itservice_task.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.liberaid.itservice_task.R
import com.liberaid.itservice_task.ui.BaseFragment

class SplashFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_splash

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_userListFragment)
        }, 1000L)
    }
}