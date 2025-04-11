package yaroslavgorbach.koropapps.vocabulary.utils.feature.ad

import android.app.Activity
import android.app.Application
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveAdFeatureAvailability
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveInterstitialShowAvailability

class AdManagerImp(
    private val app: Application,
    private val observeAdFeatureAvailability: ObserveAdFeatureAvailability,
    private val observeInterstitialShowAvailability: ObserveInterstitialShowAvailability,
) : AdManager {

    companion object {
        private const val INTERSTITIAL_AD_ID = "ca-app-pub-6043694180023070/1202276190"
        private const val INTERSTITIAL_TEST_AD_ID = "ca-app-pub-3940256099942544/1033173712"
    }

    private var _interstitialAd: InterstitialAd? = null

    override suspend fun loadInterstitial() {
        observeAdFeatureAvailability().combine(observeInterstitialShowAvailability()) { isAdFeatureAvailable, isInterstitialAdAvailable ->
            if (isAdFeatureAvailable && isInterstitialAdAvailable) {
                InterstitialAd.load(
                    app,
                    INTERSTITIAL_AD_ID,
                    AdRequest.Builder().build(),
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
                            _interstitialAd = interstitialAd

                            interstitialAd.fullScreenContentCallback =
                                object : FullScreenContentCallback() {
                                    override fun onAdDismissedFullScreenContent() {
                                        _interstitialAd = null
                                    }

                                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                        _interstitialAd = null
                                    }

                                    override fun onAdShowedFullScreenContent() {

                                    }
                                }
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            _interstitialAd = null
                        }
                    })
            }
        }.collect()
    }

    override suspend fun showInterstitial(activity: Activity) {
        withContext(Dispatchers.Main) {
            _interstitialAd?.show(activity)
            loadInterstitial()
        }
    }
}