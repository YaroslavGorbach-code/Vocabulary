package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import android.graphics.drawable.Drawable
import yaroslavgorbach.koropapps.vocabulary.data.description.local.model.Description
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.util.getString

class DescriptionView(
    private val binding: FragmentDescriptionBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onOpenExercise()
    }

    init {
        binding.openExercise.setOnClickListener {
            callback.onOpenExercise()
        }
    }

    fun setDescription(description: Description) {
        binding.text.text = binding.getString(description.text)
    }

    fun setExName(string: String) {
        binding.toolbar.title = string
    }

    fun setExerciseIcon(drawable: Drawable?) {
        binding.icon.setImageDrawable(drawable)
    }
}