package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: Date?,
)
