package yaroslavgorbach.koropapps.vocabulary.di.common

import android.app.Application
import androidx.activity.result.ActivityResultRegistry
import dagger.Module
import dagger.Provides
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveAdFeatureAvailability
import yaroslavgorbach.koropapps.vocabulary.business.settings.ObserveInterstitialShowAvailability
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.ad.AdManagerImp
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManager
import yaroslavgorbach.koropapps.vocabulary.utils.feature.permition.PermissionManagerImp
import yaroslavgorbach.koropapps.vocabulary.utils.feature.player.RecordPlayer
import yaroslavgorbach.koropapps.vocabulary.utils.feature.player.RecordPlayerImp
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorder
import yaroslavgorbach.koropapps.vocabulary.utils.feature.voicerecorder.VoiceRecorderImp

@Module
class FeatureUtilModule {

    @Provides
    fun provideVoiceRecorder(context: Application): VoiceRecorder {
        return VoiceRecorderImp(context)
    }

    @Provides
    fun provideRecordPlayer(): RecordPlayer {
        return RecordPlayerImp()
    }

    @Provides
    fun providePermissionManager(
        activityResultRegistry: ActivityResultRegistry,
        context: Application
    ): PermissionManager {
        return PermissionManagerImp(activityResultRegistry, context)
    }

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
}