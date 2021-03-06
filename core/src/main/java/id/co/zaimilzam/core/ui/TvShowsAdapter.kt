package id.co.zaimilzam.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import id.co.zaimilzam.core.R
import id.co.zaimilzam.core.databinding.ItemListMovieBinding
import id.co.zaimilzam.core.domain.model.TvShows

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.MovieViewHolder>() {

    private var listData = ArrayList<TvShows>()
    var onItemClick: ((TvShows) -> Unit)? = null

    fun setData(newListData: List<TvShows>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listData[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListMovieBinding.bind(itemView)
        fun bind(movie: TvShows) {
            // set circular progress loading
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            val image = "https://image.tmdb.org/t/p/original${movie.image}"

            Glide.with(itemView.context)
                .load(image)
                .placeholder(circularProgressDrawable)
                .into(binding.imgPoster)

            binding.tvTittleMovie.text = movie.tittle
            binding.tvGenreMovie.text = movie.description
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }


}