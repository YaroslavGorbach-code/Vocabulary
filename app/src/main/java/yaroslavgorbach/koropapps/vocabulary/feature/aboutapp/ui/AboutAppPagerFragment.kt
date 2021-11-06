package yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentAboutAppBinding
import yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.ui.recycler.AboutAppPageAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.host

class AboutAppPagerFragment: Fragment(R.layout.fragment_about_app) {

    interface Router {
        fun onNavigationScreen()
    }

    companion object {
        fun newInstance() = AboutAppPagerFragment()
    }

    private var _binding: FragmentAboutAppBinding? = null

    val binding: FragmentAboutAppBinding
        get() = requireNotNull(_binding)

    private var pageAdapter: AboutAppPageAdapter? = null

    private fun initViews() {
        _binding = FragmentAboutAppBinding.bind(requireView())

        pageAdapter = AboutAppPageAdapter {
            host<Router>().onNavigationScreen()
        }
        binding.pager.adapter = pageAdapter
        binding.wormDotsIndicator.setViewPager2(binding.pager)

        binding.toolbar.setNavigationOnClickListener {
            host<Router>().onNavigationScreen()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}