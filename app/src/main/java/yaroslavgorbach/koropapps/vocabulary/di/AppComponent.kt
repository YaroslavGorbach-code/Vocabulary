package yaroslavgorbach.koropapps.vocabulary.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.MainActivity
import yaroslavgorbach.koropapps.vocabulary.di.business.exercises.BusinessExercisesModule
import yaroslavgorbach.koropapps.vocabulary.di.business.phrase.BusinessPhraseModule
import yaroslavgorbach.koropapps.vocabulary.di.business.settings.BusinessSettingsModule
import yaroslavgorbach.koropapps.vocabulary.di.business.statistics.BusinessStatisticsModule
import yaroslavgorbach.koropapps.vocabulary.di.business.training.BusinessTrainingModule
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.word.di.WordExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithcategory.di.WordWithCategoryExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.wordwithtimer.di.WordWithTimerExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.di.ExercisesListComponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.di.LevelComponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.di.ProfileComponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.di.SettingsComponent
import yaroslavgorbach.koropapps.vocabulary.feature.training.di.TrainingComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        BusinessTrainingModule::class,
        BusinessExercisesModule::class,
        BusinessStatisticsModule::class,
        BusinessPhraseModule::class,
        BusinessSettingsModule::class
    ]
)
interface AppComponent {

    fun exercisesListComponent(): ExercisesListComponent.Factory

    fun trainingComponent(): TrainingComponent.Factory

    fun descriptionComponent(): DescriptionComponent.Factory

    fun wordExerciseComponent(): WordExerciseComponent.Factory

    fun profileComponent(): ProfileComponent.Factory

    fun wordWithCategoryExerciseComponent(): WordWithCategoryExerciseComponent.Factory

    fun levelComponent(): LevelComponent.Factory

    fun wordWithTimerExerciseComponent(): WordWithTimerExerciseComponent.Factory

    fun settingsComponent(): SettingsComponent.Factory

    @FlowPreview
    @InternalCoroutinesApi
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): AppComponent
    }
}