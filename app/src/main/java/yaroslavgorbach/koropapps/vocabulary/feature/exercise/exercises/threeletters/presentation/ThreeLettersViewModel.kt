package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.threeletters.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseWordCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import javax.inject.Inject

class ThreeLettersViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val saveStatisticsInteractor: SaveStatisticsInteractor
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor
) {

    private val letters: List<String>
        get() = application.applicationContext.resources.getStringArray(
            ExerciseWordCategory.LETTERS.resId
        ).toList()

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _letter = MutableLiveData<String>()

    val letter: LiveData<String>
        get() = _letter

    init {
        generateLetters()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateLetters()
    }

    private fun generateLetters() {
        _letter.value = letters.random() + letters.random() + letters.random()
    }
}