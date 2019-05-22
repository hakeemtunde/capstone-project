package com.corebyte.mob.kiipa.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

public class AppUtil {

    public static Drawable tintIcon(Context context, MenuItem menuItem, int resource_id) {

        Drawable normalDraw = menuItem.getIcon();
        Drawable wrap = DrawableCompat.wrap(normalDraw);
        DrawableCompat.setTint(wrap, ContextCompat.getColor(context, resource_id));
        return wrap;
    }
}
