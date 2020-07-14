package com.liberaid.itservice_task.datasource

import androidx.recyclerview.widget.DiffUtil
import com.liberaid.itservice_task.objects.User

class UsersComparator : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}