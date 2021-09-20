package yaroslavgorbach.koropapps.vocabulary.data.settings.factory

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme

class ThemeFactory {
    fun create(res: Int): Theme {
        // TODO: 9/16/2021 put logic of creating theme here
        return when (res) {
            R.style.BaseAppTheme -> Theme.Base()
            R.style.BaseAppTheme_Blue -> Theme.Blue()
            R.style.BaseAppTheme_Indigo -> Theme.Indigo()
            R.style.BaseAppTheme_DeepPurple -> Theme.DeepPurple()
            R.style.BaseAppTheme_Purple -> Theme.Purple()
            R.style.BaseAppTheme_Pink -> Theme.Pink()
            R.style.BaseAppTheme_Red -> Theme.Red()
            else -> Theme.Base()
        }
    }
}