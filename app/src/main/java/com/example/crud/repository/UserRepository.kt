package com.example.crud.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import com.example.crud.model.User
import com.google.firebase.firestore.ktx.toObject




class UserRepository {
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("users")
    private val usersCollection = db.collection("users")

    fun getUsers(): Flow<List<User>> = callbackFlow {
        var lastList: List<User>? = null

        val listener = collection.addSnapshotListener { snapshot, _ ->
            val currentList = snapshot?.documents?.mapNotNull { doc ->
                doc.toObject(User::class.java)?.copy(id = doc.id)
            } ?: emptyList()

            if (currentList != lastList) {
                trySend(currentList)
                lastList = currentList
            }
        }
        awaitClose { listener.remove() }
    }

    suspend fun addUser(name: String, email: String) {
        val user = User(name = name, email = email)
        usersCollection.add(user)
    }

    suspend fun deleteUser(id: String) {
        usersCollection.document(id).delete()
    }
}
