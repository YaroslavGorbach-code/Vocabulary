package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseCategoryFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExercisesWithFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler.ExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler.TrainingsListAdapter

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

    private val trainingsAdapter = TrainingsListAdapter()

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

        binding.training.trainings.apply {
            adapter = trainingsAdapter
            layoutManager = LinearLayoutManager(
                binding.root.context, LinearLayoutManager.HORIZONTAL, false
            )
        }
    }

    private fun initActions() {
        binding.training.item.setOnClickListener { callback.onTraining() }

        binding.chipAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.ALL)
            }
        }

        binding.chipCommunication.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.COMMUNICATION)
            }
        }

        binding.chipVocabulary.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.VOCABULARY)
            }
        }

        binding.chipDiction.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.DICTION_AND_ARTICULATION)
            }
        }

        binding.chipFavorite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.FAVORITE)
            }
        }

        binding.chipSenseOfHumor.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                callback.onExercisesFilterChanged(ExerciseCategoryFilterUi.SENSE_OF_HUMOR)
            }
        }
    }

    fun setExercisesWithFilter(exercisesWithFilterUi: ExercisesWithFilterUi) {
        initFilterChips(exercisesWithFilterUi)

        showEmptyFavoritesIcon(exercisesWithFilterUi.areFavoritesEmpty)

        setExercisesListData(exercisesWithFilterUi)
    }

    fun setTrainings(trainings: List<TrainingUi>) {
        trainingsAdapter.setData(trainings)
    }

    private fun setExercisesListData(exercisesWithFilterUi: ExercisesWithFilterUi) {
        listAdapter.setData(exercisesWithFilterUi.exercisesUi)
    }

    private fun initFilterChips(exercisesWithFilterUi: ExercisesWithFilterUi) {
        when (exercisesWithFilterUi.filterUi) {
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
            ExerciseCategoryFilterUi.DICTION_AND_ARTICULATION -> {
                binding.chipDiction.isChecked = true
            }
            ExerciseCategoryFilterUi.SENSE_OF_HUMOR -> {
                binding.chipSenseOfHumor.isCheckable = true
            }
        }
    }

    private fun showEmptyFavoritesIcon(isShow: Boolean) {
        if (isShow) {
            binding.emptyFavorites.root.visibility = View.VISIBLE
            binding.exercisesList.visibility = View.GONE
        } else {
            binding.emptyFavorites.root.visibility = View.GONE
            binding.exercisesList.visibility = View.VISIBLE
        }
    }
}