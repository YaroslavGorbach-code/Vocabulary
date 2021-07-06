package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.data.vocabulary.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemExerciseBinding

class ExercisesListAdapter : RecyclerView.Adapter<ExercisesListAdapter.Vh>() {
    private var list: List<Exercise> = emptyList()

    fun setData(data: List<Exercise>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class Vh(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(exercise: Exercise) {
            binding.exerciseName.text = exercise.name
        }

    }

}