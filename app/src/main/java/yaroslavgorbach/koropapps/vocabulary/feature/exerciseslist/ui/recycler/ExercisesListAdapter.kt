package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemExerciseBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding

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
        return ViewHolder(parent.inflateBinding(ItemExerciseBinding::inflate))
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
            if (exercise.isNew) binding.markNew.visibility =
                View.VISIBLE else binding.markNew.visibility = View.GONE
        }
    }
}