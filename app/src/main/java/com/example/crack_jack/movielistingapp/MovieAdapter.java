package com.example.crack_jack.movielistingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by CRACK-JACK on 07-12-2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private List<MovieDetails> movieList;
    //private List<MovieModel> movieModels = new ArrayList<>();
    //private Activity mActivity;

    public MovieAdapter(Context mContext, List<MovieDetails> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }



/*    MovieAdapter(Activity mActivity) {
        this.mActivity = mActivity;

    }
*/
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.MovieViewHolder holder, int position) {

        //MovieModel movieModel = movieModels.get(position);
        //holder.bindTo(movieModel);

        holder.movieName.setText(movieList.get(position).getOriginalTitle());

        holder.releaseDate.setText(movieList.get(position).getReleaseDate());

        String vote = Double.toString(movieList.get(position).getVoteAverage());
        holder.movieRating.setText(vote);

        Glide.with(mContext)
                .load(movieList.get(position).getPosterPath())
                .into(holder.movieImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private TextView movieName, releaseDate, movieRating;
        private ImageView movieImage;

        public MovieViewHolder(View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.imageView);
            movieName = itemView.findViewById(R.id.movie_title);
            movieRating = itemView.findViewById(R.id.movie_rating);
            releaseDate = itemView.findViewById(R.id.movie_release);

/*            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        MovieDetails ClickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(pos).getOriginalTitle());
                        intent.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        intent.putExtra("overview", movieList.get(pos).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(pos).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked"+ClickedDataItem.getOriginalTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
        }

/*        public void bindTo(MovieModel movieModel) {

            movieName.setText(movieModel.movieName);
            movieRating.setText("Rating : " + movieModel.rating);
            releaseDate.setText(movieModel.releaseDate);

            Glide.with(super.itemView).load(movieModel.ImageURI).into(movieImage);
        }*/
    }

/*    public void addMovieItem (MovieModel item) {
        movieModels.add(item);
        notifyItemInserted(movieModels.size());
    }*/

}
