package yaroslavgorbach.koropapps.vocabulary.business.achievements

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.repo.RepoAchievements

class ObserveAchievementsInteractor(private val repoAchievements: RepoAchievements) {
    operator fun invoke(): Flow<List<Achievement>> {
        return repoAchievements.observe()
    }
}