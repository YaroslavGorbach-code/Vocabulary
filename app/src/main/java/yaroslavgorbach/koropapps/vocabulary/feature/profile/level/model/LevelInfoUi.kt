package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model

import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.mapper.MapTrainingTimeToLevel

data class LevelInfoUi(private val commonInfoEntity: StatisticsCommonInfoEntity) {

    companion object {
        const val NEXT_LEVEL_STEP_MS = 300000f
        const val NEXT_LEVEL_STEP_MINUTE = 5f
    }

    private fun getCorrectMaxValue(currentValueMs: Long): Float {
        var currentMaxValue = currentValueMs

        while (currentMaxValue % NEXT_LEVEL_STEP_MS != 0f) {
            currentMaxValue++
        }

        return currentMaxValue.toFloat() - getCorrectPreviousMaxValue(currentValueMs)
    }

    private fun getCorrectPreviousMaxValue(currentValueMs: Long): Float {
        var newValue = currentValueMs

        while (newValue % NEXT_LEVEL_STEP_MS != 0f) {
            newValue--
        }

        return newValue.toFloat()
    }

    val oratorLevelProgress: Int
        get() = try {
            (((commonInfoEntity.summaryTrainingTimeMc.toFloat() - getCorrectPreviousMaxValue(
                commonInfoEntity.summaryTrainingTimeMc
            )) / (getCorrectMaxValue(commonInfoEntity.summaryTrainingTimeMc))) * 100f).toInt()
        } catch (ex: Throwable) {
            0
        }

    val communicationLevelProgress: Int
        get() = try {
            (((commonInfoEntity.summaryTrainingTimeCommunicationMc.toFloat() - getCorrectPreviousMaxValue(
                commonInfoEntity.summaryTrainingTimeCommunicationMc
            )) / (getCorrectMaxValue(commonInfoEntity.summaryTrainingTimeCommunicationMc))) * 100f).toInt()
        } catch (ex: Throwable) {
            0
        }

    val vocabularyLevelProgress: Int
        get() = try {
            (((commonInfoEntity.summaryTrainingTimeVocabularyMc.toFloat() - getCorrectPreviousMaxValue(
                commonInfoEntity.summaryTrainingTimeVocabularyMc
            )) / (getCorrectMaxValue(commonInfoEntity.summaryTrainingTimeVocabularyMc))) * 100f).toInt()
        } catch (ex: Throwable) {
            0
        }

    val dictionLevelProgress: Int
        get() = try {
            (((commonInfoEntity.summaryTrainingTimeDictionMc.toFloat() - getCorrectPreviousMaxValue(
                commonInfoEntity.summaryTrainingTimeDictionMc
            )) / (getCorrectMaxValue(commonInfoEntity.summaryTrainingTimeDictionMc))) * 100f).toInt()
        } catch (ex: Throwable) {
            0
        }

    val senseOfHumorLevelProgress: Int
        get() = try {
            (((commonInfoEntity.summaryTrainingTimeSenseOfHumorMc.toFloat() - getCorrectPreviousMaxValue(
                commonInfoEntity.summaryTrainingTimeSenseOfHumorMc
            )) / (getCorrectMaxValue(commonInfoEntity.summaryTrainingTimeSenseOfHumorMc))) * 100f).toInt()
        } catch (ex: Throwable) {
            0
        }

    val vocabularyLevel: Level
        get() = MapTrainingTimeToLevel().map(commonInfoEntity.summaryTrainingTimeVocabularyMinutes)

    val senseOfHumorLevel: Level
        get() = MapTrainingTimeToLevel().map(commonInfoEntity.summaryTrainingTimeSenseOfHumorMinutes)

    val communicationLevel: Level
        get() = MapTrainingTimeToLevel().map(commonInfoEntity.summaryTrainingTimeCommunicationMinutes)


    val oratorLevel: Level
        get() = MapTrainingTimeToLevel().map(commonInfoEntity.summaryTrainingTimeMinutes)


    val dictionLevel: Level
        get() = MapTrainingTimeToLevel().map(commonInfoEntity.summaryTrainingTimeDictionMinutes)

}
