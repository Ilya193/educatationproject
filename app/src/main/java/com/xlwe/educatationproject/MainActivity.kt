package com.xlwe.educatationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import com.xlwe.educatationproject.core.App
import com.xlwe.educatationproject.databinding.ActivityMainBinding
import com.xlwe.educatationproject.presentation.MainAdapter
import com.xlwe.educatationproject.presentation.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = (application as App).mainViewModel

        val retry = object : MainAdapter.Retry {
            override fun tryAgain() {
                viewModel.fetchBooks()
            }
        }
        val collapse = object : MainAdapter.CollapseListener {
            override fun collapseOrExpand(id: Int) {
                viewModel.collapseOrExpand(id)
            }
        }
        val mainAdapter = MainAdapter(retry, collapse)

        binding.recyclerView.adapter = mainAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

        viewModel.observe(this) {
            mainAdapter.submitList(it)
        }

        viewModel.fetchBooks()
    }

    override fun onPause() {
        viewModel.saveCollapsedStates()
        super.onPause()
    }
}