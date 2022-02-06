package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.common.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.DialogExerciseTimeIsOverBinding
import yaroslavgorbach.koropapps.vocabulary.utils.host

class ExerciseTimeIsOverDialog : DialogFragment() {

    interface Host {
        fun onTimeEndDialogCancel()
    }

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"
        private const val ARG_MESSAGE = "ARG_MESSAGE"
        const val DEFAULT_ARG = ""

        fun newInstance(title: String, message: String) = ExerciseTimeIsOverDialog().apply {
            arguments = bundleOf(
                ARG_TITLE to title,
                ARG_MESSAGE to message
            )
        }
    }

    private val title: String
        get() = requireArguments().getString(ARG_TITLE, DEFAULT_ARG)

    private val message: String
        get() = requireArguments().getString(ARG_MESSAGE, DEFAULT_ARG)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogExerciseTimeIsOverBinding.inflate(LayoutInflater.from(requireContext()))
        binding.title.text = title
        binding.message.text = message
        binding.button.setOnClickListener {
            host<Host>().onTimeEndDialogCancel()
        }

        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        host<Host>().onTimeEndDialogCancel()
    }
}