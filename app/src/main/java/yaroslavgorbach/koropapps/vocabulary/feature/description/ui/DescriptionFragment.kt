package yaroslavgorbach.koropapps.vocabulary.feature.description.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding

@FlowPreview
class DescriptionFragment : Fragment(R.layout.fragment_description) {

    companion object {
        fun getInstance(exerciseName: ExerciseName): DescriptionFragment {
            return DescriptionFragment().apply {
                arguments = bundleOf("exerciseName" to exerciseName)
            }
        }
        private val DescriptionFragment.exName: ExerciseName
        get() = requireArguments()["exerciseName"] as ExerciseName

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init vm

        val vm by viewModels<DescriptionVm>()
        // init view

        val v = DescriptionView(FragmentDescriptionBinding.bind(view))
        lifecycleScope.launch {
            v.setDescription(vm.getDescription(exName))
        }
    }
}