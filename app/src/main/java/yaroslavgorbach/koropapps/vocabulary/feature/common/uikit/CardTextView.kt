package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.res.use
import com.google.android.material.card.MaterialCardView
import yaroslavgorbach.koropapps.vocabulary.R

class CardTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val textView: TextView = TextView(context, attrs, defStyleAttr).apply {
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
        addView(this)
    }

    private val textAtTop: TextView =
        TextView(context, attrs, defStyleAttr).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            setTextColor(Color.WHITE)
            setPadding(0, 30, 0, 0)
            addView(this)
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CardTextView).use {
            textView.textSize = it.getDimension(
                R.styleable.CardTextView_textSize, 24f
            )
            textView.text = it.getString(
                R.styleable.CardTextView_text
            )
            textAtTop.textSize = it.getDimension(
                R.styleable.CardTextView_textAtTopSize, 16f
            )
            textAtTop.text = it.getString(
                R.styleable.CardTextView_textAtTop
            )
        }

        setPadding(10, 0, 10, 0)
    }

    fun setLetter(letter: String) {
        textView.text = letter
        generateCorrectTextSize(letter)
    }

    fun setDescription(description: String) {
        textAtTop.text = description
    }

    private fun generateCorrectTextSize(text: String) {
        textView.textSize = if (text.length > 2) {
            50f
        } else {
            100f
        }
    }
}