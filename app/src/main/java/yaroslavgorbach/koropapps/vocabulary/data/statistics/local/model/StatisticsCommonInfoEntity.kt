package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatisticsCommonInfoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    var summaryTrainingTimeMc: Long,
    var summaryTrainingTimeVocabularyMc: Long,
    var summaryTrainingTimeDictionMc: Long,
    var summaryTrainingTimeSenseOfHumorMc: Long,
    var summaryTrainingTimeCommunicationMc: Long,
    var exercisesCompleted: Int,
    var dailyTrainingsCompleted: Int,
) {
    val summaryTrainingTimeMinutes: Long
        get() = summaryTrainingTimeMc / 60000

    val summaryTrainingTimeCommunicationMinutes: Long
        get() = summaryTrainingTimeCommunicationMc / 60000

    val summaryTrainingTimeDictionMinutes: Long
        get() = summaryTrainingTimeDictionMc / 60000

    val summaryTrainingTimeVocabularyMinutes: Long
        get() = summaryTrainingTimeVocabularyMc / 60000

    val summaryTrainingTimeSenseOfHumorMinutes: Long
        get() = summaryTrainingTimeSenseOfHumorMc / 60000
}
