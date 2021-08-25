package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.dao

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.rxjava3.core.Completable
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsEntity

@Dao
interface StatisticsDao {

    @Insert
    fun insert(statisticsEntity: StatisticsEntity): Completable
}