package com.baudelioandalon.firebasewithkotlinmvvm.viewmodel

import androidx.lifecycle.*
import com.baudelioandalon.firebasewithkotlinmvvm.domain.data.network.Repository
import com.baudelioandalon.firebasewithkotlinmvvm.model.User

class MainViewModel: ViewModel(), LifecycleObserver {
    private val repo = Repository()
    private val mutableData = MutableLiveData<MutableList<User>>()
    fun fetchUser(): MutableLiveData<MutableList<User>> {
        repo.getUserData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
    fun getData(): MutableLiveData<MutableList<User>> {
        return mutableData
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
      fetchUser()
    }

}