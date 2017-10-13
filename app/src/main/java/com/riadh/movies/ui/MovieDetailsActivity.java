package com.riadh.movies.ui;


import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ImageView;
import android.widget.TextView;

import com.riadh.movies.R;
import com.riadh.movies.app.Constants;
import com.riadh.movies.app.MyApplication;
import com.riadh.movies.models.Result;
import com.riadh.movies.ui.fragments.HomeFragment;
import com.riadh.movies.utils.GlideUtils;
import com.riadh.movies.utils.MyUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_movie_details)
public class MovieDetailsActivity extends BaseActivity {


    @ViewById(R.id.movie_bg)
    ImageView movieImg;

    @ViewById(R.id.movie_title)
    TextView title;
    @ViewById(R.id.genre)
    TextView genre;
    @ViewById(R.id.date)
    TextView date;
    @ViewById(R.id.overview)
    TextView overview;

    @Extra(Constants.EXTRA.MOVIE)
    Result movie;


    @App
    MyApplication app;

    HomeFragment homeFragment;

    @ViewById
    TextView drawerHome;
    @ViewById
    TextView drawerSearch;


    private ActionBarDrawerToggle sideMenuToggle;
    private boolean doubleBackToExitPressedOnce = false;

    @AfterViews
    public void onScreenLoaded() {
        configActionBar(true);
        if (movie == null) {
            finish();
        }

        GlideUtils.loadImage(Constants.IMAGE_URL + movie.getPosterPath(), this, movieImg);
        title.setText(movie.getTitle());
        genre.setText(MyUtils.getGenresString(movie.getGenreIds(), app));
        date.setText(movie.getReleaseDate());
        overview.setText(movie.getOverview());

    }


}
