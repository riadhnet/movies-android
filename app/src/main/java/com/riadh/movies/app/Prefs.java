package com.riadh.movies.app;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;


@SharedPref(value = SharedPref.Scope.UNIQUE)
public interface Prefs {


    @DefaultString("")
    String accessToken();

    @DefaultString("")
    String genres();



}
