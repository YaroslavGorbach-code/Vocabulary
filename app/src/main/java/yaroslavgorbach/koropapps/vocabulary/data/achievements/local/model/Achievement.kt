package yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model

import android.graphics.drawable.Drawable
import yaroslavgorbach.koropapps.vocabulary.R

data class Achievement(
    val name: AchievementName,
    val iconAchieved: Drawable?,
    val iconNotAchieved: Drawable?,
    var isAchieved: Boolean = false
)

enum class AchievementName(val nameRes: Int) {
    FIRST_DAILY_TRAINING_COMPLETE(R.string.achievement_first_daily_training)
}