package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.wordwithcategory.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelBuilderModule
import yaroslavgorbach.koropapps.vocabulary.di.common.ViewModelKey
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.vocabulary.wordwithcategory.presentation.WordWithCategoryViewModel

@Module(includes = [ViewModelBuilderModule::class])
abstract class WordWithCategoryExerciceModule {
    @Binds
    @IntoMap
    @ViewModelKey(WordWithCategoryViewModel::class)
    abstract fun bindListOfCategoriesViewModel(viewModel: WordWithCategoryViewModel): ViewModel
}