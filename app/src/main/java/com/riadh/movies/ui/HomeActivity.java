package com.riadh.movies.ui;


import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.riadh.movies.R;
import com.riadh.movies.app.MyApplication;
import com.riadh.movies.ui.fragments.HomeFragment;
import com.riadh.movies.ui.fragments.HomeFragment_;
import com.riadh.movies.ui.fragments.SearchFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {


    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.toolbar)
    Toolbar homeToolbar;


    @App
    MyApplication app;

    HomeFragment homeFragment;

    @ViewById
    TextView drawerHome;
    @ViewById
    TextView drawerSearch;


    private ActionBarDrawerToggle sideMenuToggle;
    private boolean doubleBackToExitPressedOnce = false;

    @AfterViews
    public void onScreenLoaded() {
        setSupportActionBar(homeToolbar);
        configActionBar(true);


        setupDrawer();
        homeFragment = new HomeFragment_();
        replaceFragment(homeFragment, getString(R.string.home_fragment_tag));

    }

    private void setupDrawer() {
        sideMenuToggle = new ActionBarDrawerToggle(this, mDrawerLayout, homeToolbar, R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                disableAllMenuBg();
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment != null && fragment.isVisible()) {
                        if (fragment instanceof HomeFragment_) {
                            drawerHome.setActivated(true);
                        } else if (fragment instanceof SearchFragment_) {
                            drawerSearch.setActivated(true);
                        }
                    }
                }
            }
        };

        mDrawerLayout.addDrawerListener(sideMenuToggle);
        sideMenuToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return sideMenuToggle.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_frame, fragment, tag);
        ft.commit();
    }


    public void setActivityTitle(int txt) {
        if (getSupportActionBar() != null && getSupportActionBar().getCustomView() != null) {
            ((TextView) getSupportActionBar().getCustomView().findViewById(R.id.title)).setText(txt);
            getSupportActionBar().getCustomView().findViewById(R.id.title).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.click_to_exit, Toast.LENGTH_SHORT).show();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        } else {
            super.onBackPressed();
        }
    }

    @Click(R.id.drawer_home)
    public void homClick() {
        mDrawerLayout.closeDrawers();
        disableAllMenuBg();
        drawerHome.setActivated(true);
        replaceFragment(homeFragment, getString(R.string.home_fragment_tag));
    }

    @Click(R.id.drawer_search)
    public void searchClick() {
        mDrawerLayout.closeDrawers();
        disableAllMenuBg();
        drawerSearch.setActivated(true);
        replaceFragment(new SearchFragment_(), getString(R.string.search_fragment_tag));
    }


    private void disableAllMenuBg() {
        drawerHome.setActivated(false);
        drawerSearch.setActivated(false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        sideMenuToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        sideMenuToggle.onConfigurationChanged(newConfig);
    }

}
