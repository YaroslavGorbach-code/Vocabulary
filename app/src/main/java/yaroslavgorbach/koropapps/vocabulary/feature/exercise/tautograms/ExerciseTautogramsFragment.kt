package yaroslavgorbach.koropapps.vocabulary.feature.exercise.tautograms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTautogramsExerciseBinding

class ExerciseTautogramsFragment : Fragment(R.layout.fragment_tautograms_exercise) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseTautogramsVm>()

        // init view
        val v = ExerciseTautogramsView(
            FragmentTautogramsExerciseBinding.bind(view),
            object : ExerciseTautogramsView.Callback {
                override fun onNextLetter() {
                    vm.setRandomLetter()
                }
            })

        vm.getLetter().observe(viewLifecycleOwner, v::setLetter)
    }
}