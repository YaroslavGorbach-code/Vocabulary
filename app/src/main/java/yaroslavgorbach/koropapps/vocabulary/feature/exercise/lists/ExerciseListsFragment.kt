package yaroslavgorbach.koropapps.vocabulary.feature.exercise.lists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseListsBinding

class ExerciseListsFragment: Fragment(R.layout.fragment_exercise_lists) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseListsVm>()

        // init v
        val v = ExerciseListsView(FragmentExerciseListsBinding.bind(view), object :ExerciseListsView.Callback{
            override fun onNext() {
                vm.generateText()
            }

        })

        vm.getText().observe(viewLifecycleOwner, v::setText)
    }
}