package yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentProfileBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.InfoDialog
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.presentation.ProfileViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.host
import yaroslavgorbach.koropapps.vocabulary.utils.setDefaultStatusBarColor
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile), InfoDialog.Host {

    interface Router {
        fun onOpenLevelClick()
        fun onOpenSettingsClick()
    }

    companion object {
        private const val APP_LINK =
            "https://play.google.com/store/apps/details?id=yaroslavgorbach.andersen.vocabulary"

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
        activity?.setDefaultStatusBarColor()
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

                override fun onSettings() {
                    host<Router>().onOpenSettingsClick()
                }

                override fun onRemoveAds() {

                }

                override fun onShare() {
                    shareApp()
                }

                override fun onRate() {
                    rateApp()
                }

                override fun onLevel() {
                    host<Router>().onOpenLevelClick()
                }

                override fun onPhrase(phrase: String) {
                    showPhraseDialog(phrase)
                }
            })
    }

    private fun shareApp() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, APP_LINK)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun rateApp() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(APP_LINK)
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun showPhraseDialog(phrase: String) {
        InfoDialog.newInstance(title = getString(R.string.phrase_of_day), message = phrase)
            .show(childFragmentManager, null)
    }

    fun initObservers() {
        viewModel.chartDayUi.observe(viewLifecycleOwner, profileView::setChart)

        viewModel.levelInfoUi.observe(viewLifecycleOwner, profileView::setLevel)

        lifecycleScope.launch {
            viewModel.observePhrase()
                .observe(viewLifecycleOwner, profileView::setPhrase)
        }
    }
}