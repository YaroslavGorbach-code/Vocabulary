package yaroslavgorbach.koropapps.vocabulary.data.records.repo

import android.content.Context
import java.io.File

class RepoRecordsImp(private val context: Context) : RepoRecords {

    override fun getRecordFiles(): List<File> {
        return (File(context.getExternalFilesDir("/")?.absolutePath ?: "")
            .listFiles()?: emptyArray<File>()).toList()
    }

}