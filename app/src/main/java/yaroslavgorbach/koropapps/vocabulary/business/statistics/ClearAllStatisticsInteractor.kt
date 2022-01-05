package yaroslavgorbach.koropapps.vocabulary.business.statistics

import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class ClearAllStatisticsInteractor(private val repoStatistics: RepoStatistics) {
    operator fun invoke() = repoStatistics.clearAll()
}