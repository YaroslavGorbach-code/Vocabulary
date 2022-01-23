package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model

import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.utils.formatDayOfWeek
import yaroslavgorbach.koropapps.vocabulary.utils.formatDayOfWeekText


data class TrainingUi(private val trainingWithExerciseEntities: TrainingWithExercisesEntity) {
    val id = trainingWithExerciseEntities.training.id

    val progress = trainingWithExerciseEntities.exercisesProgress

    val dayOfWeek = trainingWithExerciseEntities.training.date?.formatDayOfWeek().toString()

    val dayOfWeekText = trainingWithExerciseEntities.training.date?.formatDayOfWeekText().toString()
}
