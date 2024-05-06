package com.dracul.techtask.screens.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePaddingRelative
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.dracul.techtask.databinding.FragmentMainBinding
import com.dracul.techtask.screens.main.chiprecycler.ChipsAdapter
import com.dracul.techtask.screens.main.recycler.ProductAdapter
import com.dracul.techtask.screens.main.state.State
import com.dracul.techtask.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class MainFragment : Fragment(), ProductAdapter.OnItemListener, ChipsAdapter.OnChipListner {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = ProductAdapter(this)
    private val chipsAdapter = ChipsAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.listProduct.collect {
                adapter.submitList(it)
            }
        }
        lifecycleScope.launch {
            viewModel.categories.collect{
                chipsAdapter.submitList(it)
                Log.e("", it.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            viewModel.error.collect {
                Log.e("","collecting: $it")
                if (it.isNotEmpty()) {
                    Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                    binding.tvError.text = it
                }
            }
        }
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    State.Error -> {
                        binding.llError.isVisible = true
                        binding.rvList.isVisible = false
                    }

                    State.Main -> {
                        binding.llError.isVisible = false
                        binding.rvList.isVisible = true
                    }
                }
            }
        }



        binding.run {
            rvList.layoutManager = GridLayoutManager(context, 2)
            rvList.adapter = adapter
            rvList.setHasFixedSize(true)
            chipRv.adapter = chipsAdapter
            chipRv.itemAnimator = null
            binding.btnTryAgain.setOnClickListener {
                viewModel.reloadPage()
            }
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                binding.rvList.updatePaddingRelative(bottom = systemBars.bottom)
                insets
            }
        }

        return binding.root
    }

    override fun onEnd() {
        if (viewModel.selectedCategory.value==null)
            viewModel.nextPage()

    }

    override fun onChipChecked(category: String) {
        viewModel.setCategoryFilter(category)
        Log.e("","chip checked")
    }
}