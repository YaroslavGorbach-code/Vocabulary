package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model

// TODO: 9/8/2021 ad more levels
sealed class Level {
    abstract val nextLevelTrainingTimeRequired: Int
    abstract val levelTrainingTimeRequired: Int
    abstract val level: String

    class Zero : Level() {
        override val levelTrainingTimeRequired = 0
        override val nextLevelTrainingTimeRequired = 10
        override val level: String = "0"
    }

    class One : Level() {
        override val levelTrainingTimeRequired = 10
        override val nextLevelTrainingTimeRequired = 20
        override val level: String = "1"
    }

    class Two : Level() {
        override val levelTrainingTimeRequired = 20
        override val nextLevelTrainingTimeRequired = 30
        override val level: String = "2"
    }

    class Three : Level() {
        override val levelTrainingTimeRequired = 30
        override val nextLevelTrainingTimeRequired = 40
        override val level: String = "3"
    }

    class Four : Level() {
        override val levelTrainingTimeRequired = 40
        override val nextLevelTrainingTimeRequired = 50
        override val level: String = "4"
    }

    class Fife : Level() {
        override val levelTrainingTimeRequired = 50
        override val nextLevelTrainingTimeRequired = 65
        override val level: String = "5"
    }

    class Six : Level() {
        override val levelTrainingTimeRequired = 65
        override val nextLevelTrainingTimeRequired = 80
        override val level: String = "6"
    }

    class Seven : Level() {
        override val levelTrainingTimeRequired = 80
        override val nextLevelTrainingTimeRequired = 95
        override val level: String = "7"
    }

    class Eight : Level() {
        override val levelTrainingTimeRequired = 95
        override val nextLevelTrainingTimeRequired = 110
        override val level: String = "8"
    }

    class Nine : Level() {
        override val levelTrainingTimeRequired = 110
        override val nextLevelTrainingTimeRequired = 115
        override val level: String = "9"
    }

    class Ten : Level() {
        override val levelTrainingTimeRequired = 115
        override val nextLevelTrainingTimeRequired = 130
        override val level: String = "10"
    }

    class Eleven : Level() {
        override val levelTrainingTimeRequired = 130
        override val nextLevelTrainingTimeRequired = 145
        override val level: String = "11"
    }

    class Twelve : Level() {
        override val levelTrainingTimeRequired = 145
        override val nextLevelTrainingTimeRequired = 160
        override val level: String = "12"
    }

    class Thirteen : Level() {
        override val levelTrainingTimeRequired = 160
        override val nextLevelTrainingTimeRequired = 175
        override val level: String = "13"
    }

    class Fourteen : Level() {
        override val levelTrainingTimeRequired = 175
        override val nextLevelTrainingTimeRequired = 190
        override val level: String = "14"
    }

    class Fifteen : Level() {
        override val levelTrainingTimeRequired = 190
        override val nextLevelTrainingTimeRequired = 210
        override val level: String = "15"
    }

    class Sixteen : Level() {
        override val levelTrainingTimeRequired = 210
        override val nextLevelTrainingTimeRequired = 230
        override val level: String = "16"
    }

    class Seventeen : Level() {
        override val levelTrainingTimeRequired = 230
        override val nextLevelTrainingTimeRequired = 250
        override val level: String = "17"
    }

    class Eighteen : Level() {
        override val levelTrainingTimeRequired = 250
        override val nextLevelTrainingTimeRequired = 270
        override val level: String = "18"
    }

    class Nineteen : Level() {
        override val levelTrainingTimeRequired = 270
        override val nextLevelTrainingTimeRequired = 290
        override val level: String = "19"
    }

    class Twenty : Level() {
        override val levelTrainingTimeRequired = 290
        override val nextLevelTrainingTimeRequired = 220
        override val level: String = "20"
    }
}
