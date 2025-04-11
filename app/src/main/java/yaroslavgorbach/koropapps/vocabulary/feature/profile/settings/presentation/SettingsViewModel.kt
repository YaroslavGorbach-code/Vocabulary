package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ClearAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.records.DeleteAllRecordsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.*
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ClearAllStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.DeleteAllTrainingsInteractor
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Notification
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.utils.Event
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.send
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val changeThemeInteractor: ChangeThemeInteractor,
    private val observeCurrentThemeInteractor: ObserveCurrentThemeInteractor,
    private val observeThemesInteractor: ObserveThemesInteractor,
    private val observeUiModeInteractor: ObserveUiModeInteractor,
    private val changeUiModeInteractor: ChangeUiModeInteractor,
    private val updateNotificationInteractor: UpdateNotificationInteractor,
    private val observeNotificationInteractor: ObserveNotificationInteractor,
    private val changeAutoRecordStateInteractor: ChangeAutoRecordStateInteractor,
    private val observeAutoRecordStateInteractor: ObserveAutoRecordStateInteractor,
    private val clearAllStatisticsInteractor: ClearAllStatisticsInteractor,
    private val clearAchievementsInteractor: ClearAchievementsInteractor,
    private val deleteAllRecordsInteractor: DeleteAllRecordsInteractor,
    private val deleteAllTrainingsInteractor: DeleteAllTrainingsInteractor,
    private val observeKeepScreenSettingInteractor: ObserveKeepScreenSettingInteractor,
    private val changeKeepScreenOnInteractor: ChangeKeepScreenOnInteractor
) : ViewModel() {

    val currentTheme: LiveData<Theme>
        get() = observeCurrentThemeInteractor().asLiveData(viewModelScope.coroutineContext)

    val themes: LiveData<List<Theme>>
        get() = observeThemesInteractor().asLiveData()

    val uiMode: LiveData<UiMode>
        get() = observeUiModeInteractor().asLiveData()

    val notification: LiveData<Notification>
        get() = observeNotificationInteractor().asLiveData()

    val isKeepScreenOnSettingChecked: LiveData<Boolean>
        get() = observeKeepScreenSettingInteractor().asLiveData()

    val isAutoRecordSettingChecked: LiveData<Boolean>
        get() = observeAutoRecordStateInteractor().asLiveData()

    private val _allDataCleared: MutableLiveEvent<Event<Unit>> = MutableLiveEvent()

    val allDataCleared: LiveEvent<Event<Unit>>
        get() = _allDataCleared

    fun changeTheme(theme: Theme, doAfterChange: () -> Unit) {
        viewModelScope.launch {
            changeThemeInteractor(theme)
            doAfterChange()
        }
    }

    fun changeUiMode(uiMode: UiMode) {
        viewModelScope.launch { changeUiModeInteractor(uiMode) }
    }

    fun updateNotification(notification: Notification) {
        viewModelScope.launch { updateNotificationInteractor(notification) }
    }

    fun changeAutoRecordingSettingState(isAutoRecordChecked: Boolean) {
        viewModelScope.launch { changeAutoRecordStateInteractor(isAutoRecordChecked) }
    }

    fun changeKeepScreenOnSettingState(isChecked: Boolean) {
        viewModelScope.launch { changeKeepScreenOnInteractor(isChecked) }
    }

    fun clearAllData() {
        clearAllStatisticsInteractor()
            .andThen(deleteAllTrainingsInteractor())
            .doOnComplete {
                clearAchievementsInteractor()
                deleteAllRecordsInteractor()
                _allDataCleared.send(Event(Unit))
            }
            .subscribe()
    }

}