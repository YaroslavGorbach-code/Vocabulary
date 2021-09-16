package yaroslavgorbach.koropapps.vocabulary.data.settings.local.model

sealed class Theme {
    abstract val res: Int

    class TestFirst(override val res: Int = TODO("set theme id here")) : Theme()

    class TestSecond(override val res: Int = TODO("set theme id here")) : Theme()
}