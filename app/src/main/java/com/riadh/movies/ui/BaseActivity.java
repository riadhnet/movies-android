package com.riadh.movies.ui;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.riadh.movies.R;
import com.riadh.movies.app.Constants;
import com.riadh.movies.models.ErrorResponse;
import com.riadh.movies.service.NetworkRetryRequest;
import com.riadh.movies.utils.DialogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class BaseActivity extends AppCompatActivity {


    private MaterialDialog progressDialog;


    protected void configActionBar(boolean displayHomeButton) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeButton);
            getSupportActionBar().setDisplayShowHomeEnabled(displayHomeButton);
            getSupportActionBar().setDisplayShowTitleEnabled(displayHomeButton);
        }
    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = DialogUtil.getDefaultProgressBar(this).build();
        }
        try {
            progressDialog.show();
        } catch (Exception ignored) {

        }
    }


    public void hideProgressDialog() {
        if (progressDialog == null) {
            progressDialog = DialogUtil.getDefaultProgressBar(this).build();
        }
        try {
            progressDialog.dismiss();
        } catch (Exception ignored) {

        }
    }


    public void handleUnexpectedError(Throwable t) {
        String message = t.getMessage();
        if (message == null)
            message = t.toString();

        DialogUtil.getUnexpectedErrorDialog(this, message)
                .build()
                .show();
    }

    public void handleAPIErrors(ResponseBody responseBody) {

        try {
            handleAPIErrors(responseBody.string());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleAPIErrors(String responseString) {
        ErrorResponse errorResponse = null;
        Gson gson = new Gson();

        try {
            errorResponse = gson.fromJson(responseString, ErrorResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = null;

        if (errorResponse != null) {
            if (!TextUtils.isEmpty(errorResponse.getStatusMessage())) {
                message = errorResponse.getStatusMessage();
            } else if (errorResponse.getErrors() != null && errorResponse.getErrors().size() > 0) {
                message = errorResponse.getErrors().get(0);
            }
        }
        if (message == null) {

            if (Constants.SHOW_ERROR_MESSAGE) {
                try {
                    message = String.format(
                            getString(R.string.unexpected_error_content), responseString);
                } catch (Exception e) {

                    message = String.format(
                            getString(R.string.unexpected_error_content), "");
                }
            } else {
                message = String.format(
                        getString(R.string.unexpected_error_content), "");
            }

        }

        try {
            DialogUtil.getDialog(this, null, message,
                    getString(R.string.dialog_close)).build().show();
        } catch (Exception ignored) {

        }

    }

    public void onNetworkError(final NetworkRetryRequest networkRetryRequest) {
        if (getApplicationContext() == null) return;


        try {
            DialogUtil.getNetworkErrorDialog(this)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {

                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            networkRetryRequest.retry();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {

                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    }).build().show();
        } catch (Exception ignored) {

        }
    }

    public void handleUnAuthorizedRequest(Response response) {
        logOutUnAuthorizedUser();
    }

    public void logOutUnAuthorizedUser() {
        DialogUtil.getDialogNonCancelable(this, null, getString(R.string.not_authorized_error), getString(R.string.ok)).onAny(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            try {
                onBackPressed();
            } catch (Exception ignored) {

            }
        }
        return super.onOptionsItemSelected(item);
    }

}
