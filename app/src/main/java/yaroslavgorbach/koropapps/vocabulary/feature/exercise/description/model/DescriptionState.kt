package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model

enum class DescriptionState(val maxLines: Int) {
    COLLAPSED(4), OPENED(Int.MAX_VALUE)
}