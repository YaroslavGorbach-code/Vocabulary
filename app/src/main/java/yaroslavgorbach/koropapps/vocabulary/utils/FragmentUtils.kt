package yaroslavgorbach.koropapps.vocabulary.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.App

inline fun <reified T> Fragment.host(): T = (parentFragment ?: activity) as T

fun Fragment.appComponent() = (requireActivity().application as App).appComponent

fun Fragment.onBackPressed() = requireActivity().onBackPressed()
