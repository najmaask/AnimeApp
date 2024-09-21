package com.najma.animeapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvRating: TextView = findViewById(R.id.tv_rating)
        val tvGenre: TextView = findViewById(R.id.tv_genre)
        val tvDesc: TextView = findViewById(R.id.tv_desc)
        val tvSinopsis: TextView = findViewById(R.id.tv_sinopsis)
        val ivPic: ImageView = findViewById(R.id.iv_pic)

        val anime = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Anime>(EXTRA_MOVIE)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_MOVIE)
        }

        tvName.text = anime?.name
        tvRating.text = "Rating: ${anime?.rating}"
        tvGenre.text = "Genre: ${anime?.genre}"
        tvDesc.text = anime?.description
        tvSinopsis.text = "Sinopsis: ${anime?.sinopsis}"
        anime?.photo?.let { ivPic.setImageResource(it) }

        supportActionBar?.title = anime?.name

        val data = intent.getParcelableExtra<Anime>("DATA")
        Log.d("Data Anime: ", data?.name.toString())
        }
}