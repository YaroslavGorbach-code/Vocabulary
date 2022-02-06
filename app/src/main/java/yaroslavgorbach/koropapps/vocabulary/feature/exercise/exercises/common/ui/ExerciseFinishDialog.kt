package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.databinding.DialogExerciseTimeIsOverBinding
import yaroslavgorbach.koropapps.vocabulary.utils.host

class ExerciseFinishDialog : DialogFragment() {

    interface Host {
        fun onDialogCancelled()
    }

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        private const val ARG_ICON_RES = "ARG_ICON_RES"
        const val DEFAULT_ARG = ""

        fun newInstance(title: String, message: String, iconRes: Int) =
            ExerciseFinishDialog().apply {
                arguments = bundleOf(
                    ARG_TITLE to title,
                    ARG_MESSAGE to message,
                    ARG_ICON_RES to iconRes,
                )
            }
    }

    private val title: String
        get() = requireArguments().getString(ARG_TITLE, DEFAULT_ARG)

    private val message: String
        get() = requireArguments().getString(ARG_MESSAGE, DEFAULT_ARG)

    private val icon: Drawable?
        get() = ResourcesCompat.getDrawable(
            resources,
            requireArguments().getInt(ARG_ICON_RES),
            requireActivity().theme
        )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogExerciseTimeIsOverBinding.inflate(LayoutInflater.from(requireContext()))
        binding.title.text = title
        binding.message.text = message
        binding.imageView6.setImageDrawable(icon)

        binding.button.setOnClickListener {
            host<Host>().onDialogCancelled()
        }

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        host<Host>().onDialogCancelled()
    }
}