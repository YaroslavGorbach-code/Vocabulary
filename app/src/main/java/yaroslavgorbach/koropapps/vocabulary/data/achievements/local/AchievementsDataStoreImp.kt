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
            name = AchievementName.FIRST_DICTION_COMPLETE,
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
            name = AchievementName.SEVEN_DAILY_TRAININGS_IN_A_ROW_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_7_trainings_in_a_row),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_7_trainings_in_a_row)
        ),
        Achievement(
            name = AchievementName.FIFTEEN_DAILY_TRAININGS_IN_A_ROW_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_15_trainings_in_a_row),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_15_trainings_in_a_row)
        ),
        Achievement(
            name = AchievementName.THIRTY_DAILY_TRAININGS_IN_A_ROW_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_30_trainings_in_a_row),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_30_trainings_in_a_row)
        ),
        Achievement(
            name = AchievementName.DICTIONARY_ADJECTIVES_OVER_NORM,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_dictionary_ajectives_over_norm),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_dictionary_ajectives_over_norm)
        ),
        Achievement(
            name = AchievementName.DICTIONARY_NOUNS_OVER_NORM,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_dictionary_nouns_over_norm),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_dictionary_noun_over_norm)
        ),
        Achievement(
            name = AchievementName.DICTIONARY_VERBS_OVER_NORM,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_dictionary_verds_over_norm),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_dictionary_verds_over_norm)
        ),
        Achievement(
            name = AchievementName.ALL_TONGUE_TWISTERS_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_all_tongue_twisters_complete),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_all_tongue_twisters_complete)
        ),
        Achievement(
            name = AchievementName.ALL_ALPHABET_EXERCISES_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_active_all_alphabet_complete),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achivement_not_active_all_alphabet_complete)
        ),
        Achievement(
            name = AchievementName.SPENT_MORE_THEN_HOUR_ON_TRAINING,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_active_spent_more_then_hour_on_training),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_achievement_not_active_spent_more_then_hour_on_training)
        ),
        Achievement(
            name = AchievementName.MORE_THEN_TEN_EXERCISES_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.MORE_THEN_FIFTY_EXERCISES_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.MORE_THEN_ONE_HUNDRED_EXERCISES_COMPLETE,
            iconAchieved = context.getAppComputeDrawable(R.drawable.ic_level_10),
            iconNotAchieved = context.getAppComputeDrawable(R.drawable.ic_level_20)
        ),
        Achievement(
            name = AchievementName.MORE_THEN_ONE_THOUSAND_EXERCISES_COMPLETE,
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