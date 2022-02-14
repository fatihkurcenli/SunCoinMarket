package com.autumnsun.suncoinmarket.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding get() = _binding!!
    open fun initializeUi() {}

/*    protected val activityViewModel: MainViewModel
        get() = (activity as MainActivity).mainViewModel*/

/*    protected val mainActivity: MainActivity
        get() = (activity as MainActivity)*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initializeUi()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}