package yaroslavgorbach.koropapps.vocabulary.data.achievements.repo

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName

interface RepoAchievements {
    suspend fun achieve(name: AchievementName)

    fun observe(): Flow<List<Achievement>>

}