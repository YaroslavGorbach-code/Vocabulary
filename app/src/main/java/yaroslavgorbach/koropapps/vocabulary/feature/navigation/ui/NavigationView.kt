package yaroslavgorbach.koropapps.vocabulary.feature.navigation.ui

import kotlinx.coroutines.InternalCoroutinesApi
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentNavigationBinding

@InternalCoroutinesApi
class NavigationView(
    private val binding: FragmentNavigationBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onNavigateToExercisesList()
        fun onNavigateToProfile()
    }

    init {
        initViewActions()
    }

    private fun initViewActions() {
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            if (binding.bottomNav.selectedItemId != item.itemId) {
                if (item.itemId == R.id.menu_nav_exercises) {
                    callback.onNavigateToExercisesList()
                }
                if (item.itemId == R.id.menu_nav_profile) {
                    callback.onNavigateToProfile()
                }
            }
            true
        }
    }
}