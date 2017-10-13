package com.riadh.movies.utils;


import android.content.Context;
import android.text.TextUtils;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.riadh.movies.R;

public class DialogUtil {

    private final static int WIDGET_COLOR = R.color.yellow;
    private final static int TITLE_COLOR = R.color.black;
    private final static int CONTENT_COLOR = R.color.black;


    public static void createConfirmationDialogWithoutTitle(Context context, int message, int positiveText, int negativeText, final MaterialDialog.SingleButtonCallback listener) {
        new MaterialDialog.Builder(context)
                .content(message)
                .onPositive(listener)
                .positiveText(positiveText)
                .negativeText(negativeText)
                .titleColorRes(TITLE_COLOR)
                .contentColorRes(CONTENT_COLOR)
                .positiveColorRes(TITLE_COLOR)
                .neutralColorRes(TITLE_COLOR)
                .negativeColorRes(TITLE_COLOR)
                .cancelable(false)
                .show();
    }


    public static MaterialDialog.Builder getDefaultProgressBar(Context ctx) {
        return new MaterialDialog.Builder(ctx).content(R.string.please_wait).progress(true, 0).cancelable(false);
    }


    private static MaterialDialog.Builder getDialog(Context ctx,
                                                    int title, int content, int positiveText, int negativeText) {

        return new MaterialDialog.Builder(ctx)
                .title(title)
                .content(content)
                .positiveText(positiveText)
                .negativeText(negativeText);

    }

    public static MaterialDialog.Builder getDialog(Context ctx,
                                                   String title, String content, String positiveText) {

        return new MaterialDialog.Builder(ctx)
                .title(title)
                .content(content)
                .positiveText(positiveText);

    }

    public static MaterialDialog.Builder getUnexpectedErrorDialog(Context ctx, String detail) {

        return getDialog(ctx,
                ctx.getString(R.string.unexpected_error_title),
                String.format(ctx.getString(R.string.unexpected_error_content), detail),
                ctx.getString(R.string.dialog_close));
    }


    public static MaterialDialog.Builder getNetworkErrorDialog(Context ctx) {

        return getDialog(ctx, R.string.dialog_no_connection_title,
                R.string.dialog_no_connection_content,
                R.string.dialog_no_connection_try_again,
                R.string.dialog_no_connection_cancel)
                .cancelable(false);

    }

    public static MaterialDialog.Builder getDialogNonCancelable(Context ctx,
                                                                String title, String content, String positiveText) {

        return new MaterialDialog.Builder(ctx)
                .title(title)
                .content(content)
                .cancelable(false)
                .positiveText(positiveText);

    }

    public static void ChoiceDialog(Context context, String[] items, String strTitle, MaterialDialog.ListCallbackSingleChoice listCallbackSingleChoice) {
        new MaterialDialog.Builder(context)
                .title(strTitle)
                .items((CharSequence[]) items)
                .titleColorRes(TITLE_COLOR)
                .itemsCallbackSingleChoice(0, listCallbackSingleChoice)
                .positiveText(R.string.ok)
                .theme(Theme.DARK)
                .show();
    }

    public static MaterialDialog createInformationDialog(Context context, String message, final MaterialDialog.SingleButtonCallback listener) {

        return new MaterialDialog.Builder(context)
                .content(message)
                .neutralText(R.string.ok)
                .onNeutral(listener)
                .titleColorRes(TITLE_COLOR)
                .contentColorRes(CONTENT_COLOR)
                .titleGravity(GravityEnum.CENTER)
                .contentGravity(GravityEnum.CENTER)
                .buttonsGravity(GravityEnum.CENTER)
                .cancelable(false)
                .show();
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


}
