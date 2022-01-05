package yaroslavgorbach.koropapps.vocabulary.data.achievements.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.AchievementsDataStore
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName

class RepoAchievementsImp(private val localDataSource: AchievementsDataStore) : RepoAchievements {

    override fun achieve(name: AchievementName) {
        localDataSource.achieve(name)
    }

    override fun observe(): Flow<List<Achievement>> {
        return localDataSource.observe()
    }

    override fun clearAchievements() {
        return localDataSource.clearProgress()
    }
}