package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.util.getDrawable
import yaroslavgorbach.koropapps.vocabulary.util.getString

class ExercisesListAdapter(private val onExercise: (exercise: Exercise) -> Unit) :
    RecyclerView.Adapter<ExercisesListAdapter.ViewHolder>() {

    private var list: List<Exercise> = emptyList()

    fun setData(data: List<Exercise>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                onExercise(list[adapterPosition])
            }
        }

        fun bind(exercise: Exercise) {
            binding.name.text = binding.getString(exercise.name.id)
            binding.image.setImageDrawable(binding.getDrawable(exercise.icon))
        }
    }
}