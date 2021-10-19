package yaroslavgorbach.koropapps.vocabulary.feature.records.di

import dagger.Subcomponent
import yaroslavgorbach.koropapps.vocabulary.feature.profile.profile.ui.ProfileFragment
import yaroslavgorbach.koropapps.vocabulary.feature.records.ui.RecordsListFragment

@Subcomponent(modules = [RecordsModule::class])
interface RecordsComponent {

    fun inject(fragment: RecordsListFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): RecordsComponent
    }
}
