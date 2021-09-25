package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.graphics.Color
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

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

    @Parcelize
    class LightBlue(
        override val res: @RawValue ThemeRes = ThemeResLightBlue(),
        override val colorPrimary: Int = Color.parseColor("#81d4fa"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Green(
        override val res: @RawValue ThemeRes = ThemeResGreen(),
        override val colorPrimary: Int = Color.parseColor("#66bb6a"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class LightGreen(
        override val res: @RawValue ThemeRes = ThemeResLightGreen(),
        override val colorPrimary: Int = Color.parseColor("#9ccc65"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Lime(
        override val res: @RawValue ThemeRes = ThemeResLime(),
        override val colorPrimary: Int = Color.parseColor("#d4e157"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Yellow(
        override val res: @RawValue ThemeRes = ThemeResYellow(),
        override val colorPrimary: Int = Color.parseColor("#ffee58"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Amber(
        override val res: @RawValue ThemeRes = ThemeResAmber(),
        override val colorPrimary: Int = Color.parseColor("#ffca28"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Orange(
        override val res: @RawValue ThemeRes = ThemeResOrange(),
        override val colorPrimary: Int = Color.parseColor("#ff9800"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class DeepOrange(
        override val res: @RawValue ThemeRes = ThemeResDeepOrange(),
        override val colorPrimary: Int = Color.parseColor("#ff5722"),
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
        class Brown(
        override val res: @RawValue ThemeRes = ThemeResBrown(),
        override val colorPrimary: Int = Color.parseColor("#8d6e63"),
        override var isCurrent: Boolean = false
    ) : Theme()
}