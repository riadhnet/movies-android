package com.riadh.movies.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.riadh.movies.R;
import com.riadh.movies.adapters.MoviesAdapter;
import com.riadh.movies.app.Constants;
import com.riadh.movies.app.MyApplication;
import com.riadh.movies.custom.RecyclerViewEmptySupport;
import com.riadh.movies.interfaces.RecyclerViewItemSimpleClick;
import com.riadh.movies.models.MoviesResults;
import com.riadh.movies.models.Result;
import com.riadh.movies.service.MyCallbackApi;
import com.riadh.movies.ui.BaseActivity;
import com.riadh.movies.ui.MovieDetailsActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import retrofit2.Response;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment implements RecyclerViewItemSimpleClick<Result> {

    @ViewById(R.id.progress_view)
    CircularProgressView progressView;

    @ViewById(R.id.empty_movies)
    View emptyView;

    @App
    MyApplication app;

    @ViewById(R.id.movies_list)
    RecyclerViewEmptySupport dreamsList;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bean
    MoviesAdapter feedAdapter;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    LinearLayoutManager lm;

    private MoviesResults moviesResults;

    private boolean loading = true;
    private boolean isCallingApi = false;

    @AfterViews
    void onScreenLoaded() {
        ((BaseActivity) getActivity()).configActionBarWithTitle(R.string.upcomming);
        populateMovies();

    }

    private void populateMovies() {
        setupPropertiesAdapter();
        getMovies(1);
    }

    private void setupPropertiesAdapter() {

        lm =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        dreamsList.setLayoutManager(lm);
        feedAdapter.setOnItemClickListener(this);

        dreamsList.setAdapter(feedAdapter);
        dreamsList.setEmptyView(emptyView);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies(1);
            }
        });
        swipeRefreshLayout.setColorSchemeResources(R.color.yellow);


        dreamsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    pastVisibleItems = lm.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = false;

                        if (moviesResults.getPage() < moviesResults.getTotalPages() && !isCallingApi) {
                            getMovies(moviesResults.getPage() + 1);
                        }
                    }
                }
            }


        });


    }

    public void getMovies(final Integer page) {
        loading = true;
        MyApplication.getApiService().getLatest(page).enqueue(new MyCallbackApi<MoviesResults, BaseActivity>((BaseActivity) getActivity()) {

            @Override
            public void before() {
                if (emptyView == null) return;
                isCallingApi = true;
                emptyView.setVisibility(View.GONE);
                if (!swipeRefreshLayout.isRefreshing()) {
                    progressView.setVisibility(View.VISIBLE);
                }
                if (page == 1) {
                    dreamsList.setVisibility(View.GONE);
                }
            }

            @Override
            public void after() {
                if (emptyView == null) return;
                if (progressView != null && dreamsList != null && swipeRefreshLayout != null) {
                    isCallingApi = false;
                    progressView.setVisibility(View.GONE);
                    dreamsList.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onSuccess(Response<MoviesResults> response) {
                if (getActivity() == null) return;
                moviesResults = response.body();
                if (moviesResults.getPage() == 1) {
                    feedAdapter.setData(moviesResults.getResults());
                } else {
                    feedAdapter.addData(moviesResults.getResults());
                }
            }

        });
    }


    @Override
    public void onViewClicked(View view, Result item) {
        MovieDetailsActivity_.intent(this).extra(Constants.EXTRA.MOVIE, item).start();
    }
}
