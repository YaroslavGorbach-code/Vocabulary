package yaroslavgorbach.koropapps.vocabulary.feature.exercises.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.ExercisesListFragmentBinding

class ExercisesListFragment: Fragment(R.layout.exercises_list_fragment) {
    private val vm by viewModels<ExercisesListVm>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init view
        val v = ExercisesListView(ExercisesListFragmentBinding.bind(view))
        lifecycleScope.launch {
            vm.getExercises().collect(v::setExercises)
        }
    }
}