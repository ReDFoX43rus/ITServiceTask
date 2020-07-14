package com.liberaid.itservice_task.ui.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.flowable
import com.liberaid.itservice_task.datasource.UsersPagingSource
import com.liberaid.itservice_task.datasource.UsersProvider
import com.liberaid.itservice_task.objects.User

class MainActivityViewModel @ViewModelInject constructor(private val usersProvider: UsersProvider) : ViewModel() {

    private val pagerMutableLiveData = MutableLiveData<PagingData<User>>()
    val pagerLiveData: LiveData<PagingData<User>>
        get() = pagerMutableLiveData

    private val errorMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable>
        get() = errorMutableLiveData

    private val pagerDisposable = Pager(PagingConfig(45)) {
        UsersPagingSource(usersProvider) { errorMutableLiveData.value = it }
    }
        .flowable
        .cachedIn(viewModelScope)
        .subscribe {
            pagerMutableLiveData.value = it
        }

    override fun onCleared() {
        if(!pagerDisposable.isDisposed) {
            pagerDisposable.dispose()
        }
    }
}