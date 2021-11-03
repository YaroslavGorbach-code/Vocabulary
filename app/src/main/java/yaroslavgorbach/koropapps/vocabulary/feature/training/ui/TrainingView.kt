package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.CompoundButton
import androidx.core.view.isVisible
import com.google.android.material.chip.ChipGroup
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseCategoryFilterUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler.TrainingExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.colorBackground
import yaroslavgorbach.koropapps.vocabulary.utils.getColorPrimary
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class TrainingView(
    private val binding: FragmentTrainingBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(withExercises: TrainingExerciseUi)

        fun onPageChanged(page: Int)

        fun onFilterChanged(filterUi: TrainingExerciseCategoryFilterUi)

        fun onBack()
    }

    private val pagerAdapter = TrainingExercisesListAdapter { exercise ->
        callback.onExercise(exercise)

        callback.onPageChanged(binding.viewPager.currentItem)
    }

    init {
        initView()
        initActions()
    }

    private fun initView() {
        binding.viewPager.apply {
            adapter = pagerAdapter
        }
    }

    private fun initActions() {
        binding.icClose.setOnClickListener {
            callback.onBack()
        }

        binding.noExercisesIcon.setOnClickListener {
            callback.onBack()
        }
    }

    private fun showNoExercises(isShow: Boolean) {
        if (isShow) {
            binding.noExercisesIcon.visibility = View.VISIBLE
            binding.noExercisesTextOne.visibility = View.VISIBLE
            binding.noExercisesTextTwo.visibility = View.VISIBLE
            binding.textProgress.visibility = View.GONE
        } else {
            binding.noExercisesIcon.visibility = View.GONE
            binding.noExercisesTextOne.visibility = View.GONE
            binding.noExercisesTextTwo.visibility = View.GONE
            binding.textProgress.visibility = View.VISIBLE
        }
    }

    private fun initFilterChips(
        availableFilters: List<TrainingExerciseCategoryFilterUi>,
    ) {

        if (availableFilters.contains(TrainingExerciseCategoryFilterUi.VOCABULARY)) {
            binding.chipVocabulary.visibility = View.VISIBLE
        } else {
            binding.chipVocabulary.visibility = View.GONE
        }

        if (availableFilters.contains(TrainingExerciseCategoryFilterUi.COMMUNICATION)) {
            binding.chipCommunication.visibility = View.VISIBLE
        } else {
            binding.chipCommunication.visibility = View.GONE
        }

        if (availableFilters.contains(TrainingExerciseCategoryFilterUi.ALL)) {
            binding.chipAll.visibility = View.VISIBLE
        } else {
            binding.chipAll.visibility = View.GONE
        }

        binding.chipAll.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) callback.onFilterChanged(TrainingExerciseCategoryFilterUi.ALL)
        }

        binding.chipCommunication.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) callback.onFilterChanged(TrainingExerciseCategoryFilterUi.COMMUNICATION)
        }

        binding.chipVocabulary.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) callback.onFilterChanged(TrainingExerciseCategoryFilterUi.VOCABULARY)
        }

    }

    private fun setViewPagerPage(page: Int) {
        binding.viewPager.setCurrentItem(page, false)
    }

    @SuppressLint("SetTextI18n")
    fun setTrainingWitExercises(trainingWithExercisesUi: TrainingWithExercisesUi) {
        pagerAdapter.setData(trainingWithExercisesUi.exercises)

        setViewPagerPage(trainingWithExercisesUi.currentViewPagerPage)

        binding.textProgress.setProgress(trainingWithExercisesUi.progress)

        binding.textProgress.setText(
            binding.getString(R.string.days_without_interruption) + ": ${trainingWithExercisesUi.daysWithoutInterruption}"
        )

        initFilterChips(trainingWithExercisesUi.availableFilters)

        showNoExercises(trainingWithExercisesUi.exercises.isEmpty())
    }

}