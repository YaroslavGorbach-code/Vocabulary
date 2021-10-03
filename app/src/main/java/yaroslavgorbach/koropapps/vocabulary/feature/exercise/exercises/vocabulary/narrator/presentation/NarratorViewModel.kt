package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.narrator.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import javax.inject.Inject
import kotlin.random.Random

class NarratorViewModel @Inject constructor(
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
    private val _numberOfWords = MutableLiveData<String>()

    val numberOfWords: LiveData<String>
        get() = _numberOfWords

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    init {
        generateWords()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateWords()
    }

    private fun generateWords() {
        _numberOfWords.value = Random.nextInt(3, 15).toString()
    }
}