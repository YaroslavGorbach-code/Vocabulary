package yaroslavgorbach.koropapps.vocabulary.feature.profile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentProfileBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.presentation.ProfileViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

    private lateinit var profileView: ProfileView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initDagger() {
        appComponent()
            .profileComponent()
            .create()
            .inject(this)
    }

    fun initView() {
        profileView = ProfileView(
            FragmentProfileBinding.bind(requireView()),
            object : ProfileView.Callback {
                override fun onNextChart() {
                    viewModel.onNextChart()
                }

                override fun onPreviousChart() {
                    viewModel.onPreviousChart()
                }
            })
    }

    fun initObservers() {
        viewModel.chartDayUi.observe(viewLifecycleOwner, profileView::setChart)
    }
}