package yaroslavgorbach.koropapps.vocabulary.feature.records.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemRecordBinding
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBind

class RecordsListAdapter(private val onRecordClick: (RecordUi) -> Unit) :
    ListAdapter<RecordUi, RecordsListAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<RecordUi>() {
        override fun areItemsTheSame(oldItem: RecordUi, newItem: RecordUi): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RecordUi, newItem: RecordUi): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun submitList(list: List<RecordUi>?) {
        super.submitList(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBind(ItemRecordBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { onRecordClick(getItem(absoluteAdapterPosition)) }
        }

        fun bind(item: RecordUi) {
            with(binding) {
                date.text = item.lastModified
                duration.text = item.duration
                name.text = item.name
                iconPlay.setImageResource(item.playIconRes)
            }
        }
    }
}