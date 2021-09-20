package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.graphics.Color
import yaroslavgorbach.koropapps.vocabulary.R

// TODO: 9/16/2021 add themes
sealed class Theme {
    abstract val res: Int

    abstract val colorPrimary: Int

    class Base(
        override val res: Int = R.style.BaseAppTheme,
        override val colorPrimary: Int = Color.parseColor("#4db6ac")
    ) : Theme()

    class Blue(
        override val res: Int = R.style.BaseAppTheme_Blue,
        override val colorPrimary: Int = Color.parseColor("#64b5f6")
    ) : Theme()

    class Indigo(
        override val res: Int = R.style.BaseAppTheme_Indigo,
        override val colorPrimary: Int = Color.parseColor("#5c6bc0")
    ) : Theme()

    class Purple(
        override val res: Int = R.style.BaseAppTheme_Purple,
        override val colorPrimary: Int = Color.parseColor("#7e57c2")
    ) : Theme()

    class DeepPurple(
        override val res: Int = R.style.BaseAppTheme_DeepPurple,
        override val colorPrimary: Int = Color.parseColor("#ba68c8")
    ) : Theme()

    class Pink(
        override val res: Int = R.style.BaseAppTheme_Pink,
        override val colorPrimary: Int = Color.parseColor("#f06292")
    ) : Theme()

    class Red(
        override val res: Int = R.style.BaseAppTheme_Red,
        override val colorPrimary: Int = Color.parseColor("#ef5350")
    ) : Theme()
}