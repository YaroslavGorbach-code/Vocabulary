package yaroslavgorbach.koropapps.vocabulary.util.feature.timer

import androidx.lifecycle.LiveData
import yaroslavgorbach.koropapps.vocabulary.util.Event
import yaroslavgorbach.koropapps.vocabulary.util.LiveEvent

interface Timer {
    fun start()
    val finishEvent: LiveEvent<Event<Unit>>
    val value: LiveData<Int>
}