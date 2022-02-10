package yaroslavgorbach.koropapps.vocabulary.feature.exercise.description.ui

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

    fun setCurrentStatisticItem(item: StatisticItemUi?) {
        item?.let {
            binding.statisticsValueTitle.text = binding.getString(item.valueTitleRes)
            binding.statisticsTimeTitle.text = binding.getString(item.timeTitleRes)
            binding.statisticsValueValue.text = item.value.toString()
            binding.statisticsTimeValue.text = item.time.toString()
        }
    }

    fun setDescriptionState(state: DescriptionState) {
        binding.exerciseDescription.maxLines = state.maxLines

        when (state) {
            DescriptionState.COLLAPSED -> binding.showHideDescription.setImageResource(R.drawable.ic_arrow_down)
            DescriptionState.OPENED -> binding.showHideDescription.setImageResource(R.drawable.ic_arrow_up)
        }
    }
}
