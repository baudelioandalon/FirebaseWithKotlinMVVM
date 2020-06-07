package com.baudelioandalon.firebasewithkotlinmvvm.domain.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.baudelioandalon.firebasewithkotlinmvvm.User
import com.baudelioandalon.firebasewithkotlinmvvm.viewmodel.MainViewModel
import com.google.firebase.firestore.FirebaseFirestore

class Repository{
    fun getUserData(): MutableLiveData<MutableList<User>>{
        val mutableData = MutableLiveData<MutableList<User>>()
        val listData : MutableList<User> = mutableListOf()
        val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
        val docRef = firestore.collection("Users")
        docRef.addSnapshotListener { t, _ ->
            if (t!!.documents.isNotEmpty()){
                    val data = User(
                        t.documentChanges[0].document.getString("idKey")!!,
                        t.documentChanges[0].document.getString("title")!!,
                        t.documentChanges[0].document.getString("imageUri")!!,
                        t.documentChanges[0].document.getString("description")!!)
                    Log.e("metada",t!!.documentChanges[0].type.toString())
                    if(t.documentChanges[0].type.toString() == "MODIFIED"){
                        listData[listData.indexOfFirst { it.idKey == data.idKey }] = data
                    }else if(t.documentChanges[0].type.toString() == "ADDED"){
                        listData.add(data)
                    }else if(t.documentChanges[0].type.toString() == "REMOVED"){
                        listData.removeAt(listData.indexOfFirst { it.idKey == data.idKey })
                    }
                }else{
                listData.clear()
            }
            mutableData.value = listData
        }
        return mutableData
    }
}
