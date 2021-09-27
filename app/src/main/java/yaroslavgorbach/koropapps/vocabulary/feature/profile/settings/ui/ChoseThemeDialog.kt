package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.UiMode
import yaroslavgorbach.koropapps.vocabulary.databinding.DialogChoseThemeBinding
import yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler.GridSpacingItemDecoration
import yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui.recycler.ThemesListAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.host

class ChoseThemeDialog : DialogFragment() {

    companion object {
        private const val THEMES_LIST_ARG = "THEMES_LIST_ARG"
        private const val UI_MODE_ARG = "UI_MODE_ARG"

        private const val NUMBER_OF_GRID_SPANS = 4

        fun newInstance(themes: List<Theme>, uiMode: UiMode): ChoseThemeDialog {
            return ChoseThemeDialog().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(THEMES_LIST_ARG, ArrayList(themes))
                    putParcelable(UI_MODE_ARG, uiMode)
                }
            }
        }
    }

    interface Callback {
        fun onThemeChanged(theme: Theme)
        fun onUiModeChanged(uiMode: UiMode)
    }

    private var _themesListAdapter: ThemesListAdapter? = null

    private val themesListAdapter: ThemesListAdapter
        get() = requireNotNull(_themesListAdapter)

    private var _gridLayoutManager: GridLayoutManager? = null

    private val gridLayoutManager: GridLayoutManager
        get() = requireNotNull(_gridLayoutManager)

    private var _gridSpacingItemDecorator: GridSpacingItemDecoration? = null

    private val gridSpacingItemDecorator: GridSpacingItemDecoration
        get() = requireNotNull(_gridSpacingItemDecorator)

    private var _binding: DialogChoseThemeBinding? = null

    private val binding: DialogChoseThemeBinding
        get() = requireNotNull(_binding)

    private val themes: ArrayList<Theme>
        get() = requireArguments().getParcelableArrayList(THEMES_LIST_ARG)!!

    private val uiMode: UiMode
        get() = requireArguments().getParcelable(UI_MODE_ARG)!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProperties()
        initViews()
        initViewActions()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setTitle(R.string.chose_theme)
            .create()
    }

    private fun initViews() {
        binding.list.apply {
            adapter = themesListAdapter
            layoutManager = gridLayoutManager
            addItemDecoration(gridSpacingItemDecorator)
        }

        binding.themeIcon.setImageResource(uiMode.iconRes)
        binding.themeText.setText(uiMode.textRes)
    }

    private fun initViewActions() {
        binding.uiModeContainer.setOnClickListener {
            if (uiMode is UiMode.Light) {
                host<Callback>().onUiModeChanged(UiMode.Dark())
            } else {
                host<Callback>().onUiModeChanged(UiMode.Light())
            }
            dismiss()
        }
    }

    private fun initProperties() {
        _binding = DialogChoseThemeBinding.inflate(LayoutInflater.from(requireContext()))

        _themesListAdapter = ThemesListAdapter { theme ->
            host<Callback>().onThemeChanged(theme)

            dismiss()
        }.apply { submitList(themes) }

        _gridSpacingItemDecorator = GridSpacingItemDecoration(NUMBER_OF_GRID_SPANS, 34, true, 0)

        _gridLayoutManager = GridLayoutManager(requireContext(), NUMBER_OF_GRID_SPANS)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        _binding = null
    }
}

