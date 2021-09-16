package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

// TODO: 9/16/2021 add themes
sealed class Theme {
    abstract val res: Int

    class TestFirst(override val res: Int = 0) : Theme()

    class TestSecond(override val res: Int = 0) : Theme()
}