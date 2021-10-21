package yaroslavgorbach.koropapps.vocabulary.data.records.repo

import java.io.File

interface RepoRecords {

    fun getRecordFiles(): List<File>

    fun deleteRecordFile(file: File)
}