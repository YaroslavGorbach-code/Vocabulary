package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper

import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.Level
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi.Companion.NEXT_LEVEL_STEP_MINUTE

class MapTrainingTimeToLevel {
    fun map(trainingTimeMinutes: Long): Level {
        return Level(((trainingTimeMinutes / NEXT_LEVEL_STEP_MINUTE).toInt()).toString())
    }
}