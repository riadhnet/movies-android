package com.riadh.movies.utils;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.riadh.movies.app.MyApplication;
import com.riadh.movies.models.Genre;

import java.util.List;

public class MyUtils {

    public static String getGenresString(List<Integer> genreIds, MyApplication app) {
        StringBuilder res = new StringBuilder();

        for (Genre g : app.getGenres().getGenres()) {
            for (Integer id : genreIds) {
                if (id.equals(g.getId())) {
                    res.append(g.getName()).append(", ");
                }
            }
        }

        return res.toString();
    }

    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(View view) {

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }

    }

}
