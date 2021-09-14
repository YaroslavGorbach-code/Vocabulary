package yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.dictionary.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentExerciseDictionaryBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.model.ExerciseType
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.exercises.dictionary.presentation.DictionaryViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import javax.inject.Inject

class DictionaryFragment : Fragment(R.layout.fragment_exercise_dictionary) {

    companion object {
        private const val ARG_EXERCISE_TYPE = "ARG_EXERCISE_TYPE"
        fun newInstance(exerciseType: ExerciseType) = DictionaryFragment().apply {
            arguments = bundleOf(
                ARG_EXERCISE_TYPE to exerciseType
            )
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DictionaryViewModel> { viewModelFactory }

    private lateinit var dictionaryView: DictionaryView

    private val exerciseType: ExerciseType
        get() = requireArguments()[ARG_EXERCISE_TYPE] as ExerciseType

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
    }

    private fun initDagger() {
        appComponent()
            .dictionaryComponent()
            .create(exerciseType)
            .inject(this)
    }

    private fun initView() {
        dictionaryView = DictionaryView(
            FragmentExerciseDictionaryBinding.bind(requireView()),
            object : DictionaryView.Callback {
                override fun onClick() {
                    viewModel.onNextClick()
                }

                override fun onTimeEnd() {
                    //   showTimeEndDialog()
                    viewModel.onTimerFinished()
                }

                override fun onBack() {
                    onBackPressed()
                }
            })

        dictionaryView.setDescriptionText(viewModel.description)
        dictionaryView.setExerciseName(exerciseType.getExerciseName())
    }

//    private fun showTimeEndDialog() {
//        TimeEndDialog.newInstance(viewModel.numberOnNextCLicked)
//            .show(childFragmentManager, null)
//    }

    private fun initObservers() {
        viewModel.timerState.observe(viewLifecycleOwner, dictionaryView::setTimerState)
        viewModel.numberOfWords.observe(viewLifecycleOwner, dictionaryView::setNumberOfClicked)
    }

//    override fun onDialogCancel() {
//        requireActivity().onBackPressed()
//    }
}