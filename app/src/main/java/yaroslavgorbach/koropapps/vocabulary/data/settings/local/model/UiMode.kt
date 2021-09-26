package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import yaroslavgorbach.koropapps.vocabulary.R


sealed class UiMode : Parcelable {
    abstract val textRes: Int

    abstract val iconRes: Int

    @Parcelize
    class Dark(
        override val textRes: Int = R.string.dark_theme,
        override val iconRes: Int = R.drawable.ic_dark_mode
    ) : UiMode()

    @Parcelize
    class Light(
        override val textRes: Int = R.string.light_theme,
        override val iconRes: Int = R.drawable.ic_light_mode
    ) : UiMode()
}