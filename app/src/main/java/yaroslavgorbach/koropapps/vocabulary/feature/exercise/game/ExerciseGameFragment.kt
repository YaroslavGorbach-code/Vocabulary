package yaroslavgorbach.koropapps.vocabulary.feature.exercise.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseGameBinding

class ExerciseGameFragment : Fragment(R.layout.fragment_exercise_game) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseGameVm>()

        // init v
        val v = ExerciseGameView(
            FragmentExerciseGameBinding.bind(view),
            object : ExerciseGameView.Callback {
                override fun onNext() {
                    vm.generateText()
                }
            })

        vm.getText().observe(viewLifecycleOwner, v::setText)
    }
}