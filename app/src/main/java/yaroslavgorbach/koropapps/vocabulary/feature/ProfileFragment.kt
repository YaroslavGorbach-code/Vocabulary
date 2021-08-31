package yaroslavgorbach.koropapps.vocabulary.feature

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}