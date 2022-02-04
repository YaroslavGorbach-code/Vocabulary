package yaroslavgorbach.koropapps.vocabulary.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import yaroslavgorbach.koropapps.vocabulary.R


fun ViewBinding.getString(@StringRes id: Int): String {
    return root.context.getString(id)
}

fun ViewBinding.getDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(root.context, id)
}

fun Context.getAppComputeDrawable(resId: Int): Drawable? = AppCompatResources.getDrawable(this, resId)


fun Context.getColorPrimary(): Int {
    val typedValue = TypedValue()
    val a: TypedArray = obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorPrimary))
    val color = a.getColor(0, 0)
    a.recycle()
    return color
}

val Context.colorBackground: Int
    get() {
        val typedValue = TypedValue()
        val a: TypedArray =
            obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.colorBackground))
        val color = a.getColor(0, 0)
        a.recycle()

        return color
    }

val Context.statusBarColor: Int
    get() {
        val typedValue = TypedValue()
        val a: TypedArray =
            obtainStyledAttributes(typedValue.data, intArrayOf(android.R.attr.statusBarColor))
        val color = a.getColor(0, 0)
        a.recycle()

        return color
    }

fun Context.isSystemNightMode() =
    when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO -> false
        Configuration.UI_MODE_NIGHT_YES -> true
        else -> error("can not get system night mode")
    }

fun Drawable.animate(){
    (this as AnimatedVectorDrawable).start()
}