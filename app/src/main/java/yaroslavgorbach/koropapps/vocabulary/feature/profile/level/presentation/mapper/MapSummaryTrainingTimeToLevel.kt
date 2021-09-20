package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper

import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.Level

class MapSummaryTrainingTimeToLevel {
    fun map(summaryTrainingTimeMinutes: Long): Level {
        return when (summaryTrainingTimeMinutes) {
            in Level.Zero().levelTrainingTimeRequired..Level.Zero().nextLevelTrainingTimeRequired -> Level.Zero()
            in Level.One().levelTrainingTimeRequired..Level.One().nextLevelTrainingTimeRequired -> Level.One()
            in Level.Two().levelTrainingTimeRequired..Level.Two().nextLevelTrainingTimeRequired -> Level.Two()
            in Level.Three().levelTrainingTimeRequired..Level.Three().nextLevelTrainingTimeRequired -> Level.Three()
            in Level.Four().levelTrainingTimeRequired..Level.Four().nextLevelTrainingTimeRequired -> Level.Four()
            in Level.Fife().levelTrainingTimeRequired..Level.Fife().nextLevelTrainingTimeRequired -> Level.Fife()
            in Level.Six().levelTrainingTimeRequired..Level.Six().nextLevelTrainingTimeRequired -> Level.Six()
            in Level.Seven().levelTrainingTimeRequired..Level.Seven().nextLevelTrainingTimeRequired -> Level.Seven()
            in Level.Eight().levelTrainingTimeRequired..Level.Eight().nextLevelTrainingTimeRequired -> Level.Eight()
            in Level.Nine().levelTrainingTimeRequired..Level.Nine().nextLevelTrainingTimeRequired -> Level.Nine()
            in Level.Ten().levelTrainingTimeRequired..Level.Ten().nextLevelTrainingTimeRequired -> Level.Ten()
            in Level.Eleven().levelTrainingTimeRequired..Level.Eleven().nextLevelTrainingTimeRequired -> Level.Eleven()
            in Level.Twelve().levelTrainingTimeRequired..Level.Twelve().nextLevelTrainingTimeRequired -> Level.Twelve()
            in Level.Thirteen().levelTrainingTimeRequired..Level.Thirteen().nextLevelTrainingTimeRequired -> Level.Thirteen()
            in Level.Fourteen().levelTrainingTimeRequired..Level.Fourteen().nextLevelTrainingTimeRequired -> Level.Fourteen()
            in Level.Fifteen().levelTrainingTimeRequired..Level.Fifteen().nextLevelTrainingTimeRequired -> Level.Fifteen()
            in Level.Sixteen().levelTrainingTimeRequired..Level.Sixteen().nextLevelTrainingTimeRequired -> Level.Sixteen()
            else -> Level.Zero()
        }
    }
}