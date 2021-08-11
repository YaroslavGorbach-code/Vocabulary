package yaroslavgorbach.koropapps.vocabulary.data.training.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.ExerciseTraining
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.Training
import java.util.*

class RepoTrainingImp : RepoTraining {

    private val testTrainings: MutableList<Training> = createTestTrainings().toMutableList()

    override fun observe(): Observable<List<Training>> {
        return Observable.just(testTrainings)
            .map { list ->
                while (list.size < 5) {
                    list.add(Training(0, null, emptyList()))
                }
                list.reversed()
            }
    }

    override fun insert(training: Training): Completable {
        insert(listOf(training))
        // TODO: 8/10/2021 fix it then with room
        return Completable.create { it.onComplete() }
    }

    override fun insert(trainings: List<Training>): Completable {
        testTrainings.addAll(trainings)
        // TODO: 8/10/2021 fix it then with room
        return Completable.create { it.onComplete() }
    }

    private fun createTestTrainings(): List<Training> {
        return listOf(
            Training(1, Date(), createTestExercises()),
        )
    }

    private fun createTestExercises(): List<ExerciseTraining> {
        return listOf(
            ExerciseTraining(
                1,
                ExerciseName.TEN,
                100,
                10,
            ),
            ExerciseTraining(
                2,
                ExerciseName.ANTONYMS_AND_SYNONYMS,
                100,
                10,
            )
        )
    }
}