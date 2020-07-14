package com.liberaid.itservice_task.ui.userprofile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.liberaid.itservice_task.datasource.UsersProvider
import com.liberaid.itservice_task.objects.User
import io.reactivex.disposables.Disposable

class UserFragmentViewModel @ViewModelInject constructor(private val usersProvider: UsersProvider) : ViewModel() {

    private val userMutableLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User>
        get() = userMutableLiveData

    private val errorMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable>
        get() = errorMutableLiveData

    private var disposable: Disposable? = null

    fun requestUserInfo(username: String) {
        disposable?.apply {
            if(!isDisposed) {
                dispose()
            }
        }

        disposable = usersProvider.getUserByUsername(username)
            .subscribe( { receivedUser ->
                userMutableLiveData.value = receivedUser
            }, { throwable ->
                errorMutableLiveData.value = throwable
            })
    }

    override fun onCleared() {
        disposable?.apply {
            if(!isDisposed){
                dispose()
            }
        }
        super.onCleared()
    }
}