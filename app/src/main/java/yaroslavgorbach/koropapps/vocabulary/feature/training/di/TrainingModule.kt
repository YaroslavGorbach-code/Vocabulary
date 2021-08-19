package yaroslavgorbach.koropapps.vocabulary.feature.training.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.training.presentation.TrainingViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class TrainingModule {
    @Binds
    @IntoMap
    @ViewModelKey(TrainingViewModel::class)
    abstract fun bindTrainingViewModel(viewModel: TrainingViewModel): ViewModel
}