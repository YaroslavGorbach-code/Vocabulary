package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper

import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.Level

class MapSummaryTrainingTimeToLevel() {
    fun map(summaryTrainingTime: Int): Level {
        return when (summaryTrainingTime) {
            in 0..10 -> Level.ZERO()
            in 10..20 -> Level.First()
            in 20..30 -> Level.Second()
            else -> Level.ZERO()
        }
    }
}