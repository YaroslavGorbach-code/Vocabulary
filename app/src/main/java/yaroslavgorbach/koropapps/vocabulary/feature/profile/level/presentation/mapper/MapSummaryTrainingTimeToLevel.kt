package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper

import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.Level

class MapSummaryTrainingTimeToLevel {
    fun map(summaryTrainingTimeMinutes: Long): Level {
        return when (summaryTrainingTimeMinutes) {
            in 0..10 -> Level.ZERO()
            in 10..20 -> Level.One()
            in 20..30 -> Level.Two()
            in 30..40 -> Level.Three()
            in 40..50 -> Level.Four()
            in 50..65 -> Level.Fife()
            in 65..80 -> Level.Six()
            in 80..95 -> Level.Seven()
            in 95..110 -> Level.Eight()
            in 110..115 -> Level.Nine()
            in 115..130 -> Level.Ten()
            in 130..145 -> Level.Eleven()
            in 145..160 -> Level.Twelve()
            in 160..175 -> Level.Thirteen()
            in 175..190 -> Level.Fourteen()
            in 190..210 -> Level.Fifteen()
            in 210..230 -> Level.Sixteen()
            else -> Level.ZERO()
        }
    }
}