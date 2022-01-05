package yaroslavgorbach.koropapps.vocabulary.business.training.factory

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingEntity
import java.util.*

class TrainingFactory {
    enum class TrainingType {
        EMPTY, TODAY
    }

    fun create(
        trainingType: TrainingType = TrainingType.EMPTY,
        previousTraining: TrainingEntity? = null
    ): TrainingEntity {
        return when (trainingType) {
            TrainingType.EMPTY -> TrainingEntity()
            TrainingType.TODAY -> {
                var daysWithoutInterrupting = 0
                var numberOfTrainings = previousTraining!!.numberOfTraining
                numberOfTrainings++

                if (previousTraining.isFinished) {
                    daysWithoutInterrupting = previousTraining.daysWithoutInterruption
                }

                TrainingEntity(
                    date = Date(),
                    daysWithoutInterruption = daysWithoutInterrupting,
                    numberOfTraining = numberOfTrainings
                )
            }
        }
    }
}