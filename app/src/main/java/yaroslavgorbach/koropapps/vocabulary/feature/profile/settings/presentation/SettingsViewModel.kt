package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.settings.*
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val changeThemeInteractor: ChangeThemeInteractor,
    private val observeCurrentThemeInteractor: ObserveCurrentThemeInteractor,
    private val observeThemesInteractor: ObserveThemesInteractor,
    private val observeUiModeInteractor: ObserveUiModeInteractor,
    private val changeUiModeInteractor: ChangeUiModeInteractor
) : ViewModel() {

    fun observeCurrentTheme(context: Context): LiveData<Theme> {
        return observeCurrentThemeInteractor(context).asLiveData(viewModelScope.coroutineContext)
    }

    fun changeTheme(context: Context, theme: Theme) {
        viewModelScope.launch { changeThemeInteractor(context, theme) }
    }

    fun observeThemes(context: Context): LiveData<List<Theme>> {
        return observeThemesInteractor(context).asLiveData()
    }

    fun changeUiMode(context: Context, uiMode: UiMode) {
        viewModelScope.launch { changeUiModeInteractor(context, uiMode) }
    }

    fun observeUiMode(context: Context): LiveData<UiMode> {
        return observeUiModeInteractor(context).asLiveData()
    }
}