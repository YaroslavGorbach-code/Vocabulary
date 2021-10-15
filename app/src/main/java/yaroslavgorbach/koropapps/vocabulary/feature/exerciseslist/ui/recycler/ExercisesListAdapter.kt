package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class ExercisesListAdapter(private val onExercise: (exercise: ExerciseUi) -> Unit) :
    RecyclerView.Adapter<ExercisesListAdapter.ViewHolder>() {

    private var list: List<ExerciseUi> = emptyList()

    init {
        setHasStableIds(true)
    }

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

    override fun getItemId(position: Int): Long {
        return list[position].name.id.toLong()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.item.setOnClickListener {
                if (list.isEmpty().not()) {
                    onExercise(list[adapterPosition])
                }
            }
        }

        fun bind(exercise: ExerciseUi) {
            binding.name.text = binding.getString(exercise.nameRes)
            binding.image.setImageDrawable(binding.getDrawable(exercise.iconRes))
            binding.category.setText(exercise.category.stringRes)
        }
    }
}