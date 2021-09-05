package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDayEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsValueEntity

class RepoStatisticsImp(private val localDataSource: StatisticsDao) : RepoStatistics {

    override fun insert(statisticsValueEntity: StatisticsValueEntity): Completable {
        return localDataSource.insert(statisticsValueEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insert(statisticsTimeEntity: StatisticsTimeEntity): Completable {
        return localDataSource.insert(statisticsTimeEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun insert(statisticsDayEntity: StatisticsDayEntity): Completable {
        return localDataSource.insert(statisticsDayEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun update(statisticsDayEntity: StatisticsDayEntity): Completable {
        return localDataSource.update(statisticsDayEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun observeValue(exerciseNameRes: Int): Observable<List<StatisticsValueEntity>> {
        return localDataSource.observeValue(exerciseNameRes)
            .subscribeOn(Schedulers.io())
    }

    override fun observeTime(exerciseNameRes: Int): Observable<List<StatisticsTimeEntity>> {
        return localDataSource.observeTime(exerciseNameRes)
            .subscribeOn(Schedulers.io())
    }

    override fun observeDays(): Observable<List<StatisticsDayEntity>> {
        return localDataSource.observeDays()
            .observeOn(Schedulers.io())
    }

}