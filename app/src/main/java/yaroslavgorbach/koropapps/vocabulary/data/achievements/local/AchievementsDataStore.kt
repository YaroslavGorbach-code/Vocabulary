package yaroslavgorbach.koropapps.vocabulary.data.achievements.local

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName

interface AchievementsDataStore {

    fun observe(): Flow<List<Achievement>>

    fun achieve(name: AchievementName)

    fun clearProgress()
}