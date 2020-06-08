package com.baudelioandalon.firebasewithkotlinmvvm.domain.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.baudelioandalon.firebasewithkotlinmvvm.model.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class Repository{
    private val docRef = FirebaseFirestore.getInstance().collection("Users")
    private var registration: ListenerRegistration? = null
    fun finishListener(){
        registration?.remove()
        Log.e("destroy listener", "correct")
    }
    fun getUserData(): MutableLiveData<MutableList<User>>{
        val mutableData = MutableLiveData<MutableList<User>>()
        val listData : MutableList<User> = mutableListOf()
        registration = docRef.addSnapshotListener { t, _ ->
            if (t!!.documents.isNotEmpty()){
                    val data = User(t.documentChanges[0].document.getString("idKey")!!, t.documentChanges[0].document.getString("title")!!,
                            t.documentChanges[0].document.getString("imageUri")!!, t.documentChanges[0].document.getString("description")!!)
                    Log.e("metadata", t.documentChanges[0].type.toString())
                when (t.documentChanges[0].type.toString()){
                    "MODIFIED" -> listData[listData.indexOfFirst { it.idKey == data.idKey }] = data
                    "ADDED" -> listData.add(data)
                    "REMOVED" -> listData.removeAt(listData.indexOfFirst { it.idKey == data.idKey })
                }
//                    if(t.documentChanges[0].type.toString() == "MODIFIED"){
//                        listData[listData.indexOfFirst { it.idKey == data.idKey }] = data
//                    }else if(t.documentChanges[0].type.toString() == "ADDED"){
//                        listData.add(data)
//                    }else if(t.documentChanges[0].type.toString() == "REMOVED"){
//                        listData.removeAt(listData.indexOfFirst { it.idKey == data.idKey })
//                    }
                }else{
                listData.clear()
            }
            mutableData.value = listData
        }
        return mutableData
    }

}
