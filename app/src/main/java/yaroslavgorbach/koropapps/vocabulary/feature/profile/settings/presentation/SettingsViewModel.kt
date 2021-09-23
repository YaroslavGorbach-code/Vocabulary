package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.settings.ChangeThemeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveCurrentThemeInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveThemesInteractor
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val changeThemeInteractor: ChangeThemeInteractor,
    private val observeCurrentThemeInteractor: ObserveCurrentThemeInteractor,
    private val observeThemesInteractor: ObserveThemesInteractor
) : ViewModel() {

    fun observeCurrentTheme(context: Context): LiveData<Theme> {
        return observeCurrentThemeInteractor(context).asLiveData(viewModelScope.coroutineContext)
    }

    fun onChangeThemeCLick(context: Context, theme: Theme) {
        viewModelScope.launch { changeThemeInteractor(context, theme) }
    }

    fun observeThemes(context: Context): LiveData<List<Theme>> {
        return observeThemesInteractor(context).asLiveData()
    }
}