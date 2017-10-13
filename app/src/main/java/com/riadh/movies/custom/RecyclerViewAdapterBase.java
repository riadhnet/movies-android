package com.riadh.movies.custom;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A common base class for all RecyclerView adapters
 * Created by Riadh on 26/07/2016.
 */
public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected List<T> items = new ArrayList<>();
    protected List<T> itemsCopy = new ArrayList<>();

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);


    public void setData(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        this.itemsCopy.clear();
        this.itemsCopy.addAll(items);
        this.notifyDataSetChanged();
    }

    public void addDataToTop(List<T> items) {
        this.items.addAll(0, items);
        this.itemsCopy.addAll(0, items);
        this.notifyDataSetChanged();
    }

    public void addData(List<T> items) {
        this.items.addAll(items);
        this.itemsCopy.addAll(items);
        this.notifyDataSetChanged();
    }

    public void addItem(T items) {
        this.items.add(items);
        this.notifyDataSetChanged();
    }

    public List<T> getItems() {
        return items;
    }

    // additional methods to manipulate the items
}
