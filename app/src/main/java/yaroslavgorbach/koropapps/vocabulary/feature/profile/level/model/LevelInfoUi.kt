package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model

data class LevelInfoUi(val test: String) {

    val level: String
        get() = "1"

    val progress: Int
        get() = 50

    val summaryTrainingTime: String
        get() = "20"

    val performedExercises: String
        get() = "20"

    val performedDailyTrainings: String
        get() = "20"
}
