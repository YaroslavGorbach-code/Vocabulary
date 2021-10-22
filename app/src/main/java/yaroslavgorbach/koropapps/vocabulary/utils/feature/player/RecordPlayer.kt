package yaroslavgorbach.koropapps.vocabulary.utils.feature.player

import androidx.lifecycle.LiveData
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import java.io.File

interface RecordPlayer {
    val progress: LiveData<Int>
    val finishEvent: LiveEvent<Unit>

    fun play(file: File)
    fun stop()
    fun pause()
    fun resume()
    fun seekToo(progress: Int)
}