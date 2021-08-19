package yaroslavgorbach.koropapps.vocabulary

import android.app.Application
import yaroslavgorbach.koropapps.vocabulary.di.DaggerAppComponent

class App : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}