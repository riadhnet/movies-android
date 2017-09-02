package com.riadh.movies.interfaces;

import android.view.View;

public interface RecyclerViewItemSimpleClick<T> {

    void onViewClicked(View view, T item);

}