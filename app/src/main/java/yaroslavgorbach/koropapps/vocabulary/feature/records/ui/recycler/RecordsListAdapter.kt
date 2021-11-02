package yaroslavgorbach.koropapps.vocabulary.feature.records.ui.recycler

import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemRecordBinding
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding

class RecordsListAdapter(private val onRecordClick: (RecordUi) -> Unit) :
    RecyclerView.Adapter<RecordsListAdapter.ViewHolder>() {

    private var items: List<RecordUi> = emptyList()

    init {
        setHasStableIds(true)
    }

    @MainThread
    fun submitList(list: List<RecordUi>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemRecordBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    fun getItemByPosition(position: Int) = getItem(position)

    override fun getItemCount() = items.size

    override fun getItemId(position: Int): Long {
        return getItem(position)?.file?.length() ?: 0
    }

    inner class ViewHolder(private val binding: ItemRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let(onRecordClick)
            }
        }

        fun bind(item: RecordUi) {
            with(binding) {
                date.text = item.lastModified
                duration.text = item.duration
                name.text = item.name
                iconPlay.setImageResource(item.playIconRes)

                when (item.recordState) {
                    RecordUi.RecordState.Pause -> {
                        binding.seekBar.visibility = View.VISIBLE
                    }
                    RecordUi.RecordState.Playing -> {
                        binding.seekBar.visibility = View.VISIBLE
                        seekBar.progress = item.progress
                    }
                    RecordUi.RecordState.Stop -> {
                        binding.seekBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun getItem(position: Int): RecordUi? {
        return if (position >= 0) {
            items[position]
        } else {
            null
        }
    }
}
