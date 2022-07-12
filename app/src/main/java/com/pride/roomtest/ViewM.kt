package com.pride.roomtest

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers.newThread
import kotlinx.coroutines.launch

class ViewM(application: Application): AndroidViewModel(application) {
    var list = MutableLiveData<ArrayList<Name>>()
    val dataBase = DataBase.getDatabase(application.applicationContext)
    var message = MutableLiveData<String>()

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
    fun writeToDb(name:Name) {
        viewModelScope.launch {
            dataBase.nameDao().insertToDB(name)
                .subscribeOn(newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    message.value = "Complete"
                }, {
                    Log.d("error","error $it")
                })
        }
    }
}