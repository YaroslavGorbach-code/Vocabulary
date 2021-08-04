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
        fun onBack()
    }

    init {
        initActions()
    }

    private fun initActions() {
        binding.openExercise.setOnClickListener {
            callback.onOpenExercise()
        }
        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }
    }

    fun setDescription(descriptionLocal: DescriptionLocal) {
        binding.descriptionText.text = binding.getString(descriptionLocal.descriptionTextRes)
        with(binding.getDrawable(descriptionLocal.exerciseIconRes)) {
            binding.icon1.setImageDrawable(this)
            binding.icon2.setImageDrawable(this)
            binding.icon3.setImageDrawable(this)
            binding.icon4.setImageDrawable(this)
            binding.icon5.setImageDrawable(this)
        }
    }

    fun setExName(string: String) {
        binding.toolbar.title = string
    }

}