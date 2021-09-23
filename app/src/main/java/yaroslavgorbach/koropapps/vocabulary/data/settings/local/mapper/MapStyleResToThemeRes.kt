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
            R.style.BaseAppTheme_Pink -> ThemeResPurple()
            R.style.BaseAppTheme_Red -> ThemeResRed()
            else -> error("Can not map style resource to ThemeRes value class")
        }
    }
}