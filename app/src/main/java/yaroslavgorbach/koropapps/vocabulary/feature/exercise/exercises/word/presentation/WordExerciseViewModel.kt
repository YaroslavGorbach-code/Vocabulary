package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveAutoRecordStateInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseRandomWordMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToShortDescriptionResMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base.BaseExerciseViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import javax.inject.Inject

class WordExerciseViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    saveStatisticsInteractor: SaveStatisticsInteractor,
    voiceRecorder: VoiceRecorder,
    observeAutoRecordStateInteractor: ObserveAutoRecordStateInteractor,
    permissionManager: PermissionManager,
    adManager: AdManager
) : BaseExerciseViewModel(
    exerciseType,
    incrementExercisePerformedInteractor,
    saveStatisticsInteractor,
    observeTrainingExerciseInteractor,
    observeAutoRecordStateInteractor,
    voiceRecorder,
    permissionManager,
    adManager
) {
    private val exerciseNameToExerciseRandomWordMapper =
        ExerciseNameToExerciseRandomWordMapper(application.resources)

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
        _word.value = exerciseNameToExerciseRandomWordMapper.map(exerciseType.getExerciseName())
    }
}