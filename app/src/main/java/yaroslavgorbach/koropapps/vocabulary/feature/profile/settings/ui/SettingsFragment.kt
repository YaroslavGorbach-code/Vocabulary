package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation.SettingsViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    companion object {
        fun newInstance() = SettingsFragment()
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
                override fun onChoseTheme() {
                    choseThemeDialog()
                }
            })
    }

    fun choseThemeDialog() {
        DialogChoseTheme.newInstance().show(childFragmentManager, null)
    }

    private fun initObservers() {
        viewModel.observeCurrentTheme(requireContext())
            .observe(viewLifecycleOwner, settingsView::setTheme)
    }
}