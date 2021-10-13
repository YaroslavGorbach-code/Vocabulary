package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import yaroslavgorbach.koropapps.vocabulary.R

sealed class Theme : Parcelable {
    abstract val res: ThemeRes

    abstract var isCurrent: Boolean

    abstract val colorPrimaryRes: Int

    @Parcelize
    class Teal(
        override val res: @RawValue ThemeRes = ThemeResTeal(),
        override val colorPrimaryRes: Int = R.color.TealPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Blue(
        override val res: @RawValue ThemeRes = ThemeResBlue(),
        override val colorPrimaryRes: Int = R.color.BluePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Indigo(
        override val res: @RawValue ThemeRes = ThemeResIndigo(),
        override val colorPrimaryRes: Int = R.color.IndigoPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Purple(
        override val res: @RawValue ThemeRes = ThemeResPurple(),
        override val colorPrimaryRes: Int = R.color.PurplePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class DeepPurple(
        override val res: @RawValue ThemeRes = ThemeResDeepPurple(),
        override val colorPrimaryRes: Int = R.color.DeepPurplePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Pink(
        override val res: @RawValue ThemeRes = ThemeResPink(),
        override val colorPrimaryRes: Int = R.color.PinkPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Red(
        override val res: @RawValue ThemeRes = ThemeResRed(),
        override val colorPrimaryRes: Int = R.color.RedPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class LightBlue(
        override val res: @RawValue ThemeRes = ThemeResLightBlue(),
        override val colorPrimaryRes: Int = R.color.LightBluePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Green(
        override val res: @RawValue ThemeRes = ThemeResGreen(),
        override val colorPrimaryRes: Int = R.color.GreenPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class LightGreen(
        override val res: @RawValue ThemeRes = ThemeResLightGreen(),
        override val colorPrimaryRes: Int = R.color.LightGreenPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Lime(
        override val res: @RawValue ThemeRes = ThemeResLime(),
        override val colorPrimaryRes: Int = R.color.LimePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Yellow(
        override val res: @RawValue ThemeRes = ThemeResYellow(),
        override val colorPrimaryRes: Int = R.color.YellowPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Amber(
        override val res: @RawValue ThemeRes = ThemeResAmber(),
        override val colorPrimaryRes: Int = R.color.AmberPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Orange(
        override val res: @RawValue ThemeRes = ThemeResOrange(),
        override val colorPrimaryRes: Int = R.color.OrangePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class DeepOrange(
        override val res: @RawValue ThemeRes = ThemeResDeepOrange(),
        override val colorPrimaryRes: Int = R.color.DeepOrangePrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class Brown(
        override val res: @RawValue ThemeRes = ThemeResBrown(),
        override val colorPrimaryRes: Int = R.color.BrownPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()

    @Parcelize
    class BlueGray(
        override val res: @RawValue ThemeRes = ThemeResBlueGray(),
        override val colorPrimaryRes: Int = R.color.BlueGrayPrimary,
        override var isCurrent: Boolean = false
    ) : Theme()
}