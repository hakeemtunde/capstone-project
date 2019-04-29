package com.corebyte.mob.kiipa;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.corebyte.mob.kiipa.model.Measurement;

import java.util.ArrayList;
import java.util.List;

public class PublishMeasurementTable {

    static PublishMeasurementTable sInstance;
    Context mContext;
    TableLayout mTableLayout;
    TextView mMeasureTv;
    TextView mSupplyPriceTv;
    TextView mSellingPriceTv;
    TextView mQuantityTv;
    ImageView mEditIv;
    ImageView mDeleteIv;
    public List<Measurement> measurementList;

    private PublishMeasurementTable(Context context, TableLayout tableLayout) {
        mContext = context;
        mTableLayout = tableLayout;
        measurementList = new ArrayList<>();
    }

    public static PublishMeasurementTable creator(Context context, TableLayout table) {

        if (sInstance == null) {

            sInstance = new PublishMeasurementTable(context, table);
        }

        return sInstance;
    }

    public void initTableWidgets() {
        mMeasureTv = new TextView(mContext);
        mSupplyPriceTv = new TextView(mContext);
        mSellingPriceTv = new TextView(mContext);
        mQuantityTv = new TextView(mContext);
        mEditIv = new ImageView(mContext);
        mDeleteIv = new ImageView(mContext);
        mEditIv.setImageResource(R.drawable.ic_edit_black_24dp);
        mDeleteIv.setImageResource(R.drawable.ic_delete_black_24dp);
    }

    public void attachToTable(Measurement measurement) {
        setWidgetValues(measurement);
        generateRow();
    }

    public void setStockIdForMeasurements(long stockId) {
        for (int i=0; i<measurementList.size(); i++) {
            if (measurementList.get(i).getStockId() == 0){
                measurementList.get(i).setStockId(stockId);
                Log.i(this.getClass().getSimpleName(), "measurement: "+ measurementList.get(i).toString());
            }
        }
    }

    public Measurement[] getMeasurementsAsArray() {
        return measurementList.toArray(new Measurement[measurementList.size()]);
    }

    private void setWidgetValues(Measurement measurement) {

        if (measurement == null) return;

        mMeasureTv.setText(measurement.getName());
        mSupplyPriceTv.setText(String.valueOf(measurement.getSupplyPrice()));
        mSellingPriceTv.setText(String.valueOf(measurement.getSellingPrice()));
        mQuantityTv.setText(String.valueOf(measurement.getSupplyQty()));
    }

    private void generateRow() {
        TableRow tableRow = new TableRow(mContext);
        tableRow.addView(mMeasureTv);
        tableRow.addView(mSupplyPriceTv);
        tableRow.addView(mSellingPriceTv);
        tableRow.addView(mQuantityTv);
        tableRow.addView(mEditIv);
        tableRow.addView(mDeleteIv);

        mTableLayout.addView(tableRow);
    }


}
