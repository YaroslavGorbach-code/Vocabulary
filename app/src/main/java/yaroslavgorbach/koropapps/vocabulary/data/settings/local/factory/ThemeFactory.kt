package yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.*

class ThemeFactory {
    fun create(themeRes: ThemeRes): Theme {
        // TODO: 9/16/2021 put logic of creating theme here
        return when (themeRes) {
            is ThemeResTeal -> Theme.Teal()
            is ThemeResBlue -> Theme.Blue()
            is ThemeResIndigo -> Theme.Indigo()
            is ThemeResDeepPurple -> Theme.DeepPurple()
            is ThemeResPurple -> Theme.Purple()
            is ThemeResPink -> Theme.Pink()
            is ThemeResRed -> Theme.Red()
            else -> error("There are no theme associated wit this resource")
        }
    }
}