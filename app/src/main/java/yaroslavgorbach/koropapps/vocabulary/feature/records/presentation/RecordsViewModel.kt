package yaroslavgorbach.koropapps.vocabulary.feature.records.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import yaroslavgorbach.koropapps.vocabulary.business.records.GetRecordFilesInteractor
import yaroslavgorbach.koropapps.vocabulary.feature.records.model.RecordUi
import java.io.File
import javax.inject.Inject

class RecordsViewModel @Inject constructor(
    private val getRecordFilesInteractor: GetRecordFilesInteractor
) : ViewModel() {

    private val _records: MutableLiveData<List<RecordUi>> = MutableLiveData()

    val records: LiveData<List<RecordUi>>
        get() = _records

    init {
        refreshRecords()
    }

    fun refreshRecords() {
        getRecordFilesInteractor()
            .sortedBy(File::lastModified)
            .sortedDescending()
            .map(::RecordUi)
            .also(_records::setValue)
    }

    fun startPauseRecord(record: RecordUi) {
        fun MutableList<RecordUi>.stopPlayingExceptCurrent(elementIndex: Int) {
            forEachIndexed { index, recordUi ->
                if (index != elementIndex) {
                    recordUi.isPlaying = false
                }
            }
        }

        _records.value = _records.value?.toMutableList()?.apply {

            val elementIndex = indexOfFirst { it.name == record.name }

            stopPlayingExceptCurrent(elementIndex)

            set(elementIndex, record.copy(isPlaying = record.isPlaying.not()))
        }
    }

}