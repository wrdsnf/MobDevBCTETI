package com.example.crud.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crud.model.User
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreViewModel(
    private val firestore: FirebaseFirestore
): ViewModel() {
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users

    init {
        getUsers()
    }

    fun getUsers(){
        firestore.collection("users")
            .get()
            .addOnSuccessListener { result ->
                val newUsers = result.documents.map{
                    User(
                        id = it.id,
                        name = it.getString("name") ?: "",
                        email = it.getString("email") ?: ""
                    )
                }
                _users.postValue(newUsers)
            }
            .addOnFailureListener{
                _users.value = emptyList()
            }
    }

    fun addUser(name:String, email:String){
        val data = mapOf(
            "name" to name,
            "email" to email
        )

        firestore.collection("users")
            .add(data)
            .addOnSuccessListener {
                getUsers()
            }
            .addOnFailureListener { }
    }

}