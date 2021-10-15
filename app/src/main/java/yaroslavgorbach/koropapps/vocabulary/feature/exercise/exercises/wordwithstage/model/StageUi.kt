package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model

data class StageUi(
    val number: Int,
    val text: String,
    var isActive: Boolean,
    var isFinished: Boolean,
    val isLast: Boolean
) {
    companion object {
        const val TONGUE_TWISTERS_STAGES_NUMBER = 4
    }
}
