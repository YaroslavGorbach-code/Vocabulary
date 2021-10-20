package yaroslavgorbach.koropapps.vocabulary.feature.records.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.business.records.GetRecordFilesInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.feature.player.RecordPlayer
import java.io.File
import javax.inject.Inject

class RecordsViewModel @Inject constructor(
    private val getRecordFilesInteractor: GetRecordFilesInteractor,
    private val recordsPlayer: RecordPlayer
) : ViewModel() {

    private val _records: MutableLiveData<List<RecordUi>> = MutableLiveData()

    val records: LiveData<List<RecordUi>>
        get() = _records

    val playerFinishedEvent: LiveEvent<Unit>
        get() = recordsPlayer.finishEvent

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

            val elementIndex = indexOfFirst { it.name == record.name }

            stopPlayingExceptCurrent(elementIndex)

            set(
                elementIndex, record.copy(
                    recordState = if (record.recordState is RecordUi.RecordState.Playing) {
                        RecordUi.RecordState.Pause
                    } else {
                        RecordUi.RecordState.Playing
                    }
                )
            )
        }
    }

    fun refreshRecords() {
        getRecordFilesInteractor()
            .sortedBy(File::lastModified)
            .sortedDescending()
            .map(::RecordUi)
            .also(_records::setValue)
    }

    fun startPauseRecord(record: RecordUi) {
        changeStartPauseUiState(record)
        startPauseRecordPlayer(record)
    }

    fun setStoppedUiStateToAllRecords() {
        _records.value = _records.value?.toMutableList()?.onEach {
            it.recordState = RecordUi.RecordState.Stop
        }
    }
}