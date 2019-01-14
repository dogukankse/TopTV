package com.dogukankse.toptv.adapters

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dogukankse.toptv.R
import com.dogukankse.toptv.pojo.Genre
import com.dogukankse.toptv.pojo.Result
import com.squareup.picasso.Picasso




class MyRecyclerAdapter constructor(private var results: MutableList<Result>, private var genreList: List<Genre>) :
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {

    private val imageBaseUrl = "http://image.tmdb.org/t/p/w500"

    fun appendMovies(shows: List<Result>) {
        results.addAll(shows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: MyRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var releaseDate: TextView = itemView.findViewById(R.id.show_card_release)
        private var title: TextView = itemView.findViewById(R.id.show_card_title)
        private var rating: TextView = itemView.findViewById(R.id.show_card_vote)
        private var genres: TextView = itemView.findViewById(R.id.show_card_genre)
        private var showImage: ImageView = itemView.findViewById(R.id.show_card_image)

        fun bind(results: Result) {

            releaseDate.text = results.releaseDate!!.split("-")[0]
            title.text = results.name
            rating.text = results.voteAverage.toString()
            genres.text = getGenres(results.genreIds!!)
            Picasso.get().load(imageBaseUrl+results.imagePath)
                .placeholder(R.color.colorPrimary)
                .error(R.color.colorAccent).into(showImage)


        }

        private fun getGenres(genreIds: List<Int>): String {
            val showGenres = ArrayList<String>()
            for (genreId in genreIds) {
                for (genre in genreList) {
                    if (genre.id == genreId) {
                        showGenres.add(genre.name!!)
                        break
                    }
                }
            }
            return TextUtils.join(", ", showGenres)
        }
    }


}