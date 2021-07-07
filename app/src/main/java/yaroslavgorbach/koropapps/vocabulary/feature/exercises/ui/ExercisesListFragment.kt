package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.Exercise
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.util.host

class ExercisesListFragment : Fragment(R.layout.fragment_exercises_list) {
    interface Router{
        fun openDescription(exercise: Exercise)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<ExercisesListVm>()

        // init view
        val v = ExercisesListView(
            FragmentExercisesListBinding.bind(view), object : ExercisesListView.Callback {
                override fun onExercise(exercise: Exercise) {
                    host<Router>().openDescription(exercise)
                }
            })

        lifecycleScope.launch {
            vm.getExercises().collect(v::setExercises)
        }
    }
}