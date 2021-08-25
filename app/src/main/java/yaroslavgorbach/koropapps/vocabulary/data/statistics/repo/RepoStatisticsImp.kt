package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao.StatisticsDao
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

class RepoStatisticsImp(private val localDataSours: StatisticsDao) : RepoStatistics {

    override fun insert(statisticsEntity: StatisticsEntity): Completable {
        return localDataSours.insert(statisticsEntity)
    }

}