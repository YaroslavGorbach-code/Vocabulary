package yaroslavgorbach.koropapps.vocabulary.feature.exercise.narrator

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseNarratorBinding


class ExerciseNarratorFragment : Fragment(R.layout.fragment_exercise_narrator) {

    companion object {
        fun getInstance(exerciseName: ExerciseName): ExerciseNarratorFragment {
            return ExerciseNarratorFragment().apply {
                arguments = bundleOf("exerciseName" to exerciseName)
            }
        }

        private val ExerciseNarratorFragment.exName: ExerciseName
            get() = requireArguments()["exerciseName"] as ExerciseName
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init vm
        val vm by viewModels<ExerciseNarratorVm>()

        //init v
        val v = ExerciseNarratorView(
            FragmentExerciseNarratorBinding.bind(view),
            object : ExerciseNarratorView.Callback {
                override fun onNext() {
                    vm.generateNumberOfWords()
                }
            })

        vm.getNumberOfWords().observe(viewLifecycleOwner) {
            v.setLetter(it.toString())
        }
        v.setDescriptionText(vm.getDescriptionText(exName))
    }
}