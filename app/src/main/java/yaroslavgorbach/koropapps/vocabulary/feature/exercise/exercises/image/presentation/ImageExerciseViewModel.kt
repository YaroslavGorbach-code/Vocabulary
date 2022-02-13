package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.image.presentation

import android.app.Application
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
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import javax.inject.Inject

class ImageExerciseViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    observeKeepScreenSettingInteractor: ObserveKeepScreenSettingInteractor,
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
    observeKeepScreenSettingInteractor,
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

    private val _image = MutableLiveData<String>()

    val image: LiveData<String>
        get() = _image

    init {
        generateImage()
    }

    override fun onNextClick() {
        super.onNextClick()
        generateImage()
    }

    private fun generateImage() {
        _image.value = exerciseNameToExerciseRandomWordMapper.map(exerciseType.getExerciseName())
    }
}