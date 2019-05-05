package com.corebyte.mob.kiipa.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.corebyte.mob.kiipa.MeasurementDlgProcessor;
import com.corebyte.mob.kiipa.MeasurementDlgProcessor.MeasurementHandler;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Measurement;

public class MeasureDialog extends DialogFragment implements AlertDialog.OnClickListener {

    private static final String TAG = MeasureDialog.class.getSimpleName();
    private MeasurementDlgProcessor measureDialogWidget;
    private MeasurementHandler mHandler;
    public static final String MEASUREMENT_TAG = "MEASUREMENT_ITEM";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogue = new AlertDialog.Builder(getActivity());
        dialogue.setTitle("Add Measurement");
        String positiveBtnText = getString(R.string.ok_string);

        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_measurement_layout, null);
        dialogue.setView(view);

        measureDialogWidget = new MeasurementDlgProcessor(view);

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(MEASUREMENT_TAG)) {
            dialogue.setTitle("Edit Measurement");
            positiveBtnText = getString(R.string.update_string);
            Measurement measurement = bundle.getParcelable(MEASUREMENT_TAG);
            measureDialogWidget.setupParameters(measurement);
        }

        dialogue.setPositiveButton(positiveBtnText, this);

        dialogue.setNegativeButton(R.string.cancel_string, null);

        return dialogue.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        mHandler.attach(measureDialogWidget.getUiMeasurement());
    }

    public void setMeasurementHandler(MeasurementHandler handler) {
        mHandler = handler;
    }

}
