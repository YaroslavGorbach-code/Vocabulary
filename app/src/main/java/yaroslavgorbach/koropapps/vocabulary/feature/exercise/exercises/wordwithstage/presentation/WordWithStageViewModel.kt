package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveAutoRecordStateInteractor
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveKeepScreenSettingInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseRandomWordMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.mapper.ExerciseShortDescriptionsToListStagesMapper
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model.StageUi
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import javax.inject.Inject

class WordWithStageViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    observeKeepScreenSettingInteractor: ObserveKeepScreenSettingInteractor,
    saveStatisticsInteractor: SaveStatisticsInteractor,
    observeAutoRecordStateInteractor: ObserveAutoRecordStateInteractor,
    voiceRecorder: VoiceRecorder,
    permissionManager: PermissionManager,
    adManager: AdManager
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor,
    observeAutoRecordStateInteractor,
    observeKeepScreenSettingInteractor,
    voiceRecorder,
    permissionManager,
    adManager
) {
    private val exerciseNameToExerciseRandomWordMapper =
        ExerciseNameToExerciseRandomWordMapper(application.resources)

    private val shortDescriptions: List<String>
        get() = application.resources.getStringArray(
            ExerciseNameToShortDescriptionResMapper().map(exerciseType.getExerciseName())
        ).toList()

    private var stages: MutableList<StageUi> = emptyList<StageUi>().toMutableList()

    private val _stage: MutableLiveData<StageUi> = MutableLiveData()

    val stage: LiveData<StageUi>
        get() = _stage

    val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    init {
        generateWord()
        refreshStages()
    }

    private fun refreshStages() {
        stages = ExerciseShortDescriptionsToListStagesMapper()
            .map(shortDescriptions).toMutableList()
        _stage.value = stages.first()
    }

    private fun generateWord() {
        _word.value = exerciseNameToExerciseRandomWordMapper.map(exerciseType.getExerciseName())
    }

    fun onNextStageClick() {
       stages.apply {
            removeFirst()
            if (firstOrNull() != null) {
                _stage.value = first()
            } else {
                generateWord()
                refreshStages()
                onNextClick()
            }
        }
    }
}