package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentLevelBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation.LevelViewModel
import yaroslavgorbach.koropapps.vocabulary.utils.appComponent
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed
import yaroslavgorbach.koropapps.vocabulary.utils.setBackgroundStatusBarColor
import yaroslavgorbach.koropapps.vocabulary.utils.setDefaultStatusBarColor
import javax.inject.Inject

class LevelFragment : Fragment(R.layout.fragment_level) {

    companion object {
        fun newInstance() = LevelFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LevelViewModel> { viewModelFactory }

    private lateinit var levelView: LevelView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initDagger()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
        requireActivity().setBackgroundStatusBarColor()
    }

    private fun initDagger() {
        appComponent()
            .levelComponent()
            .create()
            .inject(this)
    }

    private fun initView() {
        levelView = LevelView(
            FragmentLevelBinding.bind(requireView()),
            object : LevelView.Callback {
                override fun onBack() {
                    onBackPressed()
                }
            }
        )
    }

    private fun initObservers() {
        viewModel.levelInfoUi.observe(viewLifecycleOwner, levelView::setLevelInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().setDefaultStatusBarColor()
    }
}