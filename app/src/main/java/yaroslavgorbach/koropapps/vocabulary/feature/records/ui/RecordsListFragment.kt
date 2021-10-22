package yaroslavgorbach.koropapps.vocabulary.feature.records.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentRecordsListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.feature.records.presentation.RecordsViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import javax.inject.Inject

class RecordsListFragment : Fragment(R.layout.fragment_records_list) {

    companion object {
        fun newInstance() = RecordsListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RecordsViewModel> { viewModelFactory }

    private var _recordsView: RecordsView? = null

    private val recordsView: RecordsView
        get() = requireNotNull(_recordsView)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initDagger() {
        appComponent()
            .recordsComponent()
            .create()
            .inject(this)
    }

    private fun initView() {
        _recordsView = RecordsView(
            FragmentRecordsListBinding.bind(requireView()),
            object : RecordsView.Callback {
                override fun onRecord(record: RecordUi) {
                    viewModel.startPauseRecord(record)
                }

                override fun onRemoveRecord(record: RecordUi) {
                    viewModel.removeRecordUi(record)
                }

                override fun onRestoreRemovedRecord() {
                    viewModel.restorePreviouslyRemovedRecordUi()
                }

                override fun onRemovedRecordSnackDismiss() {
                    viewModel.removeRecordPermanently()
                }
            })
    }

    private fun initObservers() {
        viewModel.records.observe(viewLifecycleOwner, recordsView::setRecords)

        viewModel.playerFinishedEvent.observe(viewLifecycleOwner) {
            viewModel.setStoppedUiStateToAllRecords()
        }

        viewModel.currentRecordPlayingProgress.observe(
            viewLifecycleOwner,
            viewModel::setProgressToAllRecords
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _recordsView = null
    }
}