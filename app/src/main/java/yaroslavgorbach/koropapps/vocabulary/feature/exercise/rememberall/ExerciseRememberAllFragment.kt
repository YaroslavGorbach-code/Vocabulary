package yaroslavgorbach.koropapps.vocabulary.feature.exercise.rememberall

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseRememberAllBinding

class ExerciseRememberAllFragment : Fragment(R.layout.fragment_exercise_remember_all) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseRememberAllVm>()

        //init v
        val v = ExerciseRememberAllView(
            FragmentExerciseRememberAllBinding.bind(view),
            object : ExerciseRememberAllView.Callback {
                override fun onNext() {
                    vm.generateText()
                }
            })

        vm.getText().observe(viewLifecycleOwner, v::setText)
    }
}