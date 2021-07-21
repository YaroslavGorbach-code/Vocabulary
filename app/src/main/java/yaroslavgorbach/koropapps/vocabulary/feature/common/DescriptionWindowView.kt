package yaroslavgorbach.koropapps.vocabulary.feature.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class DescriptionWindowView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val descriptionWindowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.YELLOW
        setShadowLayer(15f, 0f, 1f, Color.LTGRAY);
        strokeWidth = 20f
    }

    private val descriptionWindowPath = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        descriptionWindowPath.apply {
            val heightTwentyPresent = h.toFloat() * 0.2f
            lineTo(w.toFloat(), 5f)
            lineTo(w.toFloat(), h.toFloat() - heightTwentyPresent)
            lineTo(w.toFloat() / 2 + heightTwentyPresent, h.toFloat() - heightTwentyPresent)
            lineTo(w.toFloat() / 2, h.toFloat() - 25)
            lineTo(w.toFloat() / 2 - heightTwentyPresent, h.toFloat() - heightTwentyPresent)
            lineTo(0f, h.toFloat() - heightTwentyPresent)
            lineTo(0f, 0f)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawDescriptionWindow(canvas)
    }


    private fun drawDescriptionWindow(canvas: Canvas) {
        canvas.drawPath(descriptionWindowPath, descriptionWindowPaint)
    }

}