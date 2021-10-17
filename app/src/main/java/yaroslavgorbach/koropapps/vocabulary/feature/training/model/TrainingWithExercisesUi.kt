package yaroslavgorbach.koropapps.vocabulary.feature.training.model

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity

class TrainingWithExercisesUi(private val trainingWithExercisesEntity: TrainingWithExercisesEntity) {

    val exercises: List<TrainingExerciseUi>
        get() = trainingWithExercisesEntity
            .exercises
            .filter {
                when (currentFilter) {
                    TrainingExerciseCategoryFilterUi.ALL -> true
                    TrainingExerciseCategoryFilterUi.COMMUNICATION -> it.category == ExerciseCategory.COMMUNICATION
                    TrainingExerciseCategoryFilterUi.VOCABULARY -> it.category == ExerciseCategory.VOCABULARY
                    TrainingExerciseCategoryFilterUi.DICTION_AND_ARTICULATION -> it.category == ExerciseCategory.DICTION_AND_ARTICULATION
                }
            }
            .map(::TrainingExerciseUi)
            .filter { it.isFinished.not() }

    val progress: Int
        get() = trainingWithExercisesEntity.progress

    val daysWithoutInterruption: Int
        get() = trainingWithExercisesEntity.training.daysWithoutInterruption

    val availableFilters: List<TrainingExerciseCategoryFilterUi>
        get() {
            val filters: MutableList<TrainingExerciseCategoryFilterUi> = arrayListOf()

            return filters.apply {
                val isVocabularyFilter = trainingWithExercisesEntity.exercises.any {
                    it.category == ExerciseCategory.VOCABULARY && it.isFinished.not()
                }

                if (isVocabularyFilter) {
                    filters.add(TrainingExerciseCategoryFilterUi.VOCABULARY)
                }

                val isCommunicationFilter = trainingWithExercisesEntity.exercises.any {
                    it.category == ExerciseCategory.COMMUNICATION && it.isFinished.not()
                }

                if (isCommunicationFilter) {
                    filters.add(TrainingExerciseCategoryFilterUi.COMMUNICATION)
                }

                val isDictionFilter = trainingWithExercisesEntity.exercises.any {
                    it.category == ExerciseCategory.DICTION_AND_ARTICULATION && it.isFinished.not()
                }

                if (isDictionFilter) {
                    filters.add(TrainingExerciseCategoryFilterUi.DICTION_AND_ARTICULATION)
                }

                val isAllFilter = isVocabularyFilter || isVocabularyFilter || isDictionFilter

                if (isAllFilter) {
                    filters.add(TrainingExerciseCategoryFilterUi.ALL)
                }
            }
        }


    var currentFilter = TrainingExerciseCategoryFilterUi.ALL

    var currentViewPagerPage = 0
}