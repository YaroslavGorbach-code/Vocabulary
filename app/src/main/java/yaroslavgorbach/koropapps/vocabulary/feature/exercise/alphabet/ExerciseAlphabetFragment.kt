package yaroslavgorbach.koropapps.vocabulary.feature.exercise.alphabet

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseAlphabetBinding

class ExerciseAlphabetFragment : Fragment(R.layout.fragment_exercise_alphabet) {

    companion object{
        fun getInstance(exerciseName: ExerciseName): ExerciseAlphabetFragment{
            return ExerciseAlphabetFragment().apply {
                arguments = bundleOf("exerciseName" to exerciseName)
            }
        }

        private val ExerciseAlphabetFragment.exName: ExerciseName
            get() = requireArguments()["exerciseName"] as ExerciseName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseAlphabetVm>()

        // init view
        val v = ExerciseAlphabetView(
            FragmentExerciseAlphabetBinding.bind(view),
            object : ExerciseAlphabetView.Callback {
                override fun onNewLetter() {
                    lifecycleScope.launch {
                        vm.setNewLetter()
                    }
                }
            })

        vm.getLetter().observe(viewLifecycleOwner){letter->
            v.setLetter(letter)
        }
    }
}