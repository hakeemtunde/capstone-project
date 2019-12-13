package com.corebyte.mob.kiipa.ui.dlg;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.corebyte.mob.kiipa.R;

public class CreditorsPaymentDlg extends DialogPresenter {

    private static final String TAG = CreditorsPaymentDlg.class.getSimpleName();


    public CreditorsPaymentDlg(Context context, View view, FragmentManager fmgr,
                               int resId, String title) {
        super(context, fmgr, view, resId, title);
    }

    @Override
    public void getInput() {

        //check
        if (getDialogView() == null) return;

        EditText amountTi = getDialogView().findViewById(R.id.amount_ti);

        Log.i(TAG, "Cost: " + amountTi.getText().toString());

    }



}
