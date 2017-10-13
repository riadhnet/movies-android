package com.riadh.movies.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.riadh.movies.R;
import com.riadh.movies.adapters.itemviews.MovieItemView;
import com.riadh.movies.adapters.itemviews.MovieItemView_;
import com.riadh.movies.custom.RecyclerViewAdapterBase;
import com.riadh.movies.custom.ViewWrapper;
import com.riadh.movies.interfaces.RecyclerViewItemSimpleClick;
import com.riadh.movies.models.Result;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;


@EBean
public class MoviesAdapter extends RecyclerViewAdapterBase<Result, MovieItemView> {

    @RootContext
    Context context;

    private RecyclerViewItemSimpleClick<Result> listener;

    public void setOnItemClickListener(@NonNull RecyclerViewItemSimpleClick<Result> listener) {
        this.listener = listener;
    }

    @Override
    protected MovieItemView onCreateItemView(ViewGroup parent, int viewType) {
        return MovieItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(final ViewWrapper<MovieItemView> holder, int position) {
        MovieItemView view = holder.getView();
        final Result movie = items.get(position);

        RelativeLayout container = (RelativeLayout) view.findViewById(R.id.movie_item_container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onViewClicked(v, movie);
            }
        });

        view.bind(movie);
    }
}
