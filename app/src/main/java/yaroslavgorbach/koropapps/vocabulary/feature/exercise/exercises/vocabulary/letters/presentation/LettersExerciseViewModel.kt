package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.letters.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToWordCategoryMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import javax.inject.Inject

class LettersExerciseViewModel @Inject constructor(
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

    private val letters: List<String>
        get() = application.applicationContext.resources.getStringArray(
            ExerciseNameToWordCategoryMapper().map(exerciseType.getExerciseName()).resId
        ).toList()

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _letter = MutableLiveData<String>()

    val letter: LiveData<String>
        get() = _letter

    init {
        generateLetter()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateLetter()
    }

    private fun generateLetter() {
        when (exerciseType.getExerciseName()) {
            ExerciseName.HALF -> {
                // TODO: 9/6/2021 доработь этот метод так, чтобы первая буква была не гласная а вторая гласная
                _letter.value = letters.random() + letters.random()
            }
            ExerciseName.THREE_LETTERS -> {
                _letter.value = letters.random() + letters.random() + letters.random()
            }
            else -> {
                _letter.value = letters.random()
            }
        }
    }
}