package yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(val id: Long, @StringRes val name: Int, @DrawableRes val icon: Int)
