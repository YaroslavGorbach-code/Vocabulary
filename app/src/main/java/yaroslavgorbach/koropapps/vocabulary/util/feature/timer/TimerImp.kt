package yaroslavgorbach.koropapps.vocabulary.util.feature.timer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.util.Event
import yaroslavgorbach.koropapps.vocabulary.util.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.util.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.util.send

class TimerImp : Timer {

    companion object {
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L
    }

    private val _finishEvent: MutableLiveEvent<Event<Unit>> = MutableLiveEvent()

    override val finishEvent: LiveEvent<Event<Unit>>
        get() = _finishEvent

    private val _value: MutableLiveData<Int> = MutableLiveData()

    override val value: LiveData<Int>
        get() = _value

    override fun start() {
        object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _value.value = (millisUntilFinished / ONE_SECOND).toInt()
            }

            override fun onFinish() {
                _value.value = 0
                _finishEvent.send(Event(Unit))
            }

        }
    }

}