package yaroslavgorbach.koropapps.vocabulary

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.getSystemService
import yaroslavgorbach.koropapps.vocabulary.di.DaggerAppComponent
import yaroslavgorbach.koropapps.vocabulary.utils.createChannel

class App : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        getSystemService<NotificationManager>()?.createChannel()
    }
}