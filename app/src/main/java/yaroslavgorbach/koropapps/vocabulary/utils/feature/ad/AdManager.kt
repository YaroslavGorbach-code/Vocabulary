package yaroslavgorbach.koropapps.vocabulary.utils.feature.ad

import android.app.Activity

interface AdManager {
    suspend fun loadInterstitial()
    suspend fun showInterstitial(activity: Activity)
}