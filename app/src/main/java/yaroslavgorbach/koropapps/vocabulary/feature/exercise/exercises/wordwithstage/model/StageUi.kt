package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model

data class StageUi(
    val number: Int,
    val numberOfAllStages: Int,
    val text: String,
    var isActive: Boolean,
    var isFinished: Boolean,
    val isLast: Boolean
)