package yaroslavgorbach.koropapps.vocabulary.data.achievements.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.utils.getAppComputeDrawable

private val Context.achievementsDataStoreImp: DataStore<Preferences> by preferencesDataStore(name = "achievements")

class AchievementsDataStoreImp(private val context: Context) : AchievementsDataStore {

    private val achievements: List<Achievement> = listOf(
        Achievement(
            name = AchievementName.FIRST_DAILY_TRAINING_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_active_first_daily_training),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_not_active_first_daily_training)
        ),
        Achievement(
            name = AchievementName.FIRST_TONGUE_TWISTER_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_active_first_tongue_twister),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_not_active_first_tongue_twister)
        ),
        Achievement(
            name = AchievementName.FIRST_IMPROVISATION_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_active_first_improvisation),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_not_active_first_improvisation)
        ),
        Achievement(
            name = AchievementName.FIRST_VOCABULARY_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_active_first_vocabulary),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_not_active_first_vocabulary)
        ),
        Achievement(
            name = AchievementName.SEVEN_DAILY_TRAININGS_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.DICTIONARY_ADJECTIVES_OVER_NORM,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.DICTIONARY_NOUNS_OVER_NORM,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.DICTIONARY_VERBS_OVER_NORM,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.ALL_TONGUE_TWISTERS_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        )
    )

    override fun observe(): Flow<List<Achievement>> {
        return context.achievementsDataStoreImp.data
            .map { prefs ->
                achievements.map { achievement ->
                    val isAchieved = prefs[booleanPreferencesKey(achievement.name.name)]

                    achievement.isAchieved = isAchieved ?: false

                    achievement
                }
            }
    }

    override fun achieve(name: AchievementName) {
        GlobalScope.launch {
            context.achievementsDataStoreImp.edit { prefs ->
                prefs[booleanPreferencesKey(name.name)] = true
            }
        }
    }
}