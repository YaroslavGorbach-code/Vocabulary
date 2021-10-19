package yaroslavgorbach.koropapps.vocabulary.feature.records.ui

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentRecordsListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.feature.records.ui.recycler.RecordsListAdapter

class RecordsView(private val binding: FragmentRecordsListBinding, private val callback: Callback) {

    interface Callback {
        fun onRecord(record: RecordUi)
    }

    private var recordsAdapter: RecordsListAdapter? = null

    init {
        initViews()
    }

    private fun initViews() {
        recordsAdapter = RecordsListAdapter(callback::onRecord)

        binding.recordsList.adapter = recordsAdapter

        binding.recordsList.addItemDecoration(
            LineDecorator(
                binding.root.context,
                R.drawable.line_devider
            )
        )
    }

    fun setRecords(records: List<RecordUi>) {
        recordsAdapter?.submitList(records)
    }
}