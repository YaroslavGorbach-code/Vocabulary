package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model

class ExercisesWithFilterUi(
    val exercisesUi: List<ExerciseUi>,
    val filterUi: ExerciseCategoryFilterUi
) {

    val areFavoritesEmpty: Boolean =
        filterUi == ExerciseCategoryFilterUi.FAVORITE && exercisesUi.isEmpty()

}