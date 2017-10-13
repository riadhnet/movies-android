package com.riadh.movies.app;


import com.riadh.movies.BuildConfig;

public class Constants {

    public static final boolean SHOW_ERROR_MESSAGE = BuildConfig.DEBUG;
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w342/";
    static final String TOKEN = "1f54bd990f1cdfb230adb312546d765d";
    private static final String HOST = BuildConfig.API_HOST;
    static final String BASE_URL = HOST;

    public static final class API {
        public static final String UP_COMING = "movie/upcoming" + "?api_key=" + TOKEN + "&language=en-US";
        public static final String MOVIES_GENRE = "genre/movie/list" + "?api_key=" + TOKEN + "&language=en-US";
        public static final String SEARCH_MOVIE = "search/movie" + "?api_key=" + TOKEN + "&language=en-US";
    }

    public class HEADER {
        public static final String ACCESS_TOKEN = "Authorization";
        public static final String CLIENT = "Client";
        public static final String UID = "Uid";
        public static final String RESOURCE_TYPE = "resource-type";
        static final String CONTENT_TYPE = "Content-Type";
        static final String APPLICATION_JSON = "application/json";
    }

    public class EXTRA {
        public static final String MOVIE = "movie";
    }
}
