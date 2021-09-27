package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.use
import yaroslavgorbach.koropapps.vocabulary.R

class DescriptionWindowView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    companion object {
        const val EMPTY_STRING = ""
    }

    private val descriptionWindowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.YELLOW
        //setShadowLayer(0f, 0f, 0f, Color.LTGRAY);
        strokeWidth = 20f
    }

    private val descriptionTextView = TextView(context, attrs, defStyleAttr).apply {
        gravity = Gravity.CENTER
        setPadding(5, 0, 5, 100)
        setTextColor(Color.WHITE)
        textSize = 20f
        addView(this)
    }

    private val descriptionWindowPath = Path()

    var descriptionText: String = EMPTY_STRING
        set(value) {
            descriptionTextView.text = value
            field = value
        }


    init {
        context.obtainStyledAttributes(attrs, R.styleable.DescriptionWindowView).use {
            descriptionWindowPaint.color = it.getColor(
                R.styleable.DescriptionWindowView_backgroundColor, Color.YELLOW
            )
        }
        cardElevation = 0f
        setCardBackgroundColor(0)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        measureDescriptionWindowPath(h, w)
    }

    private fun measureDescriptionWindowPath(h: Int, w: Int) {
        descriptionWindowPath.apply {
            val heightTwentyPresent = h.toFloat() * 0.2f
            setLastPoint(0f, 0f)
            lineTo(w.toFloat(), 0f)
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