package yaroslavgorbach.koropapps.vocabulary.feature.training.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.App
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentTrainingBinding
import yaroslavgorbach.koropapps.vocabulary.feature.training.model.TrainingExerciseUi
import yaroslavgorbach.koropapps.vocabulary.feature.training.presentation.TrainingViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.host
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import yaroslavgorbach.koropapps.vocabulary.utils.setBackgroundStatusBarColor
import yaroslavgorbach.koropapps.vocabulary.utils.setDefaultStatusBarColor
import javax.inject.Inject

@InternalCoroutinesApi
@FlowPreview
class TrainingFragment : Fragment(R.layout.fragment_training),
    TrainingIsOverDialog.DismissListener {

    interface Router {
        fun openDescription(exercise: TrainingExerciseUi)
    }

    companion object {
        fun newInstance() = TrainingFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TrainingViewModel> { viewModelFactory }

    private lateinit var trainingView: TrainingView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    private fun initDagger() {
        (requireActivity().application as App).appComponent
            .trainingComponent()
            .create()
            .inject(this)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().setBackgroundStatusBarColor()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().setDefaultStatusBarColor()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObservers()
    }

    private fun initObservers() {
        viewModel.getCurrentTrainingWithExercises()

        viewModel.trainingWithExercises.observe(
            viewLifecycleOwner,
            trainingView::setTrainingWitExercises
        )
    }

    private fun initView(view: View) {
        trainingView = TrainingView(
            FragmentTrainingBinding.bind(view),
            object : TrainingView.Callback {
                override fun onExercise(withExercises: TrainingExerciseUi) {
                    host<Router>().openDescription(withExercises)
                }

                override fun onShowTrainingIsFinishedDialog() {
                    showTrainingIsFinishedDialog()
                }

                override fun onBack() {
                    onBackPressed()
                }
            })
    }

    private fun showTrainingIsFinishedDialog() {
        TrainingIsOverDialog.newInstance().show(childFragmentManager, null)
    }

    override fun onDismiss() {
        onBackPressed()
    }
}