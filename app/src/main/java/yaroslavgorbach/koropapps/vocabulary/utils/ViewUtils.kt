package yaroslavgorbach.koropapps.vocabulary.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> ViewGroup.inflateBind(inflateFunc: (LayoutInflater, ViewGroup, Boolean) -> T) =
    inflateFunc(LayoutInflater.from(context), this, false)

fun Activity.setBackgroundStatusBarColor() {
    window.statusBarColor = colorBackground
}

fun Activity.setDefaultStatusBarColor() {
    window.statusBarColor = statusBarColor
}
