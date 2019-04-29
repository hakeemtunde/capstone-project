package com.corebyte.mob.kiipa;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.corebyte.mob.kiipa.model.Measurement;

import java.util.Date;

public class MeasurementDlgProcessor {

    private EditText nameEd;
    private EditText supplyPriceEd;
    private EditText sellingPriceEd;
    private EditText supplyQtyEd;
    private EditText lastSupplyQtyEd;
    private CheckBox showCb;

    public MeasurementDlgProcessor(View view) {
        nameEd = view.findViewById(R.id.ed_measure);
        supplyPriceEd = view.findViewById(R.id.ed_supply_price);
        sellingPriceEd = view.findViewById(R.id.ed_selling_price);
        supplyQtyEd = view.findViewById(R.id.ed_supply_qty);
        lastSupplyQtyEd = view.findViewById(R.id.ed_last_supply_qty);
        showCb = view.findViewById(R.id.cb_show);
    }

    public void setupParameters(Measurement measurement) {

        if (measurement == null) return;

        nameEd.setText(measurement.getName());
        supplyPriceEd.setText(String.valueOf(measurement.getSupplyPrice()));
        sellingPriceEd.setText(String.valueOf(measurement.getSellingPrice()));
        lastSupplyQtyEd.setText(String.valueOf(measurement.getLastSupplyQty()));
        lastSupplyQtyEd.setText(measurement.getLastSupplyDate().toLocaleString());

        if (measurement.getShowStatus() == 1) {
            showCb.setChecked(true);
        } else {
            showCb.setChecked(false);
        }
    }


    public Measurement getUiMeasurement() {
        Measurement measurement = new Measurement();

        measurement.createdAt = new Date();
        measurement.updatedAt = new Date();

        measurement.setName(nameEd.getText().toString());
        measurement.setSellingPrice(
                Double.parseDouble(sellingPriceEd.getText().toString()));
        measurement.setSupplyPrice(
                Double.parseDouble(supplyPriceEd.getText().toString()));
        measurement.setSupplyQty(
                Integer.parseInt(supplyQtyEd.getText().toString()));

        if (showCb.isChecked()) {
            measurement.setShowStatus(1);
        } else {
            measurement.setShowStatus(0);
        }

        return measurement;
    }

    public interface MeasurementHandler {
        void attach(Measurement measurement);
    }
}
