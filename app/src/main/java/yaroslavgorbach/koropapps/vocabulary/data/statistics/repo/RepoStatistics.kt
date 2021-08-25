package yaroslavgorbach.koropapps.vocabulary.data.statistics.repo

import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

interface RepoStatistics {
    fun insert(statisticsEntity: StatisticsEntity): Completable
}