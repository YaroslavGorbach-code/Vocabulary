package yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder

import androidx.lifecycle.LiveData
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent

interface VoiceRecorder {

    val isRecording: LiveData<Boolean>

    val isRecordSaved: LiveEvent<Unit>

    fun start(fileName: String)

    fun stop()
}