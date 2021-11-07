package yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui

import android.util.Log
import android.view.View
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExercisesListBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.LineDecorator
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseCategoryFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.ExercisesWithFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.model.TrainingUi
import yaroslavgorbach.koropapps.vocabulary.feature.exerciseslist.ui.recycler.ExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.dayOfWeek

class ExercisesListView(
    private val binding: FragmentExercisesListBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(exercise: ExerciseUi)
        fun onScrolled(y: Int)
        fun onTraining()
        fun onExercisesFilterChanged(filterUi: ExerciseCategoryFilterUi)
    }

    private val listAdapter = ExercisesListAdapter(callback::onExercise)

    init {
        initView()
        initActions()
    }

    fun setExercisesWithFilter(exercisesWithFilterUi: ExercisesWithFilterUi) {
        initFilterChips(exercisesWithFilterUi)

        showEmptyFavoritesIcon(exercisesWithFilterUi.areFavoritesEmpty)

        setExercisesListData(exercisesWithFilterUi)
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

    fun setChipsVisibility(isVisible: Boolean) {
        binding.exercisesList.stopScroll()
        if (isVisible){
            binding.chips.visibility = View.VISIBLE
        }else{
            binding.chips.visibility = View.GONE
        }
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

        binding.exercisesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                callback.onScrolled(dy)
                super.onScrolled(recyclerView, dx, dy)
            }
        })
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
        }
    }

    private fun showEmptyFavoritesIcon(isShow: Boolean) {
        if (isShow) {
            binding.emptyFavorites.root.visibility = View.VISIBLE
        } else {
            binding.emptyFavorites.root.visibility = View.GONE
        }
    }
}