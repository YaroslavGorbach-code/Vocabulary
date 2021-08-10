package yaroslavgorbach.koropapps.vocabulary.data.training.local.model

import java.util.*

data class Training(val id: Long, val date: Date, val exercises: List<ExerciseTraining>) {
    val progress: Int
        get() {
            var p = 0
            exercises.forEach {
                p += it.progress
            }
            return (p.toFloat() / exercises.size.toFloat()).toInt()
        }
}
