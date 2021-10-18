package yaroslavgorbach.koropapps.vocabulary.feature.records.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import yaroslavgorbach.koropapps.vocabulary.R

class RecordsListFragment: Fragment(R.layout.fragment_records_list) {

    companion object{
        fun newInstance() = RecordsListFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}