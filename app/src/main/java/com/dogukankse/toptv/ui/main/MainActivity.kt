package com.dogukankse.toptv.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.dogukankse.toptv.R
import com.dogukankse.toptv.adapters.MyRecyclerAdapter
import com.dogukankse.toptv.api.ApiClient
import com.dogukankse.toptv.api.OnGetGenreListCallback
import com.dogukankse.toptv.api.OnGetShowListCallback
import com.dogukankse.toptv.pojo.Genre
import com.dogukankse.toptv.pojo.Show
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.dogukankse.toptv.ui.moviedetails.ShowDetailsActivity


class MainActivity : AppCompatActivity() {

    private lateinit var client: ApiClient
    private var adapter: MyRecyclerAdapter? = null


    private var showGenres: List<Genre>? = null
    private var isFetchingShows: Boolean = false
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        client = ApiClient.instance
        show_list.layoutManager = LinearLayoutManager(this)

        setupOnScrollListener()
        getGenres()



    }

    private fun setupOnScrollListener() {
        val manager = LinearLayoutManager(this)
        show_list.layoutManager = manager
        show_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val itemCount = manager.itemCount
                val childCount = manager.childCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()

                if (firstVisibleItemPosition + childCount >= itemCount / 2) {
                    if (!isFetchingShows) {
                        val nextPage = ++currentPage
                        getShows(nextPage)
                    }
                }
            }
        })
    }

    private fun getGenres() {
        client.getGenreList(object : OnGetGenreListCallback {
            override fun onSuccess(genres: List<Genre>) {
                showGenres = genres
                getShows(currentPage)
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

    private fun myOnClick(show : Show) {
        val showDetailActivityIntent = Intent(this, ShowDetailsActivity::class.java)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, show.id.toString())
        startActivity(showDetailActivityIntent)
    }


    private fun getShows(page: Int) {
        isFetchingShows = true
        client.getShowList(page, object : OnGetShowListCallback {
            override fun onSuccess(page: Int, shows: MutableList<Show>) {
                Log.d("MoviesRepository", "Current Page = $page")

                if (adapter == null) {
                    adapter = MyRecyclerAdapter(shows, showGenres!!) { show : Show -> myOnClick(show) }
                    show_list.adapter = adapter
                } else {
                    adapter!!.appendMovies(shows)
                }
                currentPage = page
                isFetchingShows = false
            }

            override fun onError() {
                Toast.makeText(
                    applicationContext,
                    "Something wrong. There is no tv show :o",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
