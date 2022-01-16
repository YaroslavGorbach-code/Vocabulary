package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler.TrainingExercisesListAdapter

class TrainingView(
    private val binding: FragmentTrainingBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(withExercises: TrainingExerciseUi)
        fun onBack()
    }

    private val adapter = TrainingExercisesListAdapter { exercise ->
        callback.onExercise(exercise)
    }

    init {
        initRecycler()
        initActions()
    }

    @SuppressLint("SetTextI18n")
    fun setTrainingWitExercises(trainingWithExercisesUi: TrainingWithExercisesUi) {
        adapter.setData(trainingWithExercisesUi.exercises)
        showNoExercisesView(trainingWithExercisesUi.exercises.isEmpty())
    }

    private fun initRecycler() {
        val divider =
            MaterialDividerItemDecoration(binding.root.context, LinearLayoutManager.VERTICAL)
        divider.dividerInsetStart = 160
        binding.list.addItemDecoration(divider)
        binding.list.adapter = adapter
    }

    private fun initActions() {
        binding.toolbar.setNavigationOnClickListener {
            callback.onBack()
        }

        binding.noExercisesIcon.setOnClickListener {
            callback.onBack()
        }
    }

    private fun showNoExercisesView(isShow: Boolean) {
        if (isShow) {
            binding.noExercisesIcon.visibility = View.VISIBLE
            binding.noExercisesTextOne.visibility = View.VISIBLE
            binding.noExercisesTextTwo.visibility = View.VISIBLE
        } else {
            binding.noExercisesIcon.visibility = View.GONE
            binding.noExercisesTextOne.visibility = View.GONE
            binding.noExercisesTextTwo.visibility = View.GONE
        }
    }
}