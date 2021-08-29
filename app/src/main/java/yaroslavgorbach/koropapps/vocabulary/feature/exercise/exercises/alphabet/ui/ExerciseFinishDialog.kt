package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.utils.host

class ExerciseFinishDialog : DialogFragment() {
    interface Host {
        fun onDialogCancel()
    }

    companion object {
        const val WORD_AVERAGE_TIME_ARG = "WORD_AVERAGE_TIME_ARG"
        const val DEFAULT_ARG = 0f
        fun newInstance(averageTime: Float) = ExerciseFinishDialog().apply {
            arguments = bundleOf(
                WORD_AVERAGE_TIME_ARG to averageTime
            )
        }
    }

    private val averageTime: Float
        get() = requireArguments().getFloat(WORD_AVERAGE_TIME_ARG, DEFAULT_ARG)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.finish_of_exercise)
            .setMessage(getString(R.string.average_time_on_word) + " " + averageTime.toString())
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        host<Host>().onDialogCancel()
    }
}