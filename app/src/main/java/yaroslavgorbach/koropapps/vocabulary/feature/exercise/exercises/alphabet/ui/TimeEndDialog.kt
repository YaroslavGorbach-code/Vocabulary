package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.util.host

class TimeEndDialog : DialogFragment() {

    interface Host {
        fun onDialogCancel()
    }

    companion object {
        const val NUMBER_OF_LETTERS_ARG = "NUMBER_OF_LETTERS_ARG"
        const val DEFAULT_ARG = 0
        fun newInstance(numberOfWords: Int) = TimeEndDialog().apply {
            arguments = bundleOf(
                NUMBER_OF_LETTERS_ARG to numberOfWords
            )
        }

    }

    private val numberOfLetters: Int
        get() = requireArguments().getInt(NUMBER_OF_LETTERS_ARG, DEFAULT_ARG)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.end_of_time)
            .setMessage(getString(R.string.result, numberOfLetters))
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        host<Host>().onDialogCancel()
    }
}