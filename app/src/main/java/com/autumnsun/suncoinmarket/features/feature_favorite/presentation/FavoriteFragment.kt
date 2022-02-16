package com.autumnsun.suncoinmarket.features.feature_favorite.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.databinding.FragmentFavoriteBinding
import com.autumnsun.suncoinmarket.features.feature_favorite.presentation.adapter.FavoriteAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRecyclerView()
        lifecycleScope.launch {
            viewModel.favoriteState.collectLatest { state ->
                if (state.isLoading) {
                    binding.recyclerview.isVisible = false
                    binding.loadingProgressBar.customLoadingAnimation.isVisible = true
                } else {
                    if (state.favoriteCoins.isNotEmpty()) {
                        binding.loadingProgressBar.customLoadingAnimation.isVisible = false
                        binding.recyclerview.isVisible = true
                        favoriteAdapter.submitList(state.favoriteCoins)
                    }
                    if (state.errorMessage.isNotBlank()) {
                        Snackbar.make(binding.root, state.errorMessage, Snackbar.LENGTH_SHORT)
                            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE).show()
                    }
                }
            }
        }
        viewModel.getAllFavorites()
    }

    private fun createRecyclerView() {
        favoriteAdapter = FavoriteAdapter {
            //TODO navigation detail
        }
        binding.recyclerview.apply {
            addItemDecoration(
                (DividerItemDecoration(
                    requireActivity(), LinearLayoutManager.VERTICAL
                ))
            )
            adapter = favoriteAdapter
        }
    }
}