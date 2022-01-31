package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.mapper

import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model.StageUi

class ExerciseShortDescriptionsToListStagesMapper {

    fun map(descriptions: List<String>): List<StageUi> {
        return descriptions.flatMapIndexed { index, element ->
            listOf(
                StageUi(
                    number = index,
                    numberOfAllStages = descriptions.size,
                    text = element,
                    isActive = index == 0,
                    isFinished = false,
                    isLast = descriptions.size == index - 1
                )
            )
        }
    }
}