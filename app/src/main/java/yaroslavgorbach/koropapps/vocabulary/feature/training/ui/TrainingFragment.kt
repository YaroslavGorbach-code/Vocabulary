package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.alphabet.ui.ExerciseAlphabetFragment

class TrainingFragment : Fragment(R.layout.fragment_training) {

    companion object {
        fun newInstance() = TrainingFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}