package yaroslavgorbach.koropapps.vocabulary.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.MainActivity
import yaroslavgorbach.koropapps.vocabulary.di.business.description.BusinessDescriptionModule
import yaroslavgorbach.koropapps.vocabulary.di.business.exercises.BusinessExercisesModule
import yaroslavgorbach.koropapps.vocabulary.di.business.phrase.BusinessPhraseModule
import yaroslavgorbach.koropapps.vocabulary.di.business.settings.BusinessSettingsModule
import yaroslavgorbach.koropapps.vocabulary.di.business.statistics.BusinessStatisticsModule
import yaroslavgorbach.koropapps.vocabulary.di.business.training.BusinessTrainingModule
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.di.DescriptionComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.di.AlphabetExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.antonymssynonyms.di.AntonymsSynonymsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.associations.di.AssociationsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.dictionary.di.DictionaryExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.game.di.GameExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.half.di.HalfExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.jar.di.JarExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.listofcategories.di.ListOfCategoriesComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.narrator.di.NarratorExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.rememberall.di.RememberAllExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.specifications.di.SpecificationsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.tautograms.di.TautogramsExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.ten.di.TenExerciseComponent
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.threeletters.di.ThreeLettersExerciseComponent
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
        BusinessDescriptionModule::class,
        BusinessStatisticsModule::class,
        BusinessPhraseModule::class,
        BusinessSettingsModule::class
    ]
)
interface AppComponent {

    fun antonymsSynonymsComponent(): AntonymsSynonymsExerciseComponent.Factory

    fun exercisesListComponent(): ExercisesListComponent.Factory

    fun trainingComponent(): TrainingComponent.Factory

    fun descriptionComponent(): DescriptionComponent.Factory

    fun associationsComponent(): AssociationsExerciseComponent.Factory

    fun gameComponent(): GameExerciseComponent.Factory

    fun jarComponent(): JarExerciseComponent.Factory

    fun narratorComponent(): NarratorExerciseComponent.Factory

    fun rememberAllComponent(): RememberAllExerciseComponent.Factory

    fun tautogramsComponent(): TautogramsExerciseComponent.Factory

    fun tenComponent(): TenExerciseComponent.Factory

    fun alphabetComponent(): AlphabetExerciseComponent.Factory

    fun profileComponent(): ProfileComponent.Factory

    fun listOfCategoriesComponent(): ListOfCategoriesComponent.Factory

    fun threeLettersComponent(): ThreeLettersExerciseComponent.Factory

    fun halfComponent(): HalfExerciseComponent.Factory

    fun specificationsComponent(): SpecificationsExerciseComponent.Factory

    fun levelComponent(): LevelComponent.Factory

    fun dictionaryComponent(): DictionaryExerciseComponent.Factory

    fun settingsComponent(): SettingsComponent.Factory

    @FlowPreview
    @InternalCoroutinesApi
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): AppComponent
    }
}