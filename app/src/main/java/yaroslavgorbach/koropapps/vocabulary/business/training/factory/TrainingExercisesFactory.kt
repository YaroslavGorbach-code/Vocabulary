package yaroslavgorbach.koropapps.vocabulary.business.training.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity

class TrainingExercisesFactory {
    fun create(trainingEntity: TrainingEntity): List<TrainingExerciseEntity> {
        // TODO: 8/17/2021 implement logic of creating exercises
        return listOf(
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.THREE_LITER_JAR,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.REMEMBER_ALL,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.ANTONYMS_AND_SYNONYMS,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.NARRATOR_ADJECTIVES,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.NARRATOR_VERBS,
                aim = 5
            )
        )
    }
}