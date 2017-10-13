package com.riadh.movies.adapters.itemviews;


import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.riadh.movies.R;
import com.riadh.movies.app.Constants;
import com.riadh.movies.app.MyApplication;
import com.riadh.movies.models.Result;
import com.riadh.movies.utils.GlideUtils;
import com.riadh.movies.utils.MyUtils;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;


@EViewGroup(R.layout.movie_item)
public class MovieItemView extends RelativeLayout {


    Context mContext;


    @ViewById(R.id.movie_title)
    TextView title;
    @ViewById(R.id.genre)
    TextView genre;
    @ViewById(R.id.date)
    TextView date;
    @ViewById(R.id.video_bg)
    ImageView bg;


    public MovieItemView(Context context) {
        super(context);
        mContext = context;
    }

    public void bind(Result movie) {
        GlideUtils.loadImage(Constants.IMAGE_URL + movie.getBackdropPath(), mContext, bg);
        title.setText(movie.getTitle());
        genre.setText(MyUtils.getGenresString(movie.getGenreIds(), (MyApplication) mContext.getApplicationContext()));
        date.setText(movie.getReleaseDate());

    }

}
