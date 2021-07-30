package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.DescriptionLocal
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.util.getDrawable
import yaroslavgorbach.koropapps.vocabulary.util.getString

class DescriptionView(
    private val binding: FragmentDescriptionBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onOpenExercise()
    }

    init {
        initActions()
    }

    private fun initActions() {
        binding.openExercise.setOnClickListener {
            callback.onOpenExercise()
        }
    }

    fun setDescription(descriptionLocal: DescriptionLocal) {
        binding.description.text = binding.getString(descriptionLocal.descriptionTextRes)
        binding.icon.setImageDrawable(binding.getDrawable(descriptionLocal.exerciseIconRes))
    }

    fun setExName(string: String) {
        binding.toolbar.title = string
    }

}