package com.riadh.movies.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class GlideUtils {


    public static void loadImage(Object url, Context with, ImageView into) {
        if (with == null) {
            return;
        }
        try {
            Glide.with(with).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .crossFade()
                    .into(into);
        } catch (Exception ignored) {

        }

    }
}
