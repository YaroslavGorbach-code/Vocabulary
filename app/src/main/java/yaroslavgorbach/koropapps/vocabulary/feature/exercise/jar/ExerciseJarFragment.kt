package yaroslavgorbach.koropapps.vocabulary.feature.exercise.jar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseJarBinding

class ExerciseJarFragment : Fragment(R.layout.fragment_exercise_jar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseJarVm>()

        // init v
        val v = ExerciseJarView(
            FragmentExerciseJarBinding.bind(view),
            object : ExerciseJarView.Callback {
                override fun onNext() {
                    vm.generateText()
                }
            })
        vm.getText().observe(viewLifecycleOwner, v::setText)
    }
}