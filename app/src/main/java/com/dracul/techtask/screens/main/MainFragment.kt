package com.dracul.techtask.screens.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.view.updatePaddingRelative
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dracul.techtask.R
import com.dracul.techtask.databinding.FragmentMainBinding
import com.dracul.techtask.screens.main.recycler.ProductAdapter
import com.dracul.techtask.viewmodels.MainViewModel
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = ProductAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       lifecycleScope.launch {
           viewModel.listProduct.collect{
               adapter.submitList(it)
           }
       }
        Log.e("",adapter.currentList.size.toString())
        Log.e("","?")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.run {
            rvList.layoutManager = GridLayoutManager(context, 2)
            rvList.adapter = adapter

            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                binding.rvList.updatePaddingRelative(bottom = systemBars.bottom)
                insets
            }
        }

        return binding.root
    }


}