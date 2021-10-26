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
    FIRST_TONGUE_TWISTER_COMPLETE(R.string.achievement_first_tongue_twister)
}