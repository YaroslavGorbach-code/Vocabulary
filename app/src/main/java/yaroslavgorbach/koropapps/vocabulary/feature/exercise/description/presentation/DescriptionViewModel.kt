package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import yaroslavgorbach.koropapps.vocabulary.business.description.GetDescriptionIntearactor
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescription
import yaroslavgorbach.koropapps.vocabulary.data.description.repo.RepoDescriptionImp
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName

class DescriptionViewModel : ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val repoDescription: RepoDescription
        get() = RepoDescriptionImp()

    private val getDescriptionIntearactor: GetDescriptionIntearactor
        get() = GetDescriptionIntearactor(repoDescription)

    private val description: MutableLiveData<Description> = MutableLiveData()


    fun getDescription(exerciseName: ExerciseName): LiveData<Description> {
        getDescriptionIntearactor(exerciseName)
            .subscribe(Consumer(description::setValue))
        return description
    }

    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}