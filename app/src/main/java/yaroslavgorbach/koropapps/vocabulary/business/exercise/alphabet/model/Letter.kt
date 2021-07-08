package yaroslavgorbach.koropapps.vocabulary.business.exercise.alphabet.model

import androidx.annotation.StringRes

data class Letter(@StringRes val letter: Int, var consumed: Boolean = false)
