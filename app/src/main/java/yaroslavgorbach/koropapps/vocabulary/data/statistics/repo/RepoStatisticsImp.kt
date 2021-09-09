package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
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

    override fun insert(statisticsLevelEntity: StatisticsLevelEntity): Completable {
        return localDataSource.insert(statisticsLevelEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun update(statisticsLevelEntity: StatisticsLevelEntity): Completable {
        return localDataSource.update(statisticsLevelEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun update(statisticsDailyTrainingTimeEntity: StatisticsDailyTrainingTimeEntity): Completable {
        return localDataSource.update(statisticsDailyTrainingTimeEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsExerciseValueEntity>> {
        return localDataSource.observeValue(exerciseNameRes)
            .subscribeOn(Schedulers.io())
    }

    override fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsExerciseTimeEntity>> {
        return localDataSource.observeTime(exerciseNameRes)
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

    override fun getLevel(): Single<StatisticsLevelEntity> {
        return localDataSource.getLevel()
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext {
                val entity = StatisticsLevelEntity(
                    summaryTrainingTimeMc = 0,
                    exercisesCompleted = 0,
                    dailyTrainingsCompleted = 0
                )
                insert(entity).subscribe()
                Single.just(entity)
            }
    }
}