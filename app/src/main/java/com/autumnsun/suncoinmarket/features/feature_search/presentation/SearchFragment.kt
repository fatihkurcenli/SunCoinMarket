package com.autumnsun.suncoinmarket.features.feature_search.presentation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.autumnsun.suncoinmarket.R
import com.autumnsun.suncoinmarket.core.base.BaseFragment
import com.autumnsun.suncoinmarket.core.utils.hideKeyboard
import com.autumnsun.suncoinmarket.databinding.FragmentSearchBinding
import com.autumnsun.suncoinmarket.features.feature_search.presentation.adapter.SearchAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private var searchAdapter = SearchAdapter()
    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchWidget()
        binding.searchRecyclerview.adapter = searchAdapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchState.collectLatest { state ->
                    if (state.isLoading) {
                        binding.loadingProgressBar.isVisible = true
                        binding.searchRecyclerview.isVisible = false
                    } else {
                        if (state.coins.isNotEmpty()) {
                            binding.loadingProgressBar.isVisible = false
                            binding.searchRecyclerview.isVisible = true
                            searchAdapter.submitList(state.coins)
                            searchAdapter.notifyDataSetChanged()
                        }
                        if (state.error.isNotBlank()) {
                            Snackbar.make(binding.root, state.error, Snackbar.LENGTH_SHORT)
                                .setAnimationMode(Snackbar.ANIMATION_MODE_FADE).show()
                        }
                    }
                }
            }
        }
    }

    private fun searchWidget() {
        binding.autoCompleteTextView.doAfterTextChanged { queryText ->
            //viewModel.suggestionDataApi(queryText.toString())
            viewModel.searchCrypto(queryText.toString())
            /*if (viewModel.searchCrypto().value != queryText.toString()) {
                viewModel.searchCrypto(queryText.toString())
            }*/
        }
        binding.autoCompleteTextView.setOnItemClickListener { _, _, _, _ ->
            hideKeyboard()
        }
        binding.autoCompleteTextView.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard()
                binding.autoCompleteTextView.apply {
                    if (isPopupShowing) {
                        this.dismissDropDown()
                    }
                }
            }
            true
        }
    }
}