package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.training.GetTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.UpdateTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory
import javax.inject.Inject

class TautogramsViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val updateTrainingExerciseInteractor: UpdateTrainingExerciseInteractor,
    private val getTrainingExerciseInteractor: GetTrainingExerciseInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val words: List<String>
        get() = application.applicationContext.resources.getStringArray(
            ExerciseWordCategory.LETTERS.resId
        ).toList()

    val descriptionText: String
        get() = application.getString(R.string.desc_short_tautograms)

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    private val _exercise: MutableLiveData<TrainingExerciseEntity> = MutableLiveData()
        get() {
            if (exerciseType is ExerciseType.Training) {
                observeTrainingExerciseInteractor(exerciseType.exerciseId)
                    .subscribe(field::postValue)
                    .let(disposables::add)
            }
            return field
        }

    val exercise: LiveData<TrainingExerciseEntity>
        get() = _exercise

    init {
        generateWord()
    }

    fun generateWord() {
        _word.value = words.random()
    }

    fun incrementExercisePerformed() {
        if (exerciseType is ExerciseType.Training) {
            getTrainingExerciseInteractor(exerciseType.exerciseId)
                .doOnSuccess { it.performed++ }
                .flatMapCompletable(updateTrainingExerciseInteractor::invoke)
                .subscribe()
                .let(disposables::add)
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}