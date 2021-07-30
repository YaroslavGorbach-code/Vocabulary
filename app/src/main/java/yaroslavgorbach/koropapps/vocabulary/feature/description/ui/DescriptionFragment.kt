package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.coroutines.FlowPreview
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.util.host

@FlowPreview
class DescriptionFragment : Fragment(R.layout.fragment_description) {

    interface Router {
        fun openExercise(exerciseName: ExerciseName)
    }

    companion object {
        fun newInstance(exerciseName: ExerciseName) = DescriptionFragment().apply {
            arguments = bundleOf(
                "exerciseName" to exerciseName
            )
        }
    }

    private val exName: ExerciseName
        get() = requireArguments()["exerciseName"] as ExerciseName

    private val viewModel by viewModels<DescriptionVm>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val descriptionView = DescriptionView(
            FragmentDescriptionBinding.bind(requireView()),
            object : DescriptionView.Callback {
                override fun onOpenExercise() {
                    host<Router>().openExercise(exName)
                }
            })

        descriptionView.setDescription(viewModel.getDescription(exName))
        descriptionView.setExName(requireContext().getString(exName.id))
    }
}