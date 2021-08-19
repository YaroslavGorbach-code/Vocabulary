package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.presentation.TenViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class TenExerciseModule {
    @Binds
    @IntoMap
    @ViewModelKey(TenViewModel::class)
    abstract fun bindTenViewModel(viewModel: TenViewModel): ViewModel
}