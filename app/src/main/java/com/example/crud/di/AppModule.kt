package com.example.crud.di

import com.example.crud.api.API
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

interface AppModule {
    val api:API
    val firestore: FirebaseFirestore
}

class AppModuleImpl: AppModule{
    override val api: API by lazy {
        API()
    }
    override val firestore: FirebaseFirestore by lazy {
        Firebase.firestore
    }
}