package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingWithExercisesUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.ui.recycler.TrainingExercisesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class TrainingView(
    private val binding: FragmentTrainingBinding,
    private val callback: Callback
) {

    interface Callback {
        fun onExercise(withExercises: TrainingExerciseUi)

        fun onPageChanged(page: Int)

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
        binding.close.setOnClickListener {
            callback.onBack()
        }
    }

    private fun showNoExercises(isShow: Boolean) {
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

    @SuppressLint("SetTextI18n")
    fun setTrainingWitExercises(trainingWithExercisesUi: TrainingWithExercisesUi) {
        pagerAdapter.setData(trainingWithExercisesUi.exercises)

        setViewPagerPage(trainingWithExercisesUi.currentViewPagerPage)

        binding.trainingProgress.progress = trainingWithExercisesUi.progress

        binding.daysWithoutInterruption.text =
            binding.getString(R.string.days_without_interruption) + ": ${trainingWithExercisesUi.daysWithoutInterruption}"

        showNoExercises(trainingWithExercisesUi.exercises.isEmpty())
    }

    private fun setViewPagerPage(page: Int) {
        binding.viewPager.setCurrentItem(page, false)
    }
}