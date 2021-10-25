package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class GetStatisticsCommonInfoInteractor(private val repoStatistics: RepoStatistics) {

    operator fun invoke(): Single<StatisticsCommonInfoEntity> {
        return repoStatistics.getCommonInfo()
            .subscribeOn(Schedulers.io())
    }
}