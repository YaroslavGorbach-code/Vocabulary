package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.antonymssynonyms.presentation

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

class AntonymsSynonymsViewModel @Inject constructor(
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

    private val words: List<String>
        get() = application.applicationContext.resources.getStringArray(
            WordCategory.FILLINGS.resId
        ).toList()

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    init {
        generateNewWord()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateNewWord()
    }

    private fun generateNewWord() {
        _word.value = words.random()
    }

}