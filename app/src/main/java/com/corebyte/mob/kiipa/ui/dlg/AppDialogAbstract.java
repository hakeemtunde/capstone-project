package com.corebyte.mob.kiipa.ui.dlg;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import com.corebyte.mob.kiipa.R;

public class AppDialogAbstract extends DialogFragment {

    private Context mContext;
    private int dialogLayoutResourceId;
    private DialogPresenter dialogPresenter;
    private String dlgTitle;
    protected View dlgView;
    public AppDialogAbstract() {}

    public AppDialogAbstract(Context context,
                             View view,
                             int layoutResourceId,
                             String title,
                             DialogPresenter listener) {
        mContext = context;
        dialogLayoutResourceId = layoutResourceId;
        dialogPresenter = listener;
        dlgTitle = title;
        dlgView = view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);

//        dlgView = LayoutInflater.from(mContext).inflate(dialogLayoutResourceId, null);
//        ContextThemeWrapper themeWrapper = new ContextThemeWrapper(mContext, R.style.Theme_AppCompat);
//        requireActivity().getLayoutInflater().inflate(dialogLayoutResourceId, null);
        dialogBuilder.setView(dlgView);
        dialogBuilder.setTitle(dlgTitle);
        dialogBuilder.setPositiveButton("Settle", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogPresenter.getInput();
            }
        });
        return dialogBuilder.create();
    }
}
