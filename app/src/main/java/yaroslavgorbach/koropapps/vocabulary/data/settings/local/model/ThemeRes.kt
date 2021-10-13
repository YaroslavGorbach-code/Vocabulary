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

@JvmInline
value class ThemeResLightBlue(override val id: Int = R.style.BaseAppTheme_LightBlue) : ThemeRes

@JvmInline
value class ThemeResGreen(override val id: Int = R.style.BaseAppTheme_Green) : ThemeRes

@JvmInline
value class ThemeResLightGreen(override val id: Int = R.style.BaseAppTheme_LightGreen) : ThemeRes

@JvmInline
value class ThemeResLime(override val id: Int = R.style.BaseAppTheme_Lime) : ThemeRes

@JvmInline
value class ThemeResYellow(override val id: Int = R.style.BaseAppTheme_Yellow) : ThemeRes

@JvmInline
value class ThemeResAmber(override val id: Int = R.style.BaseAppTheme_Amber) : ThemeRes

@JvmInline
value class ThemeResOrange(override val id: Int = R.style.BaseAppTheme_Orange) : ThemeRes

@JvmInline
value class ThemeResDeepOrange(override val id: Int = R.style.BaseAppTheme_DeepOrange) : ThemeRes

@JvmInline
value class ThemeResBrown(override val id: Int = R.style.BaseAppTheme_Brown) : ThemeRes

@JvmInline
value class ThemeResBlueGray(override val id: Int = R.style.BaseAppTheme_BlueGray) : ThemeRes
