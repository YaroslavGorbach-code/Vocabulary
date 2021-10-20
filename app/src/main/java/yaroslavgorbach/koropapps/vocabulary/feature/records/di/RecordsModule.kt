package yaroslavgorbach.koropapps.vocabulary.feature.records.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.FeatureUtilModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.presentation.ProfileViewModel
import yaroslavgorbach.koropapps.vocabulary.feature.records.presentation.RecordsViewModel

@Module(includes = [ViewModelBuilderModule::class, FeatureUtilModule::class])
abstract class RecordsModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecordsViewModel::class)
    abstract fun bindProfileViewModel(viewModel: RecordsViewModel): ViewModel
}