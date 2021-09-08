package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.LevelViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class LevelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LevelViewModel::class)
    abstract fun bindProfileViewModel(viewModel: LevelViewModel): ViewModel
}