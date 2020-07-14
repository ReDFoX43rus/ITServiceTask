package com.liberaid.itservice_task.ui.userprofile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.liberaid.itservice_task.R
import com.liberaid.itservice_task.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user.*

@AndroidEntryPoint
class UserFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_user

    private val viewModel: UserFragmentViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            tvUsername.text = user.login
            tvLocation.text = user.location ?: "unknown"

            Glide.with(this)
                .load(user.avatarUrl)
                .into(ivAvatar)

            tvLink.setOnClickListener {
                Intent(Intent.ACTION_VIEW, Uri.parse(user.htmlUrl)).also {
                    startActivity(it)
                }
            }

            tvLink.visibility = View.VISIBLE
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { throwable ->
            tvLink.visibility = View.INVISIBLE
            Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        }

        arguments?.getString("username")?.let { username ->
            viewModel.requestUserInfo(username)
        }
    }

}