package yaroslavgorbach.koropapps.vocabulary.utils.feature.player

import androidx.lifecycle.LiveData
import java.io.File

interface RecordPlayer {
    fun play(file: File)
    fun stop()
    fun pause()
    fun resume()
    fun getProgress(): LiveData<Int?>?
    fun getDuration(): LiveData<Int?>?
    fun seekToo(progress: Int)
}