package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.ui.recycler

import android.graphics.Color
import android.graphics.Paint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemStageBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithstage.model.StageUi
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding

class StagesAdapter : ListAdapter<StageUi, StagesAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<StageUi>() {
        override fun areItemsTheSame(oldItem: StageUi, newItem: StageUi): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StageUi, newItem: StageUi): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemStageBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<StageUi>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemStageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StageUi) {
            binding.text.text = item.text
            binding.number.text = item.number.toString()

            with(item) {
                if (isActive) {
                    binding.text.setTextColor(Color.GREEN)
                }else{
                    binding.text.setTextColor(Color.GRAY)
                }

                if (isFinished) {
                    binding.text.paintFlags = binding.text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }else{
                    binding.text.paintFlags = 0
                }
            }
        }
    }
}