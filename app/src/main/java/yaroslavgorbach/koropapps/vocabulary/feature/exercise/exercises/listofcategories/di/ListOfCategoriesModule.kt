package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.listofcategories.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.listofcategories.presentation.ListOfCategoriesViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class ListOfCategoriesModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListOfCategoriesViewModel::class)
    abstract fun bindListOfCategoriesViewModel(viewModel: ListOfCategoriesViewModel): ViewModel
}