package yaroslavgorbach.koropapps.vocabulary.feature.exercise.associations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAssociationsBinding

class ExerciseAssociationsFragment : Fragment(R.layout.fragment_exercise_associations) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseAssociationsVm>()

        // init view
        val v = ExerciseAssociationsView(
            FragmentExerciseAssociationsBinding.bind(view),
            object : ExerciseAssociationsView.Callback {
                override fun onNext() {
                    vm.generateText()
                }
            })

        vm.getText().observe(viewLifecycleOwner, v::setText)
    }
}