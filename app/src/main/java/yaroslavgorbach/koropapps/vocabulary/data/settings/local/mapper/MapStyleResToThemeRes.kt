package yaroslavgorbach.koropapps.vocabulary.data.settings.local.mapper

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.*

class MapStyleResToThemeRes {

    fun map(styleRes: Int): ThemeRes {
        return when (styleRes) {
            R.style.BaseAppTheme_Teal -> ThemeResTeal()
            R.style.BaseAppTheme_Blue -> ThemeResBlue()
            R.style.BaseAppTheme_Indigo -> ThemeResIndigo()
            R.style.BaseAppTheme_Purple -> ThemeResPurple()
            R.style.BaseAppTheme_DeepPurple -> ThemeResDeepPurple()
            R.style.BaseAppTheme_Pink -> ThemeResPink()
            R.style.BaseAppTheme_Red -> ThemeResRed()
            R.style.BaseAppTheme_LightBlue -> ThemeResLightBlue()
            R.style.BaseAppTheme_Green -> ThemeResGreen()
            R.style.BaseAppTheme_LightGreen -> ThemeResLightGreen()
            R.style.BaseAppTheme_Lime -> ThemeResLime()
            R.style.BaseAppTheme_Yellow -> ThemeResYellow()
            R.style.BaseAppTheme_Amber -> ThemeResAmber()
            R.style.BaseAppTheme_Orange -> ThemeResOrange()
            R.style.BaseAppTheme_DeepOrange -> ThemeResDeepOrange()
            R.style.BaseAppTheme_Brown -> ThemeResBrown()
            else -> error("Can not map style resource to ThemeRes value class")
        }
    }
}