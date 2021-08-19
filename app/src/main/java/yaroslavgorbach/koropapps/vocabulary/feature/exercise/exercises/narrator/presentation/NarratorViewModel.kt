package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.training.GetTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.UpdateTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import javax.inject.Inject
import kotlin.random.Random

class NarratorViewModel @Inject constructor(
    private val exerciseType: ExerciseType,
    private val application: Application,
    private val updateTrainingExerciseInteractor: UpdateTrainingExerciseInteractor,
    private val getTrainingExerciseInteractor: GetTrainingExerciseInteractor,
    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _numberOfWords = MutableLiveData<String>()

    val numberOfWords: LiveData<String>
        get() = _numberOfWords

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
        generateNumberOfWords()
    }

    fun getDescriptionText(exName: ExerciseName): String {
        return when (exName) {
            ExerciseName.NARRATOR_NOUN -> {
                application.applicationContext.getString(R.string.desc_short_narrator_noun)
            }
            ExerciseName.NARRATOR_ADJECTIVES -> {
                application.applicationContext.getString(R.string.desc_short_narrator_adjectives)
            }
            ExerciseName.NARRATOR_VERBS -> {
                application.applicationContext.getString(R.string.desc_short_narrator_verbs)
            }
            else -> ""
        }
    }

    fun generateNumberOfWords() {
        _numberOfWords.value = Random.nextInt(3, 15).toString()
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