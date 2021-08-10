package yaroslavgorbach.koropapps.vocabulary.utils

import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.host(): T = (parentFragment ?: activity) as T
