package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentLevelBinding
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import yaroslavgorbach.koropapps.vocabulary.utils.onBackPressed

class LevelFragment : Fragment(R.layout.fragment_level) {

    companion object {
        fun newInstance() = LevelFragment()
    }

    private lateinit var levelView: LevelView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObservers()
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

    private fun initObservers(){
        levelView.setLevelInfo(LevelInfoUi("test"))
    }
}