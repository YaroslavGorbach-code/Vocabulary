package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.databinding.DialogTrainingIsOverBinding
import yaroslavgorbach.koropapps.vocabulary.utils.host

class TrainingIsOverDialog : DialogFragment() {

    fun interface DismissListener {
        fun onDismiss()
    }

    companion object {
        fun newInstance(): TrainingIsOverDialog {
            return TrainingIsOverDialog()
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogTrainingIsOverBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .create()

        binding.ok.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        host<DismissListener>().onDismiss()
    }
}