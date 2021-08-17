package yaroslavgorbach.koropapps.vocabulary.business.training.factory

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import java.util.*

class TrainingFactory {
    enum class TrainingType {
        EMPTY, TODAY
    }

    fun create(trainingType: TrainingType): TrainingEntity {
        return when (trainingType) {
            TrainingType.EMPTY -> TrainingEntity()
            TrainingType.TODAY -> TrainingEntity(date = Date())
        }
    }
}