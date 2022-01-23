package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemDailyTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding

class TrainingsListAdapter() : RecyclerView.Adapter<TrainingsListAdapter.ViewHolder>() {

    private var list: List<TrainingUi> = emptyList()

    init {
        setHasStableIds(true)
    }

    fun setData(data: List<TrainingUi>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemDailyTrainingBinding::inflate))
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: ItemDailyTrainingBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(exercise: TrainingUi) {
            binding.textProgress.setProgress(exercise.progress)
            binding.textProgress.setText(exercise.dayOfWeek)
            binding.dayOfWeak.text = exercise.dayOfWeekText
        }
    }
}