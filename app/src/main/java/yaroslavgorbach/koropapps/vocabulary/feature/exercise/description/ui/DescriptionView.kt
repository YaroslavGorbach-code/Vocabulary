package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import yaroslavgorbach.koropapps.vocabulary.R
import yaroslavgorbach.koropapps.vocabulary.databinding.FragmentDescriptionBinding
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.DescriptionState
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.model.StatisticItemUi
import yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui.recycler.StatisticItemsAdapter
import yaroslavgorbach.koropapps.vocabulary.utils.getDrawable
import yaroslavgorbach.koropapps.vocabulary.utils.getString

class DescriptionView(
    private val binding: FragmentDescriptionBinding,
    private val callback: Callback
) {
    interface Callback {
        fun onOpenExercise()
        fun onBack()
        fun onAddToFavorite()
        fun onChangeDescriptionState()
        fun onStatisticItemChosen(statisticItemUi: StatisticItemUi)
    }

    private val statisticItemsAdapter: StatisticItemsAdapter by lazy {
        StatisticItemsAdapter(callback::onStatisticItemChosen)
    }

    init {
        initRecycler()
        initActions()
    }

    private fun initRecycler() {
        binding.statisticDaysList.apply {
            adapter = statisticItemsAdapter
            layoutManager = LinearLayoutManager(
                binding.root.context, LinearLayoutManager.HORIZONTAL, false
            )
        }
    }

    private fun initActions() {
        binding.start.setOnClickListener {
            callback.onOpenExercise()
        }

//        binding.toolbar.setNavigationOnClickListener {
//            callback.onBack()
//        }
//
//        binding.toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.menu_add_to_favorite -> {
//                    callback.onAddToFavorite()
//                }
//            }
//            true
//        }
//
//        binding.chart.nextData.setOnClickListener {
//            callback.onNextChartValue()
//        }
//
//        binding.chart.prevData.setOnClickListener {
//            callback.onPreviousChartValue()
//        }
//
//        binding.chartTime.nextData.setOnClickListener {
//            callback.onNextChartTime()
//        }
//
//        binding.chartTime.prevData.setOnClickListener {
//            callback.onPreviousChartTime()
//        }
//
//        binding.scrollView.setOnScrollChangeListener(
//            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
//                if (scrollY > oldScrollY) {
//                    binding.openExercise.hide()
//                } else {
//                    binding.openExercise.show()
//                }
//            })

        binding.showHideDescription.setOnClickListener {
            callback.onChangeDescriptionState()
        }
    }

    fun setDescriptionRes(descriptionRes: Int) {
        binding.exerciseDescription.text = binding.getString(descriptionRes)
    }

    fun setExerciseIconRes(exerciseIconRes: Int) {
        with(binding.getDrawable(exerciseIconRes)) {
            binding.icon.setImageDrawable(this)
        }
    }

    fun setExerciseFavorite(isFavorite: Boolean) {
//        val menuItem = binding.toolbar.menu[0]
//
//        if (isFavorite) {
//            menuItem.icon = binding.getDrawable(R.drawable.ic_star_yellow)
//        } else {
//            menuItem.icon = binding.getDrawable(R.drawable.ic_star)
//        }
    }

    fun setExerciseNameRes(exerciseNameRes: Int) {
        binding.exerciseName.text = binding.getString(exerciseNameRes)
    }

    fun setStatisticItems(statisticItems: List<StatisticItemUi>) {
        statisticItemsAdapter.setData(statisticItems)
        scrollToCorrectPosition(statisticItems)
        setStatisticsVisibility(statisticItems)
    }

    private fun setStatisticsVisibility(statisticItems: List<StatisticItemUi>) {
        if (statisticItems.isEmpty()) {
            binding.statisticLayer.visibility = View.GONE
        } else {
            binding.statisticLayer.visibility = View.VISIBLE
        }
    }

    private fun scrollToCorrectPosition(statisticItems: List<StatisticItemUi>) {
        Handler(Looper.getMainLooper()).post {
            if (statisticItems.isNotEmpty()) {
                binding.statisticDaysList.smoothScrollToPosition(statisticItems.indexOfFirst { it.isChosen })
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setCurrentStatisticItem(item: StatisticItemUi?) {
        item?.let {
            binding.textStatisticValueTitle.text = binding.getString(item.valueSubtitleRes)
            binding.textStatisticValueValue.text =
                binding.getString(item.valueTitleRes) + " " + item.value.toString()
            binding.textStatisticTimeTitle.text = binding.getString(item.timeTitleRes)
            binding.textStatisticTimeValue.text =
                item.time.toString() + " " + binding.getString(R.string.seconds_short)
        }
    }

    fun setDescriptionState(state: DescriptionState) {
        binding.exerciseDescription.maxLines = state.maxLines

        when (state) {
            DescriptionState.COLLAPSED -> binding.showHideDescriptionImage.setImageResource(R.drawable.ic_arrow_down)
            DescriptionState.OPENED -> binding.showHideDescriptionImage.setImageResource(R.drawable.ic_arrow_up)
        }
    }
}
