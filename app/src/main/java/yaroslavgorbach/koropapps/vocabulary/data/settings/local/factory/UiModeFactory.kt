package yaroslavgorbach.koropapps.vocabulary.data.settings.local.factory

import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode

class UiModeFactory {

    fun create(isDark: Boolean): UiMode{
        return if (isDark){
            UiMode.Dark()
        }else{
            UiMode.Light()
        }
    }
}