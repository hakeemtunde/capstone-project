package com.corebyte.mob.kiipa.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.corebyte.mob.kiipa.AddToCartDlgProcessor;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.event.StockEvent;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.MeasurementCrudOperation;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;

import java.util.List;

public class AddToCartDialogActivity extends DialogFragment implements AlertDialog.OnClickListener{

    AddToCartDlgProcessor mDlgProcessor;
    StockEvent mStockEvent;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Stock stock = null;
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(StockItemActivity.STOCKITEM)) {
            stock = bundle.getParcelable(StockItemActivity.STOCKITEM);
        }

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Add To Cart");

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addtocart, null);
        dialog.setView(view);

        MeasurementCrudOperation measurementCrudOperation = new MeasurementCrudOperation(getContext());
        List<Measurement> measurements = measurementCrudOperation.findByStockId(stock.id);

        mDlgProcessor = new AddToCartDlgProcessor(getContext(), view, stock);
        mDlgProcessor.attachMeasurements(measurements);

        dialog.setPositiveButton(getString(R.string.ok_string), this);
        dialog.setNegativeButton(getString(R.string.cancel_string), null);

        return dialog.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Measurement measurement = mDlgProcessor.getSelectedMeasurement();
        int qty = mDlgProcessor.getInputQuantity();
        mStockEvent.onAddToCart(measurement, qty);
    }

    public void setOnStockEventHandler(StockEvent eventHandler) {
        mStockEvent = eventHandler;
    }
}
