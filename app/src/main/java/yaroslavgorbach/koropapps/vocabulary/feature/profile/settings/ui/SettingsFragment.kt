package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation.SettingsViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.host
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings), ChoseThemeDialog.Callback {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    interface ThemeChangedFragment {
        fun onThemeChanged(theme: Theme)
        fun onUiModeChanged(uiMode: UiMode)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SettingsViewModel> { viewModelFactory }

    private lateinit var settingsView: SettingsView

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
            .settingsComponent()
            .create()
            .inject(this)
    }

    private fun initView() {
        settingsView = SettingsView(
            FragmentSettingsBinding.bind(requireView()),
            object : SettingsView.Callback {
                override fun onChoseTheme(themes: List<Theme>, uiMode: UiMode) {
                    choseThemeDialog(themes, uiMode)
                }

                override fun onBack() {
                    onBackPressed()
                }
            })
    }

    fun choseThemeDialog(themes: List<Theme>, uiMode: UiMode) {
        ChoseThemeDialog.newInstance(themes, uiMode).show(childFragmentManager, null)
    }

    private fun initObservers() {
        viewModel.observeCurrentTheme(requireContext())
            .observe(viewLifecycleOwner, settingsView::setCurrentTheme)

        viewModel.observeThemes(requireContext())
            .observe(viewLifecycleOwner, settingsView::setThemes)

        viewModel.observeUiMode(requireContext())
            .observe(viewLifecycleOwner, settingsView::setUiMode)
    }

    override fun onThemeChanged(theme: Theme) {
        viewModel.changeTheme(requireContext(), theme)

        host<ThemeChangedFragment>().onThemeChanged(theme)
    }

    override fun onUiModeChanged(uiMode: UiMode) {
        viewModel.changeUiMode(requireContext(), uiMode)

        host<ThemeChangedFragment>().onUiModeChanged(uiMode)
    }
}