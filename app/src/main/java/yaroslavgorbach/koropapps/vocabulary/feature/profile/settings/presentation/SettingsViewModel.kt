package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.settings.*
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val changeThemeInteractor: ChangeThemeInteractor,
    private val observeCurrentThemeInteractor: ObserveCurrentThemeInteractor,
    private val observeThemesInteractor: ObserveThemesInteractor,
    private val observeUiModeInteractor: ObserveUiModeInteractor,
    private val changeUiModeInteractor: ChangeUiModeInteractor,
    private val updateNotificationInteractor: UpdateNotificationInteractor,
    private val observeNotificationInteractor: ObserveNotificationInteractor
) : ViewModel() {

    val currentTheme: LiveData<Theme>
        get() = observeCurrentThemeInteractor().asLiveData(viewModelScope.coroutineContext)

    val themes: LiveData<List<Theme>>
        get() = observeThemesInteractor().asLiveData()

    val uiMode: LiveData<UiMode>
        get() = observeUiModeInteractor().asLiveData()

    val notification: LiveData<Notification>
        get() = observeNotificationInteractor().asLiveData()

    fun changeTheme(theme: Theme) {
        viewModelScope.launch { changeThemeInteractor(theme) }
    }

    fun changeUiMode(uiMode: UiMode) {
        viewModelScope.launch { changeUiModeInteractor(uiMode) }
    }

    fun updateNotification(notification: Notification) {
        viewModelScope.launch { updateNotificationInteractor(notification) }
    }

}