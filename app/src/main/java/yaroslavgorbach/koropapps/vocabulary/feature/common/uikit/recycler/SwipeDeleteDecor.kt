package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.recycler

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class SwipeDeleteDecor(
    itemBackground: Drawable,
    private val onSwipe: (viewHolder: RecyclerView.ViewHolder) -> Unit,
    private val onHalfSwipe: (viewHolder: RecyclerView.ViewHolder) -> Unit
) : ItemTouchHelper(object : SimpleCallback(0, START or END) {

    private fun drawBackgroundHint(
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        canvas: Canvas
    ) {
        val item = viewHolder.itemView

        val clipLeft: Int = if (dX >= 0) {
            0
        } else {
            item.width + dX.toInt()
        }

        val clipRight: Int = if (dX >= 0) {
            dX.toInt()
        } else {
            item.width
        }

        canvas.clipRect(clipLeft, item.top, clipRight, item.bottom)
        itemBackground.setBounds(0, item.top, item.width, item.bottom)
        itemBackground.alpha = ((1 - abs(dX / item.width)) * 255).toInt()
        itemBackground.draw(canvas)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipe(viewHolder)
    }

    override fun onChildDraw(
        canvas: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        drawBackgroundHint(viewHolder, dX, canvas)
        calculateHalfSwipe(dX, viewHolder, onHalfSwipe)
        super.onChildDraw(
            canvas,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
    }

    private fun calculateHalfSwipe(
        dX: Float,
        viewHolder: RecyclerView.ViewHolder,
        onHalfSwipe: (viewHolder: RecyclerView.ViewHolder) -> Unit
    ) {
        val itemWidth = viewHolder.itemView.width

        if (abs(dX) > itemWidth / 2) {
            onHalfSwipe(viewHolder)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        viewHolder.itemView.isActivated = false
        super.clearView(recyclerView, viewHolder)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState == ACTION_STATE_SWIPE) {
            if (viewHolder != null) {
                viewHolder.itemView.isActivated = true
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }
})
