package com.example.crud

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.crud.adapter.MainAdapter
import com.example.crud.databinding.ActivityFirestoreBinding
import com.example.crud.helpers.viewModelFactory
import com.example.crud.viewmodel.FirestoreViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class FirestoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirestoreBinding
    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: FirestoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirestoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = MainAdapter()
        viewModel = ViewModelProvider(
            this,
            viewModelFactory { FirestoreViewModel(MainApplication.appModule.firestore) }
        )[FirestoreViewModel::class.java]

        viewModel.users.observe(this) {
            adapter.submitList(it)
        }

        with(binding){
            rvUser.adapter = adapter

            btnAdd.setOnClickListener {
                val name = tieName.text.toString().trim()
                val email = tieEmail.text.toString().trim()

                if (name.isNotEmpty() && email.isNotEmpty()) {
                    viewModel.addUser(name, email)
                    tieName.text?.clear()
                    tieEmail.text?.clear()
                }
            }
        }
    }
}