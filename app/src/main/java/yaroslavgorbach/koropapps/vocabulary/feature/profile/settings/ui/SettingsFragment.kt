package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentSettingsBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation.SettingsViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.host
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import yaroslavgorbach.koropapps.vocabulary.utils.scheduleNotification
import javax.inject.Inject

@InternalCoroutinesApi
class SettingsFragment : Fragment(R.layout.fragment_settings), ChoseThemeDialog.Callback,
    NotificationSettingsDialog.Host {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    interface ThemeChangedListener {
        fun onThemeChanged(theme: Theme, isNeedToRecreate: Boolean)
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
                    showChoseThemeDialog(themes, uiMode)
                }

                override fun onNotificationSettings(notification: Notification) {
                    showNotificationsSettingsDialog(notification)
                }

                override fun onAutoRecording(isChecked: Boolean) {
                    viewModel.changeAutoRecordingSettingState(isChecked)
                }

                override fun onBack() {
                    onBackPressed()
                }
            })
    }

    private fun initObservers() {
        viewModel.currentTheme.observe(viewLifecycleOwner, settingsView::setCurrentTheme)

        viewModel.themes.observe(viewLifecycleOwner, settingsView::setThemes)

        viewModel.uiMode.observe(viewLifecycleOwner, settingsView::setUiMode)

        viewModel.notification.observe(viewLifecycleOwner, settingsView::setNotification)

        viewModel.isAutoRecordSettingChecked.observe(
            viewLifecycleOwner,
            settingsView::setAutoRecordStateChecked
        )
    }

    fun showChoseThemeDialog(themes: List<Theme>, uiMode: UiMode) {
        ChoseThemeDialog.newInstance(themes, uiMode).show(childFragmentManager, null)
    }

    fun showNotificationsSettingsDialog(notification: Notification) {
        NotificationSettingsDialog.newInstance(notification).show(childFragmentManager, null)
    }

    override fun onThemeChanged(theme: Theme) {
        viewModel.changeTheme(theme){
            host<ThemeChangedListener>().onThemeChanged(theme, true)
        }
    }

    override fun onUiModeChanged(uiMode: UiMode) {
        viewModel.changeUiMode(uiMode)

        host<ThemeChangedListener>().onUiModeChanged(uiMode)
    }

    override fun onNotificationChanged(notification: Notification) {
        viewModel.updateNotification(notification)

        requireContext().scheduleNotification(notification)
    }
}