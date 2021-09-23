package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.graphics.Color
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

// TODO: 9/16/2021 add themes
sealed class Theme : Parcelable {
    abstract val res: ThemeRes

    abstract var isCurrent: Boolean

    abstract val colorPrimary: Int

    @Parcelize
    class Teal(
        override val res: @RawValue ThemeRes = ThemeResTeal(),
        override val colorPrimary: Int = Color.parseColor("#4db6ac"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Blue(
        override val res: @RawValue ThemeRes = ThemeResBlue(),
        override val colorPrimary: Int = Color.parseColor("#64b5f6"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Indigo(
        override val res: @RawValue ThemeRes = ThemeResIndigo(),
        override val colorPrimary: Int = Color.parseColor("#5c6bc0"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Purple(
        override val res: @RawValue ThemeRes = ThemeResPurple(),
        override val colorPrimary: Int = Color.parseColor("#7e57c2"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class DeepPurple(
        override val res: @RawValue ThemeRes = ThemeResDeepPurple(),
        override val colorPrimary: Int = Color.parseColor("#ba68c8"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Pink(
        override val res: @RawValue ThemeRes = ThemeResPink(),
        override val colorPrimary: Int = Color.parseColor("#f06292"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Red(
        override val res: @RawValue ThemeRes = ThemeResRed(),
        override val colorPrimary: Int = Color.parseColor("#ef5350"),
        override var isCurrent: Boolean = false
    ) : Theme()
}