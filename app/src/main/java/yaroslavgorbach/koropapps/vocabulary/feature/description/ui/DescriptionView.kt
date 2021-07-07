package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.util.getString

class DescriptionView(private val binding: FragmentDescriptionBinding) {

    fun setDescription(description: Description) {
        binding.text.text = binding.getString(description.text)
    }
}