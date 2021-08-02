package yaroslavgorbach.koropapps.vocabulary.feature.common.uikit

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.res.use
import com.google.android.material.card.MaterialCardView
import yaroslavgorbach.koropapps.vocabulary.R

class LetterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val letterTextView: TextView = TextView(context, attrs, defStyleAttr).apply {
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
        addView(this)
    }

    private val descriptionTextView: TextView =
        TextView(context, attrs, defStyleAttr).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            setTextColor(Color.WHITE)
            setPadding(0, 30, 0, 0)
            addView(this)
        }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.LetterView).use {
            letterTextView.textSize = it.getDimension(
                R.styleable.LetterView_letterSize, 24f
            )
            letterTextView.text = it.getString(
                R.styleable.LetterView_letter
            )
            descriptionTextView.textSize = it.getDimension(
                R.styleable.LetterView_descriptionSize, 16f
            )
            descriptionTextView.text = it.getString(
                R.styleable.LetterView_description
            )
        }

        setPadding(10, 0, 10, 0)
    }

    fun setLetter(letter: String) {
        letterTextView.text = letter
        generateCorrectTextSize(letter)
    }

    fun setDescription(description: String) {
        descriptionTextView.text = description
    }

    private fun generateCorrectTextSize(text: String) {
        letterTextView.textSize = if (text.length > 2) {
            50f
        } else {
            100f
        }
    }
}