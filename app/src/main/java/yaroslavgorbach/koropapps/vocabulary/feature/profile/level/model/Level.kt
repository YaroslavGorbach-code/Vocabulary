package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model

// TODO: 9/8/2021 ad more levels
sealed class Level {
    abstract val nextLevelTrainingTimeRequired: Int
    abstract val level: String

    class ZERO : Level() {
        override val nextLevelTrainingTimeRequired = 10
        override val level: String = "0"
    }

    class First : Level() {
        override val nextLevelTrainingTimeRequired = 20
        override val level: String = "1"
    }

    class Second : Level() {
        override val nextLevelTrainingTimeRequired = 30
        override val level: String = "2"
    }

}
