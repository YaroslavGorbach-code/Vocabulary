package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

class RepoStatisticsImp(private val localDataSource: StatisticsDao) : RepoStatistics {

    override fun insert(statisticsEntity: StatisticsEntity): Completable {
        return localDataSource.insert(statisticsEntity)
            .subscribeOn(Schedulers.io())
    }

    override fun observe(exerciseNameRes: Int): Observable<List<StatisticsEntity>> {
        return localDataSource.observe(exerciseNameRes)
            .subscribeOn(Schedulers.io())
    }

}