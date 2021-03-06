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
                val numberOfTrainings = previousTraining?.numberOfTraining?.inc() ?: 0

                val daysWithoutInterrupting = if (previousTraining?.isFinished == true) {
                    previousTraining.daysWithoutInterruption.inc()
                } else {
                    0
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