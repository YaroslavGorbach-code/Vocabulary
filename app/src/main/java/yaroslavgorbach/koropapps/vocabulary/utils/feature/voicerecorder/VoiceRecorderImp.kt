package yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder

import android.content.Context
import android.media.MediaRecorder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.send
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class VoiceRecorderImp(private val context: Context) : VoiceRecorder {
    private var _isRecording: MutableLiveData<Boolean> = MutableLiveData(false)

    override val isRecording: LiveData<Boolean>
        get() = _isRecording

    private val _isRecordSaved: MutableLiveEvent<Unit> = MutableLiveEvent()

    override val isRecordSaved: LiveEvent<Unit>
        get() = _isRecordSaved

    private var mediaRecorder: MediaRecorder? = null

    override fun start(fileName: String) {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(
                context.getExternalFilesDir("/")!!.absolutePath + "/" + createAudioFileName(
                    fileName
                )
            )
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            start()

            _isRecording.value = true
        }
    }

    override fun stop() {
        mediaRecorder?.let { recorder ->
            try {
                recorder.stop()
                recorder.release()
                _isRecordSaved.send(Unit)
            }catch (e: Exception){}
            mediaRecorder = null

            _isRecording.value = false
        }
    }

    private fun createAudioFileName(fileName: String): String {
        val format = SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.getDefault())

        return (fileName + format.format(Date().time).toString() + ".3gp")
            .replace(" ", "_")
    }
}