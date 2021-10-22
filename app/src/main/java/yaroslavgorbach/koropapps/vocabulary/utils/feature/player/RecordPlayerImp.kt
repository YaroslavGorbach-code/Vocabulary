package yaroslavgorbach.koropapps.vocabulary.utils.feature.player

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
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

    private val _finishEvent: MutableLiveEvent<Unit> = MutableLiveEvent()

    override val finishEvent: LiveEvent<Unit>
        get() = _finishEvent

    private var mediaPlayer: MediaPlayer? = null

    private val progressHandler: Handler = Handler(Looper.getMainLooper())

    private var progressRunnable: Runnable? = null


    private fun startProgressRunnable() {
        progressRunnable = object : Runnable {
            override fun run() {
                progressHandler.postDelayed(this, 100)
                mediaPlayer?.let {
                    _progress.value =
                        ((it.currentPosition.toFloat() / it.duration.toFloat()) * 100f).toInt()
                }
            }
        }

        progressHandler.postDelayed(progressRunnable!!, 300)
    }

    private fun stopProgressRunnable() {
        progressRunnable?.let(progressHandler::removeCallbacks)
    }

    override fun play(file: File) {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            stop()
        }

        try {
            mediaPlayer = MediaPlayer()
            mediaPlayer!!.setDataSource(file.absolutePath)
            mediaPlayer!!.prepare()

            startProgressRunnable()
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

            _progress.value = 0
            stopProgressRunnable()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun pause() {
        if (mediaPlayer != null) {
            mediaPlayer!!.pause()
            stopProgressRunnable()
        }
    }

    override fun resume() {
        if (mediaPlayer != null) {
            mediaPlayer!!.start()
            startProgressRunnable()
        }
    }

    override fun seekToo(progress: Int) {

    }
}