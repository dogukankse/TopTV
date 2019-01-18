package com.dogukankse.toptv.ui.moviedetails

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.dogukankse.toptv.R
import com.dogukankse.toptv.api.*
import com.dogukankse.toptv.pojo.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_show_details.*


class ShowDetailsActivity : AppCompatActivity() {

    private var showId: Int? = null
    private lateinit var client: ApiClient

    private val youtubeVideoUrl = "http://www.youtube.com/watch?v=%s"
    private val imageBaseUrl = "http://image.tmdb.org/t/p/w500"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        show_detail_back_button.setOnClickListener {
            finish()
        }


        showId = intent.getStringExtra(Intent.EXTRA_TEXT).toInt()

        client = ApiClient.instance

        getTvShow()
    }

    private fun getTvShow() {
        client.getShow(showId!!, object : OnGetShowCallback {
            override fun onSuccess(showDetail: ShowDetail) {
                getCastList(showId!!)
                getRecommendationList(showId!!)
                getTrailer(showId!!)

                show_detail_title.text = showDetail.title
                show_detail_overview.text = showDetail.overview
                show_detail_vote.text = showDetail.vote.toString()
                show_detail_status.text = showDetail.status
                setStatusInfo(showDetail.status)
                getGenreList(showDetail)
                setSeasonInfo(showDetail.numberOfSeasons)
                show_detail_episodes.text = "Episodes: ${showDetail.numberOfEpisodes}"
                Picasso.get().load(imageBaseUrl + showDetail.imagePath).into(show_detail_image)
            }

            override fun onError() {
                Toast.makeText(applicationContext, "This show too bad to load!", Toast.LENGTH_LONG).show()
                finish()
            }

        })
    }

    private fun getTrailer(showId: Int) {
        client.getTrailerList(showId, object : OnGetTrailerListCallback {
            override fun onSuccess(trailerList: List<Trailer>) {
                show_detail_trailer_button.setOnClickListener {
                    val intent =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(youtubeVideoUrl.replace("%s", trailerList[0].key.toString()))
                        )
                    startActivity(intent)
                }

            }

            override fun onError() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

    private fun getRecommendationList(showId: Int) {
        client.getRecommendationList(showId, object : OnGetRecommendationListCallback {
            override fun onSuccess(recommendationList: List<Show>) {
                for (rec in recommendationList) {
                    val inflater =
                        applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val view = inflater.inflate(R.layout.cast_card, null)
                    val showImage = view.findViewById<ImageView>(R.id.cast_image)
                    val showName = view.findViewById<TextView>(R.id.cast_name)
                    Picasso.get().load(imageBaseUrl + rec.imagePath).error(R.mipmap.no_image).into(showImage)
                    showName.text = rec.name
                    view.setOnClickListener {
                        val showDetailActivityIntent = Intent(this@ShowDetailsActivity, ShowDetailsActivity::class.java)
                        showDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, rec.id.toString())
                        startActivity(showDetailActivityIntent)
                    }
                    show_detail_recommendations.addView(view)
                }

            }

            override fun onError() {
                Toast.makeText(applicationContext, "It's a quite unique show", Toast.LENGTH_LONG).show()
            }


        })
    }

    private fun getCastList(showId: Int) {
        client.getCastList(showId, object : OnGetCastCallback {
            override fun onSuccess(castList: List<Cast>) {
                for (cast in castList) {
                    val inflater = applicationContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE
                    ) as LayoutInflater
                    val view = inflater.inflate(R.layout.cast_card, null)
                    val castImage = view.findViewById<ImageView>(R.id.cast_image)
                    val castName = view.findViewById<TextView>(R.id.cast_name)
                    Picasso.get().load(imageBaseUrl + cast.imagePath).error(R.mipmap.no_image).into(castImage)
                    castName.text = cast.name
                    show_detail_cast.addView(view)

                }


            }

            override fun onError() {
                Toast.makeText(applicationContext, "No cast only AI!!", Toast.LENGTH_LONG).show()

            }

        })
    }

    private fun setStatusInfo(status: String?) {
        show_detail_status.run {
            when (status) {
                "Ended" -> setTextColor(Color.RED)
                "Canceled" -> setTextColor(Color.RED)
                "Returning Series" -> setTextColor(ContextCompat.getColor(context, R.color.darkGreen))
            }
        }


    }

    private fun setSeasonInfo(seasonInfo: Int?) {
        when (seasonInfo) {
            null -> show_detail_seasons.text = "Unknown"
            1 -> show_detail_seasons.text = "Season: $seasonInfo"
            else -> show_detail_seasons.text = "Seasons: $seasonInfo"
        }
    }

    private fun getGenreList(showDetail: ShowDetail) {
        client.getGenreList(object : OnGetGenreListCallback {
            override fun onSuccess(genres: List<Genre>) {
                if (showDetail.genreList != null) {
                    val currentGenres = ArrayList<String>()
                    for (genre in showDetail.genreList) {
                        currentGenres.add(genre.name!!)
                    }
                    show_detail_genres.text = TextUtils.join(", ", currentGenres)
                }
            }

            override fun onError() {
                Toast.makeText(
                    applicationContext,
                    "Something wrong. All shows are the same :(",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}
