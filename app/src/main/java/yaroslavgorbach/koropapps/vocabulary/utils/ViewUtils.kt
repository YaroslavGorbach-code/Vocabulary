package yaroslavgorbach.koropapps.vocabulary.utils

import android.app.Activity
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> ViewGroup.inflateBinding(inflateFunc: (LayoutInflater, ViewGroup, Boolean) -> T) =
    inflateFunc(LayoutInflater.from(context), this, false)

fun Activity.setBackgroundStatusBarColor() {
    window.statusBarColor = colorBackground

    if (isSystemNightMode().not()) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun Activity.setPrimaryStatusBarColor() {
    window.statusBarColor = getColorPrimary()
    window.decorView.systemUiVisibility = 0
}

fun Activity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
    window.decorView.systemUiVisibility = 0
}

fun Activity.setDefaultStatusBarColor() {
    window.statusBarColor = statusBarColor

    if (isSystemNightMode().not()) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun Activity.setKeepScreenOn(isNeedToKeep: Boolean) {
    if (isNeedToKeep) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    } else {
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
