package com.riadh.movies.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class GlideUtils {

    public static void loadImageCropCircle(Object url, Context with, ImageView into) {
        if (with == null) {
            return;
        }
        try {
            Glide.with(with).load(url)
                    .bitmapTransform(new CropCircleTransformation(with))
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(into);
        } catch (Exception ignored) {

        }

    }

    public static void loadImageCropCircleIntoTextViewDrawable(Object url, Context with, final TextView into, int width, int height) {
        if (with == null) {
            return;
        }
        Glide.with(with)
                .load(url)
                .bitmapTransform(new CropCircleTransformation(with))
                .into(new SimpleTarget<GlideDrawable>(width, height) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        into.setCompoundDrawablesWithIntrinsicBounds(resource, null, null, null);
                    }
                });
    }

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
