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
import com.dogukankse.toptv.pojo.Show
import com.squareup.picasso.Picasso


class MyRecyclerAdapter constructor(
    private var showList: MutableList<Show>,
    private var genreList: List<Genre>,
    private val clickListener: (Show) -> Unit
) :
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder>() {

    private val imageBaseUrl = "http://image.tmdb.org/t/p/w500"

    fun appendMovies(shows: List<Show>) {
        showList.addAll(shows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return showList.size
    }

    override fun onBindViewHolder(holder: MyRecyclerAdapter.ViewHolder, position: Int) {
        holder.bind(showList[position], clickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var releaseDate: TextView = itemView.findViewById(R.id.show_card_release)
        private var title: TextView = itemView.findViewById(R.id.show_card_title)
        private var vote: TextView = itemView.findViewById(R.id.show_card_vote)
        private var genres: TextView = itemView.findViewById(R.id.show_card_genre)
        private var showImage: ImageView = itemView.findViewById(R.id.show_card_image)


        fun bind(show: Show, clickListener: (Show) -> Unit) {

            releaseDate.text = show.releaseDate!!.split("-")[0]
            title.text = show.name
            vote.text = show.voteAverage.toString()
            genres.text = getGenreList(show.genreIds!!)
            Picasso.get().load(imageBaseUrl + show.imagePath)
                .placeholder(R.color.colorPrimary)
                .error(R.mipmap.no_image).into(showImage)
            itemView.setOnClickListener { clickListener(show) }

        }

        private fun getGenreList(genreIds: List<Int>): String {
            val showGenreList = ArrayList<String>()
            for (genreId in genreIds) {
                for (genre in genreList) {
                    if (genre.id == genreId) {
                        showGenreList.add(genre.name!!)
                        break
                    }
                }
            }
            return TextUtils.join(", ", showGenreList)
        }
    }


}