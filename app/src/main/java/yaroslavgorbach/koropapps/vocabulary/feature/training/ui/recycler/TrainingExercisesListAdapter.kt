package yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler

import android.annotation.SuppressLint
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemExerciseTariningBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class TrainingExercisesListAdapter(private val onExercise: (exercise: TrainingExerciseUi) -> Unit) :
    RecyclerView.Adapter<TrainingExercisesListAdapter.ViewHolder>() {

    private var list: List<TrainingExerciseUi> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<TrainingExerciseUi>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExerciseTariningBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemExerciseTariningBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                onExercise(list[adapterPosition])
            }
        }

        fun bind(exercise: TrainingExerciseUi) {
            binding.name.text = binding.getString(exercise.name.id)
            binding.category.text = binding.getString(exercise.category.stringRes)
            binding.image.setImageDrawable(binding.getDrawable(exercise.iconRes))
            binding.progress.progress = exercise.progress.toFloat()

            if (exercise.isFinished) {
                binding.finishMark.setBackgroundResource(R.drawable.ic_check_animation)
                (binding.finishMark.background as AnimatedVectorDrawable).start()
            }else{
                binding.finishMark.setBackgroundResource(R.drawable.ic_circle_outline)
            }
        }
    }
}