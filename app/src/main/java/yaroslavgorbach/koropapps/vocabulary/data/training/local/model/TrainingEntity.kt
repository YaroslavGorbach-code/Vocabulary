package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val date: Date? = null,
    var isFinished: Boolean = false,
    var daysWithoutInterruption: Int = 0
)

