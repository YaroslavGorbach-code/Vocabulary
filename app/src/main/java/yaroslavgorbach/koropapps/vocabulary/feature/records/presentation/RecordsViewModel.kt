package yaroslavgorbach.koropapps.vocabulary.feature.records.presentation

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.business.records.DeleteRecordFileInteractor
import yaroslavgorbach.koropapps.vocabulary.business.records.GetRecordFilesInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.feature.player.RecordPlayer
import java.io.File
import javax.inject.Inject

class RecordsViewModel @Inject constructor(
    private val getRecordFilesInteractor: GetRecordFilesInteractor,
    private val deleteRecordFileInteractor: DeleteRecordFileInteractor,
    private val recordsPlayer: RecordPlayer
) : ViewModel() {

    private val _records: MutableLiveData<List<RecordUi>> = MutableLiveData()

    val records: LiveData<List<RecordUi>>
        get() = _records

    val playerFinishedEvent: LiveEvent<Unit>
        get() = recordsPlayer.finishEvent

    private var previouslyRemovedRecord: RecordUi? = null

    init {
        refreshRecords()
    }

    private fun startPauseRecordPlayer(record: RecordUi) {
        when (record.recordState) {
            is RecordUi.RecordState.Pause -> recordsPlayer.resume()
            is RecordUi.RecordState.Playing -> recordsPlayer.pause()
            is RecordUi.RecordState.Stop -> {
                recordsPlayer.stop()
                recordsPlayer.play(record.file)
            }
        }
    }

    private fun changeStartPauseUiState(record: RecordUi) {
        fun MutableList<RecordUi>.stopPlayingExceptCurrent(elementIndex: Int) {
            forEachIndexed { index, recordUi ->
                if (index != elementIndex) {
                    recordUi.recordState = RecordUi.RecordState.Stop
                }
            }
        }

        _records.value = _records.value?.toMutableList()?.apply {
            val currentRecordIndex = indexOfFirst { it.name == record.name }

            stopPlayingExceptCurrent(currentRecordIndex)

            val newRecord = set(
                currentRecordIndex, record.copy(
                    recordState = when (record.recordState) {
                        is RecordUi.RecordState.Pause -> RecordUi.RecordState.Playing
                        is RecordUi.RecordState.Playing -> RecordUi.RecordState.Pause
                        is RecordUi.RecordState.Stop -> RecordUi.RecordState.Playing
                    }
                )
            )

            startPauseRecordPlayer(newRecord)
        }
    }

    private fun refreshRecords() {
        getRecordFilesInteractor()
            .sortedByDescending(File::lastModified)
            .map(::RecordUi)
            .also(_records::setValue)
    }

    fun startPauseRecord(record: RecordUi) {
        changeStartPauseUiState(record)
    }

    fun setStoppedUiStateToAllRecords() {
        _records.value = _records.value?.toMutableList()?.onEach {
            it.recordState = RecordUi.RecordState.Stop
        }
    }

    fun removeRecordUi(record: RecordUi) {
        previouslyRemovedRecord = record

        _records.value = _records.value?.toMutableList()?.filter {
            record.name != it.name
        }
    }

    fun restorePreviouslyRemovedRecordUi() {
        previouslyRemovedRecord?.let {
            _records.value = _records.value?.toMutableList()?.apply {
                previouslyRemovedRecord = null
                add(it)
            }?.sortedByDescending { it.file.lastModified() }
        }
    }

    fun removeRecordPermanently() {
        previouslyRemovedRecord?.file?.let(deleteRecordFileInteractor::invoke)
    }
}