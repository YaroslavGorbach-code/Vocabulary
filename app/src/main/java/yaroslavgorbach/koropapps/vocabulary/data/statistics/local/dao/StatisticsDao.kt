package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao

import androidx.room.Dao

@Dao
interface StatisticsDao {
    fun observe()
}