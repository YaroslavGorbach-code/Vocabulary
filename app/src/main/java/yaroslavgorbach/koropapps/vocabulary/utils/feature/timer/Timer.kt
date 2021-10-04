package yaroslavgorbach.koropapps.vocabulary.utils.feature.timer

import androidx.lifecycle.LiveData

interface Timer {
    companion object {
        const val ONE_SECOND = 1000L
        const val ONE_MINUTE = 60000L
    }

    sealed class State {
        class Tick(val millisecondsUntilFinished: Long, val duration: Long) : State()
        object Finish : State()
    }

    fun start(countDownTime: Long, interval: Long)
    fun cancel()
    val state: LiveData<State>
}