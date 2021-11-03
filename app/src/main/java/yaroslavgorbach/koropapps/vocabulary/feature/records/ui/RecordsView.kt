package yaroslavgorbach.koropapps.vocabulary.feature.records.ui

import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentRecordsListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.SwipeDeleteDecor
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.feature.records.ui.recycler.RecordsListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class RecordsView(private val binding: FragmentRecordsListBinding, private val callback: Callback) {

    interface Callback {
        fun onRecord(record: RecordUi)
        fun onSeekRecordTo(progress: Int)
        fun onRemoveRecord(record: RecordUi)
        fun onRestoreRemovedRecord()
        fun onRemovedRecordSnackDismiss()
    }

    private var recordsAdapter: RecordsListAdapter? = null

    private var swipeDecor: SwipeDeleteDecor? = null

    init {
        initViews()
    }

    private fun showRestoreRemovedRecordSnack(onRestore: () -> Unit) {
        Snackbar.make(binding.root, R.string.record_deleted, Snackbar.LENGTH_LONG)
            .setAction(binding.getString(R.string.cancel)) { onRestore() }
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    callback.onRemovedRecordSnackDismiss()
                }
            }).show()
    }

    private fun initViews() {
        recordsAdapter = RecordsListAdapter(callback::onRecord, callback::onSeekRecordTo)

        swipeDecor =
            SwipeDeleteDecor(
                binding.getDrawable(R.drawable.bg_delete_record_hint)!!,
                onHalfSwipe = { viewHolder ->
                    recordsAdapter?.getItemByPosition(viewHolder.layoutPosition)?.let { record ->
                        if (record.recordState == RecordUi.RecordState.Playing) {
                            callback.onRecord(record)
                        }
                    }
                },
                onSwipe = { viewHolder ->
                    recordsAdapter?.getItemByPosition(viewHolder.layoutPosition)?.let { record ->
                        callback.onRemoveRecord(record)

                        showRestoreRemovedRecordSnack {
                            callback.onRestoreRemovedRecord()
                        }
                    }

                }).also { it.attachToRecyclerView(binding.recordsList) }

        binding.recordsList.apply {
            adapter = recordsAdapter

            addItemDecoration(
                LineDecorator(
                    binding.root.context,
                    R.drawable.line_devider
                )
            )

            addItemDecoration(swipeDecor!!)
        }
    }

    fun setRecords(records: List<RecordUi>) {
        recordsAdapter?.submitList(records)
        showHideIconNoRecords(records)
    }

    private fun showHideIconNoRecords(records: List<RecordUi>) {
        if (records.isEmpty()) {
            binding.icNoRecords.visibility = View.VISIBLE
        } else {
            binding.icNoRecords.visibility = View.GONE
        }
    }

}
