package yaroslavgorbach.koropapps.vocabulary.data.achievements.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.utils.getAppComputeDrawable

private val Context.achievementsDataStoreImp: DataStore<Preferences> by preferencesDataStore(name = "achievements")

class AchievementsDataStoreImp(private val context: Context) : AchievementsDataStore {

    private val achievements: List<Achievement> = listOf(
        Achievement(
            name = AchievementName.FIRST_DAILY_TRAINING_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        )
    )

    override fun observe(): Flow<List<Achievement>> {
        return context.achievementsDataStoreImp.data
            .map { prefs ->
                achievements.onEach { achievement ->
                    val isAchieved = prefs[booleanPreferencesKey(achievement.name.name)]

                    achievement.isAchieved = isAchieved ?: false
                }
            }
    }

    override suspend fun achieve(name: AchievementName) {
        context.achievementsDataStoreImp.edit { prefs ->
            prefs[booleanPreferencesKey(name.name)] =
                prefs[booleanPreferencesKey(name.name)]?.not() ?: true
        }
    }
}