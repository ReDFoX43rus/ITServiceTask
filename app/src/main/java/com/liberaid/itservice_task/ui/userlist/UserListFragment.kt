package com.liberaid.itservice_task.ui.userlist

import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.liberaid.itservice_task.R
import com.liberaid.itservice_task.ui.BaseFragment
import com.liberaid.itservice_task.ui.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_userlist.*

class UserListFragment : BaseFragment(),
    RVUsersAdapter.IViewHolderListener {

    override val layoutId = R.layout.fragment_userlist

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pagingAdapter = RVUsersAdapter(this)
        rvUsers.adapter = pagingAdapter
        rvUsers.layoutManager = LinearLayoutManager(context)

        viewModel.pagerLiveData.observe(viewLifecycleOwner) { data ->
            pagingAdapter.submitData(lifecycle, data)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { throwable ->
            Toast.makeText(context, throwable.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUserClick(username: String) {
        findNavController().navigate(R.id.action_userListFragment_to_userFragment, bundleOf("username" to username))
    }
}