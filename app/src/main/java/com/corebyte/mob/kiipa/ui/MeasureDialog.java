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
import android.view.ViewGroup;
import android.widget.Toast;

import com.corebyte.mob.kiipa.R;

public class MeasureDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogue = new AlertDialog.Builder(getActivity());
        dialogue.setTitle("Add Measurement");
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_measurement_layout, null);
        dialogue.setView(view);

        dialogue.setPositiveButton(R.string.ok_string, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "Click Ok", Toast.LENGTH_SHORT).show();
            }
        });

        dialogue.setNegativeButton(R.string.cancel_string, null);

        return dialogue.create();
    }
}
