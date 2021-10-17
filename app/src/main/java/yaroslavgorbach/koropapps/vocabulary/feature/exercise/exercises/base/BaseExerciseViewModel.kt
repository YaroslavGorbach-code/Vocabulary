package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.base

import android.Manifest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.statistics.SaveStatisticsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.IncrementExercisePerformedInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import yaroslavgorbach.koropapps.vocabulary.utils.send
import java.util.*

open class BaseExerciseViewModel(
    private val exerciseType: ExerciseType,
    private val incrementExercisePerformedInteractor: IncrementExercisePerformedInteractor,
    private val saveStatisticsInteractor: SaveStatisticsInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor,
    private val voiceRecorder: VoiceRecorder,
    private val permissionManager: PermissionManager
) : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()

    var numberOnNextCLicked: Int = 0
        private set

    private val timeIntervals: MutableList<Long> = arrayListOf()

    private var previousTime: Long = Date().time

    private var isStatisticsSaved = false

    private val summaryTimeSpendOnExercise: Long
        get() = timeIntervals.sum()

    val averageTimeOnWord: Float
        get() {
            return try {
                (summaryTimeSpendOnExercise / timeIntervals.size) / 1000f
            } catch (ex: Exception) {
                0f
            }
        }

    private val _exercise: MutableLiveData<TrainingExerciseUi> = MutableLiveData()
        get() {
            if (exerciseType is ExerciseType.Training) {
                observeTrainingExerciseInteractor(exerciseType.exerciseId)
                    .map(::TrainingExerciseUi)
                    .subscribe(field::postValue)
                    .let(disposables::add)
            }
            return field
        }

    val exercise: LiveData<TrainingExerciseUi>
        get() = _exercise

    val isVoiceRecorderRecording: LiveData<Boolean>
        get() = voiceRecorder.isRecording

    val isRecordSavedEvent: LiveEvent<Unit>
        get() = voiceRecorder.isRecordSaved

    private val _showPermissionDeniedDialogEvent: MutableLiveEvent<Unit> = MutableLiveEvent()

    val showPermissionDeniedDialogEvent: LiveEvent<Unit>
        get() = _showPermissionDeniedDialogEvent

    private fun incrementNumberOnNextClicked() {
        numberOnNextCLicked++
    }

    private fun measureClickInterval() {
        val currentTime = Date().time
        val interval = currentTime - previousTime
        previousTime = currentTime
        timeIntervals.add(interval)
    }

    protected fun incrementExercisePerformed() {
        if (exerciseType is ExerciseType.Training) {
            incrementExercisePerformedInteractor(exerciseType.exerciseId)
                .subscribe()
                .let(disposables::add)
        }
    }

    private fun saveStatistics(doOnComplete: () -> Unit = {}) {
        if (isStatisticsSaved.not()) {
            saveStatisticsInteractor(
                exerciseType = exerciseType,
                passedLettersOrWordsCount = numberOnNextCLicked,
                averageTimeOnWord = averageTimeOnWord,
                summaryTimeSpendOnExercise = summaryTimeSpendOnExercise
            )
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(doOnComplete)
                .subscribe()
                .let(disposables::add)

            isStatisticsSaved = true
        }
    }

    private fun checkOrRequestRecordAudioPermission(onGranted: () -> Unit) {
        permissionManager.checkPermission(
            permission = Manifest.permission.RECORD_AUDIO
        ) { userHasPermission ->
            if (userHasPermission) {
                onGranted()
            } else {
                permissionManager.requestPermission(Manifest.permission.RECORD_AUDIO) { isGranted ->
                    if (isGranted) {
                        onGranted()
                    } else {
                        _showPermissionDeniedDialogEvent.send(Unit)
                    }
                }
            }
        }
    }

    private fun disposeDisposables() {
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }

    open fun onNextClick() {
        incrementNumberOnNextClicked()
        measureClickInterval()
        incrementExercisePerformed()
    }

    fun onStartStopRecording() {
        checkOrRequestRecordAudioPermission {
            isVoiceRecorderRecording.value?.let { isRecording ->
                if (isRecording) {
                    voiceRecorder.stop()
                } else {
                    voiceRecorder.start(exerciseType.getExerciseName().name.lowercase())
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        measureClickInterval()

        saveStatistics {
            disposeDisposables()
        }
    }
}