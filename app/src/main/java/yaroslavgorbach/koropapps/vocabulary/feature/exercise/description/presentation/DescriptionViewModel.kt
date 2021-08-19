package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.description.GetDescriptionInteractor
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import javax.inject.Inject

class DescriptionViewModel @Inject constructor(
    private val getDescriptionInteractor: GetDescriptionInteractor
) : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val description: MutableLiveData<Description> = MutableLiveData()

    fun getDescription(exerciseName: ExerciseName): LiveData<Description> {
        getDescriptionInteractor(exerciseName)
            .subscribe(description::setValue)
            .let(disposables::add)
        return description
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}