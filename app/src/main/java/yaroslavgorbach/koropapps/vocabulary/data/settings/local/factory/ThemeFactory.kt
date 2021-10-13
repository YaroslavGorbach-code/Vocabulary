package yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.*

class ThemeFactory {
    fun create(themeRes: ThemeRes): Theme {
        return when (themeRes) {
            is ThemeResTeal -> Theme.Teal()
            is ThemeResBlue -> Theme.Blue()
            is ThemeResIndigo -> Theme.Indigo()
            is ThemeResDeepPurple -> Theme.DeepPurple()
            is ThemeResPurple -> Theme.Purple()
            is ThemeResPink -> Theme.Pink()
            is ThemeResRed -> Theme.Red()
            is ThemeResLightBlue -> Theme.LightBlue()
            is ThemeResGreen -> Theme.Green()
            is ThemeResLightGreen -> Theme.LightGreen()
            is ThemeResLime -> Theme.Lime()
            is ThemeResYellow -> Theme.Yellow()
            is ThemeResAmber -> Theme.Amber()
            is ThemeResOrange -> Theme.Orange()
            is ThemeResDeepOrange -> Theme.DeepOrange()
            is ThemeResBrown -> Theme.Brown()
            is ThemeResBlueGray -> Theme.BlueGray()
            else -> error("There are no theme associated wit this resource")
        }
    }
}