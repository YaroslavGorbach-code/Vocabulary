package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonimssininims.presentation

import android.app.Application
import androidx.lifecycle.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.business.training.GetTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.UpdateTrainingExerciseInteractor
import yaroslavgorbach.koropapps.vocabulary.data.training.local.TrainingDatabase
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingExerciseEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTrainingImp
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.model.ExerciseWordCategory
import kotlin.random.Random

class ExerciseAntonymsSynonymsViewModel(
    private val exerciseType: ExerciseType,
    application: Application
) : AndroidViewModel(application) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val trainingDatabase: TrainingDatabase
        get() = TrainingDatabase.getInstance(getApplication())

    private val trainingRepo: RepoTraining
        get() = RepoTrainingImp(trainingDatabase.trainingDao)

    private val updateTrainingExerciseInteractor: UpdateTrainingExerciseInteractor
        get() = UpdateTrainingExerciseInteractor(trainingRepo)

    private val getTrainingExerciseInteractor: GetTrainingExerciseInteractor
        get() = GetTrainingExerciseInteractor(trainingRepo)

    private val observeTrainingExerciseInteractor: ObserveTrainingExerciseInteractor
        get() = ObserveTrainingExerciseInteractor(trainingRepo)

    private val words: List<String>
        get() = getApplication<Application>().applicationContext.resources.getStringArray(
            ExerciseWordCategory.FILLINGS.resId
        ).toList()

    private val _word = MutableLiveData<String>()

    val word: LiveData<String>
        get() = _word

    private val _descriptionText = MutableLiveData<String>()

    val descriptionText: LiveData<String>
        get() = _descriptionText

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
        refreshData()
    }

    fun refreshData() {
        when (Random.nextInt(1, 2)) {
            1 -> {
                _descriptionText.value = getApplication<Application>().applicationContext.getString(
                    R.string.desc_short_antonyms,
                )
            }
            2 -> {
                _descriptionText.value = getApplication<Application>().applicationContext.getString(
                    R.string.desc_short_synonyms,
                )
            }
        }
        generateWord()
    }

    private fun generateWord() {
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

class ExerciseAntonymsSynonymsViewModelFactory(
    private val exerciseType: ExerciseType,
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseAntonymsSynonymsViewModel::class.java)) {
            return ExerciseAntonymsSynonymsViewModel(exerciseType, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}