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
                name = ExerciseName.ALPHABET_ADJECTIVES,
                aim = 1
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.ALPHABET_NOUN,
                aim = 1
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.ALPHABET_VERBS,
                aim = 1
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.TAUTOGRAMS,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.NARRATOR_NOUN,
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
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.ANTONYMS_AND_SYNONYMS,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.TEN,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.ASSOCIATIONS,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.REMEMBER_ALL,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.GAME_I_KNOW_5_NAMES,
                aim = 5
            ),
            TrainingExerciseEntity(
                trainingId = trainingEntity.id,
                name = ExerciseName.THREE_LITER_JAR,
                aim = 5
            )
        )
    }
}