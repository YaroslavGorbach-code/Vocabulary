package yaroslavgorbach.koropapps.vocabulary.business.records

import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecords
import java.io.File

class DeleteRecordFileInteractor(private val repoRecords: RepoRecords) {
    operator fun invoke(file: File) {
        repoRecords.deleteRecordFile(file)
    }
}