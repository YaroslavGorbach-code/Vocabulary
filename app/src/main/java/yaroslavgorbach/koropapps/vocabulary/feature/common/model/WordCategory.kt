package yaroslavgorbach.koropapps.vocabulary.feature.common.model

import yaroslavgorbach.koropapps.vocabulary.R

sealed class WordCategory {

    abstract val resArray: Int

    class Letters(override val resArray: Int = R.array.letters) : WordCategory()

    class Fillings(override val resArray: Int = R.array.fillings) : WordCategory()

    class NounsNotAlive(override val resArray: Int = R.array.nouns_not_alive) : WordCategory()

    class NounsAlive(override val resArray: Int = R.array.nouns_alive) : WordCategory()

    class Category(override val resArray: Int = R.array.categories) : WordCategory()

    class Abbreviations(override val resArray: Int = R.array.abbreviations) : WordCategory()

    class Terms(override val resArray: Int = R.array.terms) : WordCategory()

    class Questions(override val resArray: Int = R.array.questions) : WordCategory()

    class TongueTwistersEasy(override val resArray: Int = R.array.twisters_easy) : WordCategory()

    class TongueTwistersHard(override val resArray: Int = R.array.twisters_hard) : WordCategory()

    class TongueTwistersVeryHard(override val resArray: Int = R.array.twisters_very_hard) : WordCategory()

    class None(override val resArray: Int = R.array.empty_array) : WordCategory()
}