package yaroslavgorbach.koropapps.vocabulary.utils

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

fun ViewBinding.getString(@StringRes id: Int): String {
    return root.context.getString(id)
}

fun ViewBinding.getDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(root.context, id)
}