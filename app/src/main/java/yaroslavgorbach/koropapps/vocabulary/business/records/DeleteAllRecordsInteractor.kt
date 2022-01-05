package yaroslavgorbach.koropapps.vocabulary.business.records

import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecords

class DeleteAllRecordsInteractor(private val repoRecords: RepoRecords) {
    operator fun invoke() = repoRecords.deleteAllRecords()
}