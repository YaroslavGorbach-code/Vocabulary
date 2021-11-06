package yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.ui.recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yaroslavgorbach.koropapps.vocabulary.databinding.ItemPageBinding
import yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.factory.AboutAppPagesFactory
import yaroslavgorbach.koropapps.vocabulary.feature.aboutapp.model.AboutAppPageUi
import yaroslavgorbach.koropapps.vocabulary.utils.inflateBinding

class AboutAppPageAdapter(private val onStart: () -> Unit) :
    RecyclerView.Adapter<AboutAppPageAdapter.ViewHolder>() {

    private val pagesFactory: AboutAppPagesFactory
        get() = AboutAppPagesFactory()

    private val items: List<AboutAppPageUi> = pagesFactory.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(ItemPageBinding::inflate))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.start.setOnClickListener {
                onStart()
            }

            binding.icClose.setOnClickListener {
                onStart()
            }
        }

        fun bind(page: AboutAppPageUi) {
            with(binding) {
                icon.setImageResource(page.imageRes)
                title.setText(page.titleRes)
                message.setText(page.messageRes)

                if (page.isStartButtonVisible){
                    binding.start.visibility = View.VISIBLE
                }else{
                    binding.start.visibility = View.GONE
                }
            }
        }
    }
}