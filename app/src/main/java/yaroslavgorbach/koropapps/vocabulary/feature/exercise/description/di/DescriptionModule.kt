package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation.DescriptionViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class DescriptionModule {
    @Binds
    @IntoMap
    @ViewModelKey(DescriptionViewModel::class)
    abstract fun bindDescriptionViewModel(viewModel: DescriptionViewModel): ViewModel
}