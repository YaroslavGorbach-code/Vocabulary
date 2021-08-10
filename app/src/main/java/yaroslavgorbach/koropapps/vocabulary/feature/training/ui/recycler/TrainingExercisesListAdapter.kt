package yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemeExerciseTariningBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.ExerciseTrainingUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class TrainingExercisesListAdapter(private val onExercise: (exercise: ExerciseTrainingUi) -> Unit) :
    RecyclerView.Adapter<TrainingExercisesListAdapter.ViewHolder>() {

    private var list: List<ExerciseTrainingUi> = emptyList()

    fun setData(data: List<ExerciseTrainingUi>) {
        list = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemeExerciseTariningBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemeExerciseTariningBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                onExercise(list[adapterPosition])
            }
        }

        fun bind(exercise: ExerciseTrainingUi) {
            binding.name.text = binding.getString(exercise.nameRe)
            binding.image.setImageDrawable(binding.getDrawable(exercise.iconRes))
        }
    }
}