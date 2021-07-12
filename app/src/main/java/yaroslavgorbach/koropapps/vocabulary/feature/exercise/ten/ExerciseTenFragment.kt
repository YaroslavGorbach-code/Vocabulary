package yaroslavgorbach.koropapps.vocabulary.feature.exercise.ten

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseTenBinding

class ExerciseTenFragment: Fragment(R.layout.fragment_exercise_ten) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExerciseTenVm>()

        // init v
        val v = ExerciseTenView(FragmentExerciseTenBinding.bind(view), object : ExerciseTenView.Callback{
            override fun onNext() {
                vm.generateText()
            }
        })

        vm.getText().observe(viewLifecycleOwner, v::setText)
    }
}