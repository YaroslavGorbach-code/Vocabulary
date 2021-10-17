package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithcategory.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseRandomWordMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.WordCategory
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import javax.inject.Inject

class WordWithCategoryViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    saveStatisticsInteractor: SaveStatisticsInteractor,
    voiceRecorder: VoiceRecorder,
    permissionManager: PermissionManager
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor,
    voiceRecorder,
    permissionManager
) {

    private val exerciseNameToExerciseRandomWordMapper =
        ExerciseNameToExerciseRandomWordMapper(application.resources)

    private val categories: List<String>
        get() = application.applicationContext.resources.getStringArray(
            WordCategory.Category().resArray
        ).toList()

    val description: String
        get() = application.getString(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        )

    private val _letter = MutableLiveData<String>()

    val letter: LiveData<String>
        get() = _letter

    private val _category = MutableLiveData<String>()

    val category: LiveData<String>
        get() = _category

    init {
        generateLetter()
        generateCategory()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateCategory()
        generateLetter()
    }

    private fun generateLetter() {
        _letter.value = exerciseNameToExerciseRandomWordMapper.map(exerciseType.getExerciseName())
    }

    private fun generateCategory() {
        _category.value = categories.random()
    }

}