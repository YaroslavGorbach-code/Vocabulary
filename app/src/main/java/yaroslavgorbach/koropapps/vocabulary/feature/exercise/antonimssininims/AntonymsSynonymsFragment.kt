package yaroslavgorbach.koropapps.vocabulary.feature.exercise.antonimssininims

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentAntonymsSynonymsBinding

class AntonymsSynonymsFragment : Fragment(R.layout.fragment_antonyms_synonyms) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init vm
        val vm by viewModels<AntonymsSynonymsVm>()

        // init v
        val v = AntonymsSynonymsView(
            FragmentAntonymsSynonymsBinding.bind(view),
            object : AntonymsSynonymsView.Callback {
                override fun onNext() {
                    vm.generateText()
                }

            })
        vm.getText().observe(viewLifecycleOwner) { text ->
            v.setTaskText(text)
        }
    }
}