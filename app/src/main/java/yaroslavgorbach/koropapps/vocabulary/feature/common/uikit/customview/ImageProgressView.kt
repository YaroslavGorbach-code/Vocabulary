package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit.customview

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.res.use
import androidx.core.math.MathUtils
import yaroslavgorbach.koropapps.vocabulary.R
import kotlin.math.min

class ImageProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEF_SIZE = 20
    }

    private var progressStrokeWidth: Float = 16f
    private var outlineStrokeWidth: Float = 4f
    private var progress: Int = -1
    private var outlineColor: Int = Color.LTGRAY
    private var progressColor: Int = Color.YELLOW

    private val imageView: ImageView = ImageView(context, attrs, defStyleAttr).apply {
        scaleType = ImageView.ScaleType.CENTER
    }

    private val progressPaint: Paint
    private var outlinePaint: Paint

    private var size = 0f
    private var prevSize = 0f
    private var cen = 0f
    private var rad = 0f
    private val path = Path()
    private val circleRect = RectF()

    init {
        var progressAttr = 0
        context.obtainStyledAttributes(attrs, R.styleable.ImageProgressView).use {
            progressStrokeWidth = it.getDimension(
                R.styleable.ImageProgressView_imageProgressStrokeWidth, 16f
            )
            outlineColor = it.getColor(
                R.styleable.ImageProgressView_imageProgressOutlineColor, Color.LTGRAY
            )
            outlineStrokeWidth = it.getDimension(
                R.styleable.ImageProgressView_imageProgressOutlineStrokeWidth, 4f
            )
            progressAttr = it.getInteger(
                R.styleable.ImageProgressView_imageProgress, 0
            )
            progressColor = it.getColor(
                R.styleable.ImageProgressView_imageProgressColor, Color.YELLOW
            )
        }
        setProgress(progressAttr)

        outlinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = outlineStrokeWidth
            color = outlineColor
        }
        progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = progressStrokeWidth
            color = progressColor
        }
        addView(imageView)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        size = min(getSpecSize(widthMeasureSpec), getSpecSize(heightMeasureSpec)).toFloat()
        if (size != prevSize) {
            rad = (size - progressStrokeWidth) / 2
            cen = size / 2
            circleRect.set(cen - rad, cen - rad, cen + rad, cen + rad)
            prevSize = size
        }
        val sizeSpec = MeasureSpec.makeMeasureSpec(size.toInt(), MeasureSpec.EXACTLY)
        super.onMeasure(sizeSpec, sizeSpec)
    }

    private fun getSpecSize(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            MeasureSpec.AT_MOST -> min(DEF_SIZE, MeasureSpec.getSize(spec))
            else -> DEF_SIZE
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        // draw outline
        canvas.drawOval(circleRect, outlinePaint)
        // draw progress
        val angle = 360f * progress / 100
        if (angle == 360f) { // arc 360 draws nothing
            canvas.drawOval(circleRect, progressPaint)
        } else {
            path.reset()
            path.arcTo(circleRect, -90f, angle, true)
            canvas.drawPath(path, progressPaint)
        }
        super.dispatchDraw(canvas)
    }

    fun setProgress(value: Int) {
        if (progress != value) {
            progress = MathUtils.clamp(value, 0, 100)
            invalidate()
        }
    }

    fun setImage(drawable: Drawable?) {
        imageView.setImageDrawable(drawable)
    }
}