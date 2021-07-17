package com.corebyte.mob.kiipa.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.MenuItem;

public class AppUtil {

    public static final String CURRENCY_SYMBOL = "currency_symbol";
    public static final String APP_MODE = "use_app_as_store_keeper";
    public static final String CREDIT_LIMIT = "customer_credit_limit";

    public static Drawable tintIcon(Context context, MenuItem menuItem, int resource_id) {

        Drawable normalDraw = menuItem.getIcon();
        Drawable wrap = DrawableCompat.wrap(normalDraw);
        DrawableCompat.setTint(wrap, ContextCompat.getColor(context, resource_id));
        return wrap;
    }

    public static String getPreferenceSettings(Context context, String key, String defaultVal) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, defaultVal);
    }

    public static String formatPriceWithCurrencySymbol(Context context, Double price) {
        String currencySymbol = getPreferenceSettings(context, CURRENCY_SYMBOL, "");
        return String.valueOf(" "+currencySymbol + " " +price);
    }

    public static boolean getPreferenceSettings(Context context, String key, boolean defaultVal) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(key, defaultVal);
    }

    public static int getPreferenceSettings(Context context, String key) {
        String strDays = PreferenceManager.getDefaultSharedPreferences(context).getString(key, "10");
        return Integer.valueOf(strDays);
    }
}
