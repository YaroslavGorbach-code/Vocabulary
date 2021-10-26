package yaroslavgorbach.koropapps.vocabulary.business.statistics

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.repo.RepoStatistics

class GetStatisticsCommonInfoInteractor(
    private val repoStatistics: RepoStatistics,
    private val achieveAchievementInteractor: AchieveAchievementInteractor
) {

    operator fun invoke(): Single<StatisticsCommonInfoEntity> {
        return repoStatistics.getCommonInfo()
            .doOnSuccess { commonInfo ->
                if (commonInfo.dailyTrainingsCompleted > 0) {
                    achieveAchievementInteractor(AchievementName.FIRST_DAILY_TRAINING_COMPLETE)
                }
            }
            .subscribeOn(Schedulers.io())
    }
}