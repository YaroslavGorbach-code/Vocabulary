package yaroslavgorbach.koropapps.vocabulary

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RedirectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.redirect_screen)

        val recycler = findViewById<RecyclerView>(R.id.screenshotsRecycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = ScreenshotAdapter(
            listOf(
                R.drawable.screenshot_1,
                R.drawable.screenshot_2,
                R.drawable.screenshot_3,
                R.drawable.screenshot_4,
                R.drawable.screenshot_5,
                R.drawable.screenshot_6,
            )
        )
        findViewById<Button>(R.id.goToNewAppButton).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://play.google.com/store/apps/details?id=com.korop.yaroslavhorach.lingoFlow")
            }
            startActivity(intent)
            finishAffinity()
        }
    }

    override fun onBackPressed() {
        // Блокуємо вихід
    }


    class ScreenshotAdapter(private val images: List<Int>) :
        RecyclerView.Adapter<ScreenshotAdapter.ImageViewHolder>() {

        inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_screenshot, parent, false)
            return ImageViewHolder(view)
        }

        override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
            holder.imageView.setImageResource(images[position])
        }

        override fun getItemCount(): Int = images.size
    }
}