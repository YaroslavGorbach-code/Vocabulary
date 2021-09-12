package yaroslavgorbach.koropapps.vocabulary.utils.feature.timer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import yaroslavgorbach.koropapps.vocabulary.utils.Event
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.MutableLiveEvent
import yaroslavgorbach.koropapps.vocabulary.utils.feature.timer.Timer.Companion.ONE_SECOND
import yaroslavgorbach.koropapps.vocabulary.utils.send

class TimerImp : Timer {

    private val _state: MutableLiveData<Timer.State> = MutableLiveData()

    override val state: LiveData<Timer.State>
        get() = _state

    private lateinit var  countDownTimer: CountDownTimer

    override fun start(countDownTime: Long, interval: Long) {
        countDownTimer = object : CountDownTimer(countDownTime, interval) {
            override fun onTick(millisUntilFinished: Long) {
                _state.value = Timer.State.Tick(millisUntilFinished)
            }

            override fun onFinish() {
                _state.value = Timer.State.Finish
            }
        }.start()
    }

    override fun cancel() {
        if (::countDownTimer.isInitialized){
            countDownTimer.cancel()
        }
    }
}