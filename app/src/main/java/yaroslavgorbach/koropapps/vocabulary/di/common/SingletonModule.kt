package yaroslavgorbach.koropapps.vocabulary.di.common

import android.app.Application
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveAdFeatureAvailability
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveInterstitialShowAvailability
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManagerImp
import yaroslavgorbach.koropapps.vocabulary.utils.feature.billing.BillingManager
import javax.inject.Singleton

@Module
class SingletonModule {

    @Singleton
    @Provides
    fun provideAdManager(
        context: Application,
        observeAdFeatureAvailability: ObserveAdFeatureAvailability,
        observeInterstitialShowAvailability: ObserveInterstitialShowAvailability
    ): AdManager {
        return AdManagerImp(
            context,
            observeAdFeatureAvailability,
            observeInterstitialShowAvailability
        )
    }

    @Singleton
    @Provides
    fun provideBillingManager(
        context: Application,
    ): BillingManager {
        return BillingManager(context)
    }
}