package com.xlwe.educatationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xlwe.educatationproject.core.App
import com.xlwe.educatationproject.databinding.ActivityMainBinding
import com.xlwe.educatationproject.presentation.MainAdapter

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = (application as App).mainViewModel

        val mainAdapter = MainAdapter()
        binding.recyclerView.adapter = mainAdapter

        viewModel.observe(this) {
            mainAdapter.update(it)
        }

        viewModel.fetchBooks()
    }
}