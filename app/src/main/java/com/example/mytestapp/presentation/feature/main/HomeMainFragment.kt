package com.example.mytestapp.presentation.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mytestapp.databinding.FragmentHomeMainBinding
import com.example.mytestapp.presentation.feature.adapter.HomeMainViewPagerAdapter
import com.example.mytestapp.presentation.feature.main.winget.BottomMenu
import kotlinx.android.synthetic.main.fragment_home_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeMainFragment : Fragment()  , BottomMenu.BottomMenuListener{
    private val binding by lazy { FragmentHomeMainBinding.inflate(layoutInflater) }
    private val viewModel: HomeMainViewModel by viewModel()

    private var pageSelected = BottomMenu.BottomMenuHomePages.HOME

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setViewPagerState()
        bottomMenu.setMenuClickListener(this@HomeMainFragment)
    }

    private fun observeViewModel() = with(viewModel){
        buttonMenuClick.observe(viewLifecycleOwner) { data ->
            data?.getContentIfNotHandled()?.let {
                onBottomMenuClick(it)
                bottomMenu.setViewSelected(it)
            }
        }
    }

    private fun setViewPagerState() {
        homeMainViewPager.adapter = getViewPagerAdapter()
        homeMainViewPager.setCurrentItem(pageSelected.ordinal, false)
        homeMainViewPager.isUserInputEnabled = false
    }

    private fun getViewPagerAdapter(): HomeMainViewPagerAdapter {
        return HomeMainViewPagerAdapter(
            childFragmentManager,
            lifecycle,
        )
    }

    override fun onBottomMenuClick(page: BottomMenu.BottomMenuHomePages) {
        pageSelected = page
        homeMainViewPager.setCurrentItem(page.ordinal, false)
    }

    override fun onResume() {
        super.onResume()
        bottomMenu.setViewSelected(pageSelected)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}