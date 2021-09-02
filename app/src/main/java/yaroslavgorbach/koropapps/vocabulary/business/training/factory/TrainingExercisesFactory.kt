package yaroslavgorbach.koropapps.vocabulary.business.training.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import kotlin.random.Random

class TrainingExercisesFactory {
    fun create(trainingEntity: TrainingEntity): List<TrainingExerciseEntity> {
        return when (trainingEntity.numberOfTraining) {
            in 1..5 -> createExercises(5, trainingEntity.id)
            in 5..10 -> createExercises(6, trainingEntity.id)
            in 10..15 -> createExercises(7, trainingEntity.id)
            in 15..20 -> createExercises(8, trainingEntity.id)
            in 20..25 -> createExercises(9, trainingEntity.id)
            in 25..30 -> createExercises(10, trainingEntity.id)
            else -> createExercises(10, trainingEntity.id)
        }
    }

    private fun createExercises(anim: Int, trainingId: Long): List<TrainingExerciseEntity> {
        return when (Random.nextInt(0, 2)) {
            0 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ALPHABET_ADJECTIVES,
                        aim = 1
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ALPHABET_NOUN,
                        aim = 1
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ALPHABET_VERBS,
                        aim = 1
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ANTONYMS_AND_SYNONYMS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.THREE_LITER_JAR,
                        aim = anim
                    ),
                )
            }
            1 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.NARRATOR_NOUN,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.NARRATOR_ADJECTIVES,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.NARRATOR_VERBS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ASSOCIATIONS,
                        aim = anim
                    ),
                )
            }
            else -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.GAME_I_KNOW_5_NAMES,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.TAUTOGRAMS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.TEN,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.REMEMBER_ALL,
                        aim = anim
                    ),
                )
            }
        }
    }
}