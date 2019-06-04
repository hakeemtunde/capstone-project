package com.corebyte.mob.kiipa;

import android.arch.persistence.room.util.StringUtil;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.text.DateFormat;
import java.util.Date;

import butterknife.internal.Utils;

public class MeasurementDlgProcessor {

    private EditText nameEd;
    private EditText supplyPriceEd;
    private EditText sellingPriceEd;
    private EditText supplyQtyEd;
    private EditText lastSupplyQtyEd;
    private TextView lastSupplyDate;
    private CheckBox showCb;

    private Measurement mMeasurement;

    public MeasurementDlgProcessor(View view) {
        nameEd = view.findViewById(R.id.ed_measure);
        supplyPriceEd = view.findViewById(R.id.ed_supply_price);
        sellingPriceEd = view.findViewById(R.id.ed_selling_price);
        supplyQtyEd = view.findViewById(R.id.ed_supply_qty);
        lastSupplyQtyEd = view.findViewById(R.id.ed_last_supply_qty);
        lastSupplyDate = view.findViewById(R.id.tv_last_supply_date);

        showCb = view.findViewById(R.id.cb_show);
    }

    public void setupParameters(Measurement measurement) {

        if (measurement == null) return;

        mMeasurement = measurement;

        nameEd.setText(measurement.getName());
        supplyPriceEd.setText(String.valueOf(measurement.getSupplyPrice()));
        sellingPriceEd.setText(String.valueOf(measurement.getSellingPrice()));
        lastSupplyQtyEd.setText(String.valueOf(measurement.getSupplyQty()));
        lastSupplyDate.setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(measurement.updatedAt));

        if (measurement.getShowStatus() == 1) {
            showCb.setChecked(true);
        } else {
            showCb.setChecked(false);
        }
    }


    public Measurement getUiMeasurement() {

        if (!isFormValid()) return null;

        if (mMeasurement == null) {
            mMeasurement = new Measurement();
        }

        mMeasurement.setName(nameEd.getText().toString());
        mMeasurement.setSellingPrice(
                Double.parseDouble(sellingPriceEd.getText().toString()));
        mMeasurement.setSupplyPrice(
                Double.parseDouble(supplyPriceEd.getText().toString()));


        int qty =  TextUtils.isEmpty(supplyQtyEd.getText().toString()) ? 0
                : Integer.parseInt(supplyQtyEd.getText().toString());
        mMeasurement.setSupplyQty(qty);
        mMeasurement.setLastSupplyQty(qty);
        mMeasurement.updateAvailableQty();

        if (showCb.isChecked()) {
            mMeasurement.setShowStatus(1);
        } else {
            mMeasurement.setShowStatus(0);
        }

        return mMeasurement;
    }

    public interface MeasurementHandler {
        void attach(Measurement measurement);
    }

    private boolean isFormValid() {


        if (TextUtils.isEmpty(nameEd.getText()) || TextUtils.isEmpty(supplyPriceEd.getText()) ||
                !TextUtils.isDigitsOnly(supplyPriceEd.getText()) || TextUtils.isEmpty(sellingPriceEd.getText())
            || !TextUtils.isDigitsOnly(sellingPriceEd.getText()) || TextUtils.isEmpty(supplyQtyEd.getText())
            || !TextUtils.isDigitsOnly(supplyQtyEd.getText()) ) {

            return false;
        }

        return true;

    }
}
