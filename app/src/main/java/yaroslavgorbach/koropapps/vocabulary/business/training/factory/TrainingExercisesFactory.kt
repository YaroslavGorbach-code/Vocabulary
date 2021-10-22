package yaroslavgorbach.koropapps.vocabulary.business.training.factory

import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import kotlin.random.Random

class TrainingExercisesFactory {

    private fun createExercises(
        anim: Int,
        trainingId: Long,
        tongueTwisterExerciseName: ExerciseName
    ): List<TrainingExerciseEntity> {
        return when (Random.nextInt(0, 9)) {
            0 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.SOUND_COMBINATIONS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ALPHABET_ADJECTIVES,
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ALPHABET_NOUN,
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ALPHABET_VERBS,
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.LINGUISTIC_PYRAMIDS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.RAVEN_LOOK_LIKE_A_TABLE,
                        aim = anim
                    ),
                )
            }
            1 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
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
                        name = ExerciseName.NARRATOR_NOUN,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.STORYTELLER_IMPROVISER,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ADVANCED_BINDING,
                        aim = anim
                    ),
                )
            }
            2 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.DIFFICULT_WORDS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.TAUTOGRAMS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ANTONYMS_AND_SYNONYMS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.OTHER_ABBREVIATIONS,
                        aim = anim
                    ),
                )
            }
            3 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.SOUND_COMBINATIONS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.TEN,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.ASSOCIATIONS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.MAGIC_NAMING,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.BUYING_SELLING,
                        aim = anim
                    ),
                )
            }
            4 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.REMEMBER_ALL,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.GAME_I_KNOW_5_NAMES,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.CO_AUTHORED_WITH_DAHL,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.RORSCHACH_TEST,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.QUESTION_ANSWER,
                        aim = anim
                    ),
                )
            }
            5 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.SOUND_COMBINATIONS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.THREE_LITER_JAR,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.LIST_OF_CATEGORIES,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.WILL_NOT_BE_WORSE,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.QUESTION_ANSWER,
                        aim = anim
                    ),
                )
            }
            6 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.THREE_LETTERS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.HALF,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.RAVEN_LOOK_LIKE_A_TABLE_FILINGS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.LINGUISTIC_PYRAMIDS,
                        aim = anim
                    ),
                )
            }
            7 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.DIFFICULT_WORDS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.SPECIFICATIONS,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.WHAT_I_SEE_I_SING_ABOUT,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.OTHER_ABBREVIATIONS,
                        aim = anim
                    ),
                )
            }
            8 -> {
                listOf(
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = tongueTwisterExerciseName,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.DICTIONARY_ADJECTIVES,
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.DICTIONARY_NOUN,
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.DICTIONARY_VERBS,
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.STORYTELLER_IMPROVISER,
                        aim = anim
                    ),
                    TrainingExerciseEntity(
                        trainingId = trainingId,
                        name = ExerciseName.MAGIC_NAMING,
                        aim = anim
                    ),
                )
            }
            else -> {
                listOf()
            }
        }
    }

    fun create(trainingEntity: TrainingEntity): List<TrainingExerciseEntity> {
        return when (trainingEntity.numberOfTraining) {
            in 1..5 -> createExercises(5, trainingEntity.id, ExerciseName.TONGUE_TWISTERS_EASY)
            in 5..10 -> createExercises(6, trainingEntity.id, ExerciseName.TONGUE_TWISTERS_EASY)
            in 10..15 -> createExercises(7, trainingEntity.id, ExerciseName.TONGUE_TWISTERS_HARD)
            in 15..20 -> createExercises(8, trainingEntity.id, ExerciseName.TONGUE_TWISTERS_HARD)
            in 20..25 -> createExercises(
                9,
                trainingEntity.id,
                ExerciseName.TONGUE_TWISTERS_VERY_HARD
            )
            in 25..30 -> createExercises(
                10,
                trainingEntity.id,
                ExerciseName.TONGUE_TWISTERS_VERY_HARD
            )
            else -> createExercises(10, trainingEntity.id, ExerciseName.TONGUE_TWISTERS_VERY_HARD)
        }
    }

}