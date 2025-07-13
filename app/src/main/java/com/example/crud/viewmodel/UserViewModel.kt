package com.example.crud.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import com.example.crud.repository.UserRepository

class UserViewModel : ViewModel() {
    private val repo = UserRepository()

    val users = repo.getUsers().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addUser(name: String, email: String) = viewModelScope.launch {
        repo.addUser(name, email)
    }

    fun deleteUser(id: String) = viewModelScope.launch {
        repo.deleteUser(id)
    }
}
