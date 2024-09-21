package com.najma.animeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvAnime: RecyclerView
    private val list = ArrayList<Anime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = "Rekomendasi Anime"

        rvAnime = findViewById(R.id.rv_animes)
        rvAnime.setHasFixedSize(true)

        list.addAll(getListAnimes())
        showRecyclerList()

        val btnMoveActivity: RecyclerView = findViewById(R.id.rv_animes)
        btnMoveActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rv_animes -> {
//                val anime = Anime(
//                    "Kimi No Nawa",
//                    "8.4/10",
//                    "Drama, Romance, Supernatural",
//                    "Two strangers find themselves linked in a bizarre way. When a connection forms, will distance be the only thing to keep them apart?",
//                    "Mitsuha Miyamizu, seorang siswi sekolah menengah atas yang tinggal di desa fiktif bernama Itomori di daerah pegunungan Hida Prefektur Gifu, mulai bosan dengan kehidupannya di pedesaan tempat dia lahir dan berharap dapat terlahir menjadi pemuda tampan yang hidup di Tokyo pada kehidupan selanjutnya. Kemudian, Taki Tachibana, seorang siswa sekolah menengah atas yang tinggal di Tokyo, terbangun dari tidurnya dan menyadari bahwa dirinya adalah Mitsuha, yang entah bagaimana bisa masuk ke dalam tubuh Taki.",
//                    0
//                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvAnime.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAnime.layoutManager = GridLayoutManager(this, 2)
            }
        }
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about -> {
                val goAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(goAbout)
            }
        }
    }


    @SuppressLint("Recycle")
    private fun getListAnimes(): ArrayList<Anime> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataSinopsis = resources.getStringArray(R.array.data_sinopsis)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listAnime = ArrayList<Anime>()
        for (i in dataName.indices) {
            val anime = Anime(dataName[i], dataRating[i], dataGenre[i], dataDescription[i], dataSinopsis[i], dataPhoto.getResourceId(i, -1))
            listAnime.add(anime)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        rvAnime.adapter = listAnimeAdapter

        listAnimeAdapter.setOnItemClickCallback(object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                val moveActivityIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveActivityIntent.putExtra(DetailActivity.EXTRA_MOVIE, data)
                startActivity(moveActivityIntent)
            }
        })
    }
}