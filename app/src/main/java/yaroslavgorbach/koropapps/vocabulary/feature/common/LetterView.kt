package yaroslavgorbach.koropapps.vocabulary.feature.common

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

    private val textView: TextView = TextView(context, attrs, defStyleAttr).apply {
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
    }


    fun setLetter(letter: String) {
        textView.text = letter

    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.LetterView).use {
            textView.textSize = it.getDimension(R.styleable.LetterView_letterSize, 24f)
            textView.text = it.getString(R.styleable.LetterView_letter)
        }
        addView(textView)
    }

}