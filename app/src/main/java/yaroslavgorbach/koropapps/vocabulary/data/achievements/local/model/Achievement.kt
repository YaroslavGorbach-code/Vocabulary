package yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model

import android.graphics.drawable.Drawable
import yaroslavgorbach.koropapps.vocabulary.R

data class Achievement(
    val name: AchievementName,
    private val iconAchieved: Drawable?,
    private val iconNotAchieved: Drawable?,
    var isAchieved: Boolean = false
) {
    val icon: Drawable?
        get() = if (isAchieved) iconAchieved else iconNotAchieved
}

enum class AchievementName(val nameRes: Int) {
    FIRST_DAILY_TRAINING_COMPLETE(R.string.achievement_first_daily_training),
    FIRST_TONGUE_TWISTER_COMPLETE(R.string.achievement_first_tongue_twister),
    FIRST_IMPROVISATION_COMPLETE(R.string.achievement_first_improvisation),
    FIRST_VOCABULARY_COMPLETE(R.string.achievement_first_vocabulary),
    SEVEN_DAILY_TRAININGS_COMPLETE(R.string.achievement_seven_daily_training),
    DICTIONARY_ADJECTIVES_OVER_NORM(R.string.achievement_dictionary_adjectives_over_norm),
    DICTIONARY_NOUNS_OVER_NORM(R.string.achievement_dictionary_nouns_over_norm),
    DICTIONARY_VERBS_OVER_NORM(R.string.achievement_dictionary_verbs_over_norm),
    ALL_TONGUE_TWISTERS_COMPLETE(R.string.achievement_all_tongue_twisters),
}