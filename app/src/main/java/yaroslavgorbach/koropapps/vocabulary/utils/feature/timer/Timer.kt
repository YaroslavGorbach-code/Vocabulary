package yaroslavgorbach.koropapps.vocabulary.utils.feature.timer

import androidx.lifecycle.LiveData
import yaroslavgorbach.koropapps.vocabulary.utils.Event
import yaroslavgorbach.koropapps.vocabulary.utils.LiveEvent

interface Timer {
    fun start()
    val finishEvent: LiveEvent<Event<Unit>>
    val value: LiveData<Int>
}