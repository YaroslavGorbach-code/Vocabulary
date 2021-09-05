package yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class StatisticsDayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var summaryTrainingTime: Long,
    val date: Date
)
