package yaroslavgorbach.koropapps.vocabulary.business.settings

import yaroslavgorbach.koropapps.vocabulary.data.settings.repo.RepoSettings

class ChangeAutoRecordStateInteractor(private val repoSettings: RepoSettings) {
    suspend operator fun invoke(isAutoRecordTurnedOn: Boolean) {
        repoSettings.changeAutoRecordState(isAutoRecordTurnedOn)
    }
}