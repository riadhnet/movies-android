package com.riadh.movies.utils;


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

}
