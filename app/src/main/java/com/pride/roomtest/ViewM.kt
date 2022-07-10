package com.pride.roomtest

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.coroutines.launch

class ViewM(application: Application): AndroidViewModel(application) {
    var list : MutableLiveData<ArrayList<Name>> = MutableLiveData()
    val dataBase = DataBase.getDatabase(application.applicationContext)
    fun readDb() {
        viewModelScope.launch {

            dataBase.nameDao().getAll()
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    list.value = it.toCollection(ArrayList())
                }, {
                    Log.d("error","error $it")
                }, {

                })
        }
    }

}