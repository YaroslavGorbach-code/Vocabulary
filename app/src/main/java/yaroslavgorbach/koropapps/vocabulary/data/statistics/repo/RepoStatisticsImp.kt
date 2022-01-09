package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import java.util.*

class RepoStatisticsImp(private val localDataSource: StatisticsDao) : RepoStatistics {

    override fun insert(statisticsExerciseValueEntity: StatisticsExerciseValueEntity): Completable {
        return localDataSource.insert(statisticsExerciseValueEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insert(statisticsExerciseTimeEntity: StatisticsExerciseTimeEntity): Completable {
        return localDataSource.insert(statisticsExerciseTimeEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insert(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable {
        return localDataSource.insert(statisticsDailyTrainingTimeEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insert(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable {
        return localDataSource.insert(statisticsCommonInfoEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun update(statisticsCommonInfoEntity: StatisticsCommonInfoEntity): Completable {
        return localDataSource.update(statisticsCommonInfoEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun update(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable {
        return localDataSource.update(statisticsDailyTrainingTimeEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun observeValue(exerciseName: ExerciseName): Observable<List<StatisticsExerciseValueEntity>> {
        return localDataSource.observeValue(exerciseName)
            .subscribeOn(Schedulers.io())
    }

    override fun observeTime(exerciseName: ExerciseName): Observable<List<StatisticsExerciseTimeEntity>> {
        return localDataSource.observeTime(exerciseName)
            .subscribeOn(Schedulers.io())
    }

    override fun observeDays(): Observable<List<StatisticsDailyTrainingTimeEntity>> {
        return localDataSource.observeDays()
            .subscribeOn(Schedulers.io())
            .map {
                if (it.isEmpty()) {
                    val entity = StatisticsDailyTrainingTimeEntity(0, 0, Date())
                    val entities = listOf(entity)
                    insert(entity).subscribe()
                    entities
                } else {
                    it
                }
            }
    }

    override fun getCommonInfo(): Single<StatisticsCommonInfoEntity> {
        return localDataSource.getLevel()
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext {
                val entity = StatisticsCommonInfoEntity(
                    summaryTrainingTimeMc = 0,
                    exercisesCompleted = 0,
                    dailyTrainingsCompleted = 0,
                    summaryTrainingTimeCommunicationMc = 0,
                    summaryTrainingTimeDictionMc = 0,
                    summaryTrainingTimeVocabularyMc = 0,
                    summaryTrainingTimeSenseOfHumorMc = 0
                )
                insert(entity).subscribe()
                Single.just(entity)
            }
    }

    override fun getAllExercisesValue(): Single<List<StatisticsExerciseValueEntity>> {
        return localDataSource.getAllExercisesValue()
            .subscribeOn(Schedulers.io())
    }

    override fun clearAll(): Completable {
        return localDataSource.cleaDailyTrainingTime()
            .andThen(localDataSource.cleaCommonInfo())
            .andThen(localDataSource.clearExercisesValue())
            .andThen(localDataSource.clearExercisesTime())
            .subscribeOn(Schedulers.io())
    }
}