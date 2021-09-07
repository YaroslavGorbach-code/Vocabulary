package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsLevelEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class GetStatisticsLevelInteractor(private val repoStatistics: RepoStatistics) {

    operator fun invoke(): Single<StatisticsLevelEntity> {
        return repoStatistics.getLevel().subscribeOn(Schedulers.io())
    }
}