package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemAchievementBinding
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding

class AchievementsAdapter : RecyclerView.Adapter<AchievementsAdapter.ViewHolder>() {

    var items: List<Achievement> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemAchievementBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemAchievementBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Achievement) {
            binding.icon.setImageDrawable(item.icon)
        }
    }
}