package yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemeExerciseTariningBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class TrainingExercisesListAdapter(private val onExercise: (withExercises: TrainingExerciseUi) -> Unit) :
    RecyclerView.Adapter<TrainingExercisesListAdapter.ViewHolder>() {

    private var list: List<TrainingExerciseUi> = emptyList()

    fun setData(data: List<TrainingExerciseUi>) {
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

        fun bind(withExercises: TrainingExerciseUi) {
            binding.name.text = binding.getString(withExercises.nameRes)
            binding.image.setImageDrawable(binding.getDrawable(withExercises.iconRes))
        }
    }
}