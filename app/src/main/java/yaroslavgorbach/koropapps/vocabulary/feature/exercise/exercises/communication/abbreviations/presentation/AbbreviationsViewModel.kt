package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.communication.abbreviations.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import javax.inject.Inject

class AbbreviationsViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    saveStatisticsInteractor: SaveStatisticsInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor
) {
    private val abbreviations: List<String>
        get() = application.applicationContext.resources.getStringArray(
            WordCategory.ABBREVIATIONS.resId
        ).toList()

    private val _abbreviation = MutableLiveData<String>()

    val abbreviation: LiveData<String>
        get() = _abbreviation

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    init {
        generateNewAbbreviation()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateNewAbbreviation()
    }

    private fun generateNewAbbreviation() {
        _abbreviation.value = abbreviations.random()
    }
}