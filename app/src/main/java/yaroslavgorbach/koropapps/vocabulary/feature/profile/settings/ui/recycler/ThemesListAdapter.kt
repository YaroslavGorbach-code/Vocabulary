package yaroslavgorbach.koropapps.vocabulary.feature.profile.settings.ui.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.data.settings.local.model.Theme
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemThemeBinding
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBind

class ThemesListAdapter(private val onThemeClick: (Theme) -> Unit) :
    ListAdapter<Theme, ThemesListAdapter.ViewHolder>(object : ItemCallback<Theme>() {
        override fun areItemsTheSame(oldItem: Theme, newItem: Theme): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Theme, newItem: Theme): Boolean {
            return false
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBind(ItemThemeBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemThemeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            initViewActions()
        }

        private fun initViewActions() {
            binding.root.setOnClickListener {
                onThemeClick(getItem(adapterPosition))
            }
        }

        fun bind(theme: Theme) {
            with(binding) {
                if (theme.isCurrent) {
                    mark.visibility = View.VISIBLE
                } else {
                    mark.visibility = View.INVISIBLE
                }

                root.background.setTint(binding.root.context.resources.getColor(theme.colorPrimaryRes))
            }
        }
    }

}