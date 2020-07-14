package com.liberaid.itservice_task.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.liberaid.itservice_task.R
import com.liberaid.itservice_task.datasource.UsersComparator
import com.liberaid.itservice_task.objects.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_user.*

class RVUsersAdapter(private val viewHolderListener: IViewHolderListener,
                     diffCallback: DiffUtil.ItemCallback<User> = UsersComparator()) : PagingDataAdapter<User, RVUsersAdapter.ViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)

        user?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.card_user, parent, false)

        return ViewHolder(
            mainView,
            viewHolderListener
        )
    }

    class ViewHolder(override val containerView: View, private val viewHolderListener: IViewHolderListener) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(user: User) {
            tvUsername.text = user.login

            Glide.with(containerView)
                .load(user.avatarUrl)
                .into(ivAvatar)

            containerView.setOnClickListener { viewHolderListener.onUserClick(user.login) }
        }
    }

    interface IViewHolderListener {
        fun onUserClick(username: String)
    }
}