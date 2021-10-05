package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseCategoryFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler.ExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.dayOfWeek

class ExercisesListView(
    private val binding: FragmentExercisesListBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: ExerciseUi)
        fun onTraining()
        fun onExercisesFilterChanged(filterUi: ExerciseCategoryFilterUi)
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

        binding.chipAll.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.ALL)
            }
        }

        binding.chipCommunication.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.COMMUNICATION)
            }
        }

        binding.chipVocabulary.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.VOCABULARY)
            }
        }

        binding.chipFavorite.setOnCheckedChangeListener { chip, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.FAVORITE)
            }
        }
    }

    fun setExercises(list: List<ExerciseUi>) {
        listAdapter.setData(list)
    }

    fun setExercisesFilter(filter: ExerciseCategoryFilterUi) {

        when (filter) {
            ExerciseCategoryFilterUi.ALL -> {
                binding.chipAll.isChecked = true
            }
            ExerciseCategoryFilterUi.VOCABULARY -> {
                binding.chipVocabulary.isChecked = true
            }
            ExerciseCategoryFilterUi.COMMUNICATION -> {
                binding.chipCommunication.isChecked = true
            }
            ExerciseCategoryFilterUi.FAVORITE -> {
                binding.chipFavorite.isChecked = true
            }
        }
    }

    fun setTraining(trainingUi: TrainingUi) {
        with(binding.training.days) {

            trainingUi.first?.let { trainingWithExercises ->
                day1.setProgress(trainingWithExercises.progress)
                day1.setText(trainingWithExercises.training.date.dayOfWeek())
            }

            trainingUi.second?.let { trainingWithExercises ->
                day2.setProgress(trainingWithExercises.progress)
                day2.setText(trainingWithExercises.training.date.dayOfWeek())
            }

            trainingUi.third?.let { trainingWithExercises ->
                day3.setProgress(trainingWithExercises.progress)
                day3.setText(trainingWithExercises.training.date.dayOfWeek())
            }

            trainingUi.fourth?.let { trainingWithExercises ->
                day4.setProgress(trainingWithExercises.progress)
                day4.setText(trainingWithExercises.training.date.dayOfWeek())
            }

            trainingUi.fifth?.let { trainingWithExercises ->
                day5.setProgress(trainingWithExercises.progress)
                day5.setText(trainingWithExercises.training.date.dayOfWeek())
            }
        }
    }
}