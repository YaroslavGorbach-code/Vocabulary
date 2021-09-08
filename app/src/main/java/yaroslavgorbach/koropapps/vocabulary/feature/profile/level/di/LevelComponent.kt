package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.di

import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui.LevelFragment

@Subcomponent(modules = [LevelModule::class])
interface LevelComponent {

    fun inject(fragment: LevelFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): LevelComponent
    }

}