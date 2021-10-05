package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class ExercisesListAdapter(private val onExercise: (exercise: ExerciseUi) -> Unit) :
    RecyclerView.Adapter<ExercisesListAdapter.ViewHolder>() {

    private var list: List<ExerciseUi> = emptyList()

    fun setData(data: List<ExerciseUi>) {
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

        fun bind(exercise: ExerciseUi) {
            binding.name.text = binding.getString(exercise.nameRes)
            binding.image.setImageDrawable(binding.getDrawable(exercise.iconRes))

            when (exercise.category) {
                ExerciseCategory.COMMUNICATION -> binding.category.setText(R.string.category_communications)
                ExerciseCategory.VOCABULARY -> binding.category.setText(R.string.category_vocabulary)
            }
        }
    }
}