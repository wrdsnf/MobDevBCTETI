package com.example.crud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.crud.ui.theme.CRUDTheme
import androidx.compose.material3.MaterialTheme
import com.example.crud.adapter.MainAdapter
import com.example.crud.databinding.ActivityMainBinding
import com.example.crud.ui.MainScreen
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //mainAdapter.submitList()
        binding.rvMain.adapter = mainAdapter
    }
}