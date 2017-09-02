package com.riadh.movies.custom;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * A generic class which can be used to wrap all kind of Views into a ViewHolder
 */
public class ViewWrapper<V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);

        view = itemView;
    }

    public V getView() {
        return view;
    }
}