package yaroslavgorbach.koropapps.vocabulary.feature.records.ui

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentRecordsListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.SwipeDeleteDecor
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.feature.records.ui.recycler.RecordsListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable

class RecordsView(private val binding: FragmentRecordsListBinding, private val callback: Callback) {

    interface Callback {
        fun onRecord(record: RecordUi)
        fun onRemoveRecord(record: RecordUi)
    }

    private var recordsAdapter: RecordsListAdapter? = null

    private var swipeDecor: SwipeDeleteDecor? = null

    init {
        initViews()
    }

    private fun initViews() {
        swipeDecor = SwipeDeleteDecor(binding.getDrawable(R.drawable.bg_delete_record_hint)!!) { viewHolder ->
            callback.onRemoveRecord(recordsAdapter?.getItemByPosition(viewHolder.absoluteAdapterPosition)!!)
        }.also { it.attachToRecyclerView(binding.recordsList) }

        recordsAdapter = RecordsListAdapter(callback::onRecord)

        binding.recordsList.adapter = recordsAdapter

        binding.recordsList.addItemDecoration(
            LineDecorator(
                binding.root.context,
                R.drawable.line_devider
            )
        )

        binding.recordsList.addItemDecoration(swipeDecor!!)
    }

    fun setRecords(records: List<RecordUi>) {
        recordsAdapter?.submitList(records)
    }
}