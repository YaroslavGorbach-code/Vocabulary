package yaroslavgorbach.koropapps.vocabulary.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import yaroslavgorbach.koropapps.vocabulary.R

fun ViewBinding.getString(@StringRes id: Int): String {
    return root.context.getString(id)
}

fun ViewBinding.getDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(root.context, id)
}

fun Context.getColorPrimary(): Int {
    val typedValue = TypedValue()
    obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorPrimary)).use {}
    return typedValue.data
}