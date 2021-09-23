package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import yaroslavgorbach.koropapps.vocabulary.R

interface ThemeRes {
    val id: Int
}

@JvmInline
value class ThemeResTeal(override val id: Int = R.style.BaseAppTheme_Teal) : ThemeRes

@JvmInline
value class ThemeResBlue(override val id: Int = R.style.BaseAppTheme_Blue) : ThemeRes

@JvmInline
value class ThemeResIndigo(override val id: Int = R.style.BaseAppTheme_Indigo) : ThemeRes

@JvmInline
value class ThemeResPurple(override val id: Int = R.style.BaseAppTheme_Purple) : ThemeRes

@JvmInline
value class ThemeResDeepPurple(override val id: Int = R.style.BaseAppTheme_DeepPurple) : ThemeRes

@JvmInline
value class ThemeResPink(override val id: Int = R.style.BaseAppTheme_Pink) : ThemeRes

@JvmInline
value class ThemeResRed(override val id: Int = R.style.BaseAppTheme_Red) : ThemeRes

