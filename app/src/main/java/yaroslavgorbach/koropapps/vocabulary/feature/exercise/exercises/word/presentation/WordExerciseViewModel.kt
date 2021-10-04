package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseWordMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import javax.inject.Inject

class WordExerciseViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    saveStatisticsInteractor: SaveStatisticsInteractor
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor
) {

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    init {
        generateWord()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateWord()
    }

    private fun generateWord() {
        _word.value = ExerciseNameToExerciseWordMapper.map(
            exerciseType.getExerciseName(),
            application.resources
        )
//        when (exerciseType.getExerciseName()) {
//            ExerciseName.HALF -> {
//                // TODO: 9/6/2021 доработь этот метод так, чтобы первая буква была не гласная а вторая гласная
//                _word.value = words.random() + words.random()
//            }
//            ExerciseName.THREE_LETTERS -> {
//                _word.value = words.random() + words.random() + words.random()
//            }
//            ExerciseName.NARRATOR_ADJECTIVES,
//            ExerciseName.NARRATOR_NOUN,
//            ExerciseName.NARRATOR_VERBS -> {
//                _word.value = Random.nextInt(3, 15).toString()
//            }
//            else -> {
//                _word.value = words.random()
//            }
//        }
    }
}