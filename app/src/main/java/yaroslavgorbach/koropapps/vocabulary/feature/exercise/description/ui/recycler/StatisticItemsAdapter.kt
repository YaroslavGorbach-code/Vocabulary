package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui.recycler

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.TypedValue
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemStatisticDayBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.StatisticItemUi
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding
import yaroslavgorbach.koropapps.vocabulary.utils.selectableItemBackgroundBorderless


class StatisticItemsAdapter(
    private val onItemChosen: (StatisticItemUi) -> Unit
) : RecyclerView.Adapter<StatisticItemsAdapter.ViewHolder>() {

    private var list: List<StatisticItemUi> = emptyList()

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<StatisticItemUi>) {
        list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemStatisticDayBinding::inflate))
    }

    override fun getItemId(position: Int) = list[position].id

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(
        private val binding: ItemStatisticDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (absoluteAdapterPosition >= 0)
                    onItemChosen(list[absoluteAdapterPosition])
            }
        }

        fun bind(item: StatisticItemUi) {
            binding.dayNumber.text = item.dayOfWeek
            binding.dayString.text = item.dayOfWeekText
            when (item.isChosen) {
                true -> {
                    binding.dayString.setTextColor(Color.WHITE)
                    binding.dayNumber.setTextColor(Color.WHITE)
                    binding.root.background = binding.getDrawable(R.drawable.bg_statistic_selected)
                }
                false -> {
                    binding.dayString.setTextColor(Color.GRAY)
                    binding.dayNumber.setTextColor(Color.BLACK)
                    binding.root.setBackgroundResource(binding.root.context.selectableItemBackgroundBorderless())
                }
            }
        }
    }
}