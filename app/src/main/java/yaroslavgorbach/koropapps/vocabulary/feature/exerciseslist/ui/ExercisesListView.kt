package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler.ExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.day

class ExercisesListView(
    private val binding: FragmentExercisesListBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: ExerciseUi)
        fun onTraining()
    }

    private val listAdapter = ExercisesListAdapter(callback::onExercise)

    init {
        initView()
        initActions()
    }

    private fun initView() {
        binding.exercisesList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(LineDecorator(this.context, R.drawable.line_devider))
        }
    }

    private fun initActions() {
        binding.training.item.setOnClickListener { callback.onTraining() }
    }

    fun setExercises(list: List<ExerciseUi>) {
        listAdapter.setData(list)
    }

    fun setTraining(trainingUi: TrainingUi) {
        with(binding.training.days) {
            day1.setProgress(trainingUi.first.progress)
            day1.setText(trainingUi.first.date.day())

            day2.setProgress(trainingUi.second.progress)
            day2.setText(trainingUi.second.date.day())

            day3.setProgress(trainingUi.third.progress)
            day3.setText(trainingUi.third.date.day())

            day4.setProgress(trainingUi.fourth.progress)
            day4.setText(trainingUi.fourth.date.day())

            day5.setProgress(trainingUi.fifth.progress)
            day5.setText(trainingUi.fifth.date.day())
        }
    }
}