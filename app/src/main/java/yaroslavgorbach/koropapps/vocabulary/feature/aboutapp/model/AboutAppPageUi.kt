package yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.model

data class AboutAppPageUi(
    val imageRes: Int,
    val titleRes: Int,
    val messageRes: Int,
    val isStartButtonVisible: Boolean = false
)
