package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseIconColorMapper
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.StatisticItemUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.presentation.DescriptionViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.*
import javax.inject.Inject

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    interface Router {
        fun onOpenExercise(exerciseType: ExerciseType)
    }

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = DescriptionFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DescriptionViewModel> { viewModelFactory }

    private lateinit var descriptionView: DescriptionView

    private val exerciseType: ExerciseType
        get() = (requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        requireActivity().setStatusBarColor(
            ExerciseNameToExerciseIconColorMapper(requireContext()).map(
                exerciseType.getExerciseName()
            )
        )
    }

    override fun onPause() {
        super.onPause()
        requireActivity().setDefaultStatusBarColor()
    }

    private fun initDagger() {
        appComponent()
            .descriptionComponent()
            .create(exerciseType.getExerciseName())
            .inject(this)
    }

    private fun initView() {
        descriptionView = DescriptionView(
            FragmentDescriptionBinding.bind(requireView()),
            object : DescriptionView.Callback {
                override fun onOpenExercise() {
                    host<Router>().onOpenExercise(exerciseType)
                }

                override fun onBack() {
                    onBackPressed()
                }

                override fun onAddToFavorite() {
                    viewModel.changeExerciseFavorite()
                }

                override fun onChangeDescriptionState() {
                    viewModel.changeDescriptionState()
                }

                override fun onStatisticItemChosen(statisticItemUi: StatisticItemUi) {
                    viewModel.choseStatisticItem(statisticItemUi)
                }
            })

        descriptionView.setDescriptionRes(viewModel.descriptionRes)

        descriptionView.setExerciseIconRes(viewModel.exerciseIconRes)

        descriptionView.setExerciseNameRes(exerciseType.getExerciseName().id)

    }

    private fun initObservers() {
        viewModel.statisticItems.observe(viewLifecycleOwner, descriptionView::setStatisticItems)
        viewModel.chosenStatisticItem.observe(viewLifecycleOwner, descriptionView::setCurrentStatisticItem)
        viewModel.isExerciseFavorite.observe(
            viewLifecycleOwner,
            descriptionView::setExerciseFavorite
        )
        viewModel.descriptionState.observe(viewLifecycleOwner, descriptionView::setDescriptionState)
    }

}