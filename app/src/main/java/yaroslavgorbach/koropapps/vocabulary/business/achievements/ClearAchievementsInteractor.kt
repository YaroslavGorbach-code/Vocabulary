package yaroslavgorbach.koropapps.vocabulary.business.achievements

import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievements

class ClearAchievementsInteractor(private val repoAchievements: RepoAchievements) {
    operator fun invoke() = repoAchievements.clearAchievements()
}