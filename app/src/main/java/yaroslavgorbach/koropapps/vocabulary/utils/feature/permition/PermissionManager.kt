package yaroslavgorbach.koropapps.vocabulary.utils.feature.permition

import android.app.Activity

interface PermissionManager {
    fun requestPermission(
        permission: String,
        callback: (isGranted: Boolean) -> Unit,
    )

    fun isNeverAskAgainChecked(activity: Activity, permission: String): Boolean

    fun checkPermission(
        permission: String,
        callback: (isGranted: Boolean) -> Unit
    )

    fun showSystemSettingsActivity()

}