package yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model

import yaroslavgorbach.koropapps.vocabulary.R

sealed class Achievement {
    abstract val nameRes: Int
    abstract val iconAchievedRes: Int
    abstract val iconNotAchievedRes: Int
    var isAchieved: Boolean = false

    class FirstDailyTrainingCompleted(
        override val nameRes: Int = R.string.achievement_first_daily_training,
        override val iconAchievedRes: Int = R.drawable.ic_level_10,
        override val iconNotAchievedRes: Int = R.drawable.ic_close,
    ) : Achievement()
}