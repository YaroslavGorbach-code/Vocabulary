package yaroslavgorbach.koropapps.vocabulary.utils.feature.player

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import androidx.lifecycle.*
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.send
import java.io.File
import java.io.IOException

class RecordPlayerImp : RecordPlayer, LifecycleObserver {

    private val _progress: MutableLiveData<Int> = MutableLiveData()

    override val progress: LiveData<Int>
        get() = _progress

    private val _duration: MutableLiveData<Int> = MutableLiveData()

    override val duration: LiveData<Int>
        get() = _duration

    private val _finishEvent: MutableLiveEvent<Unit> = MutableLiveEvent()

    override val finishEvent: LiveEvent<Unit>
        get() = _finishEvent

    private var mediaPlayer: MediaPlayer? = null

    override fun play(file: File) {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            stop()
        }

        try {
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setDataSource(file.absolutePath)
            mediaPlayer!!.prepare()
            _duration.setValue(mediaPlayer!!.duration)
            //initProgressRunnable()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer?.setOnPreparedListener { mediaPlayer!!.start() }
        mediaPlayer?.setOnCompletionListener {
            _finishEvent.send(Unit)
            stop()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun stop() {
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
            //mProgressHandler.removeCallbacks(progressRunnable)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun pause() {
        if (mediaPlayer != null) {
            //mCallback.finish()
            mediaPlayer!!.pause()
            // mCurrentRecord.isPause = true
            //mProgressHandler.removeCallbacks(progressRunnable)
        }
    }

    override fun resume() {
        if (mediaPlayer != null) {
            mediaPlayer!!.start()
//            mCurrentRecord.isPause = false
//            initProgressRunnable()
        }
    }

    override fun seekToo(progress: Int) {

    }
}