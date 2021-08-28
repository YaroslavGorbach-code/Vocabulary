package yaroslavgorbach.koropapps.vocabulary.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
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

fun Context.getColorPrimary(): Int {

    val typedValue = TypedValue()
    theme.resolveAttribute(
        yaroslavgorbach.koropapps.vocabulary.R.attr.colorPrimary,
        typedValue,
        true
    )

    return typedValue.data
}