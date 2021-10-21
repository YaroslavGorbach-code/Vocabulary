package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseRandomWordMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.mapper.ExerciseShortDescriptionsToListStagesMapper
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model.StageUi
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import javax.inject.Inject

class WordWithStageViewModel @Inject constructor(
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

    private val shortDescriptions: List<String>
        get() = application.resources.getStringArray(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        ).toList()

    private val _stages: MutableLiveData<List<StageUi>> = MutableLiveData()

    val stages: LiveData<List<StageUi>>
        get() = _stages

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    init {
        generateWord()
        refreshStages()
    }

    private fun refreshStages() {
        _stages.value = ExerciseShortDescriptionsToListStagesMapper().map(shortDescriptions)
    }

    private fun generateWord() {
        _word.value = exerciseNameToExerciseRandomWordMapper.map(exerciseType.getExerciseName())
    }

    fun onNextStageClick() {
        _stages.value.orEmpty().toMutableList().apply {
            val currentIndex = indexOfFirst { it.isActive }

            if (getOrNull(currentIndex + 1) != null) {
                find { it.isActive }!!.isFinished = true
                find { it.isActive }!!.isActive = false

                getOrNull(currentIndex + 1)!!.isActive = true

                _stages.value = this
            } else {
                generateWord()
                refreshStages()
                onNextClick()
            }
        }
    }
}