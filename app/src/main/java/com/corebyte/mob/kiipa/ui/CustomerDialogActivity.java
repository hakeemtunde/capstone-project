package com.corebyte.mob.kiipa.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.StockDialogAction.StockDialogGenericAction;

public class CustomerDialogActivity extends DialogFragment {

    private StockDialogGenericAction mDialogAction;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        dialogBuilder.setTitle("Add Customer");

        final View view = layoutInflater.inflate(R.layout.dialog_add_customer, null);
        final EditText nameEt = view.findViewById(R.id.customer_name_et);
        final EditText phoneEt = view.findViewById(R.id.customer_phone_et);

        dialogBuilder.setView(view);
        dialogBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //validate
                String[] params = new String[2];
                params[0] = nameEt.getText().toString();
                params[1] = phoneEt.getText().toString();
                mDialogAction.create(params);
            }
        });



        return dialogBuilder.create();
    }

    public void setDialogAction(StockDialogGenericAction action) {
        mDialogAction = action;
    }
}
