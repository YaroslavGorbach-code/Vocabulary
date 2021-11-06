package yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.factory

import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.model.AboutAppPageUi

class AboutAppPagesFactory {
    fun create(): List<AboutAppPageUi> {
        return listOf(
            AboutAppPageUi(
                imageRes = R.drawable.ic_lamp,
                titleRes = R.string.page_one_title,
                messageRes = R.string.page_one_text
            ),
            AboutAppPageUi(
                imageRes = R.drawable.ic_target,
                titleRes = R.string.page_two_title,
                messageRes = R.string.page_two_text
            ),
            AboutAppPageUi(
                imageRes = R.drawable.ic_sign,
                titleRes = R.string.page_three_title,
                messageRes = R.string.page_three_text
            ),
            AboutAppPageUi(
                imageRes = R.drawable.ic_rocket,
                titleRes = R.string.page_four_title,
                messageRes = R.string.page_four_text,
                isStartButtonVisible = true
            )
        )
    }
}