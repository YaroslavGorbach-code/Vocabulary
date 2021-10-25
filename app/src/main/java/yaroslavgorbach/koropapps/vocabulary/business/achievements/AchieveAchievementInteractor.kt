package yaroslavgorbach.koropapps.vocabulary.business.achievements

import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievements

class AchieveAchievementInteractor(private val repoAchievements: RepoAchievements) {
    suspend operator fun invoke(name: AchievementName) {
        repoAchievements.achieve(name)
    }
}