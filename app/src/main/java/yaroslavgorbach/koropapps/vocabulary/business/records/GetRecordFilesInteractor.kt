package yaroslavgorbach.koropapps.vocabulary.business.records

import yaroslavgorbach.koropapps.vocabulary.data.records.repo.RepoRecords
import java.io.File

class GetRecordFilesInteractor(private val repoRecords: RepoRecords) {

    operator fun invoke(): List<File> {
        return repoRecords.getRecordFiles()
    }

}