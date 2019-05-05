package com.corebyte.mob.kiipa;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.ui.MeasureDialog;
import com.corebyte.mob.kiipa.ui.StockItemActivity;

import java.util.ArrayList;
import java.util.List;

public class PublishMeasurementTable {

    public List<Measurement> measurementList;
    Context mContext;
    TableLayout mTableLayout;
    TextView mMeasureTv;
    TextView mSupplyPriceTv;
    TextView mSellingPriceTv;
    TextView mQuantityTv;
    ImageView mEditIv;
    ImageView mDeleteIv;
    StockItemActivity mStockItemActivity;

    public PublishMeasurementTable(Activity activity, TableLayout tableLayout) {
        mTableLayout = tableLayout;
        measurementList = new ArrayList<>();
        mStockItemActivity = (StockItemActivity) activity;
        mContext = mStockItemActivity.getApplicationContext();
    }

//    public static PublishMeasurementTable creator(Context context, TableLayout table) {
//
//        if (sInstance == null) {
//
//            sInstance = new PublishMeasurementTable(context, table);
//        }
//
//        return sInstance;
//    }

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

    public void attachToTable(List<Measurement> measurements) {
        for (Measurement measurement : measurements) {
            attachToTable(measurement);
        }
    }

    public void attachToTable(Measurement measurement) {
        initTableWidgets();
        setWidgetValues(measurement);
        generateRow();
        measurementList.add(measurement);

    }

    public void setStockId(long stockId) {
        for (int i = 0; i < measurementList.size(); i++) {
            if (measurementList.get(i).getStockId() == 0) {
                measurementList.get(i).setStockId(stockId);
            }
        }
    }

    public void setStockId(long stockId, Measurement[] measurements) {
        for (Measurement measurement : measurements) {
            measurement.setStockId(stockId);
        }
    }

    public Measurement[] getMeasurementsAsArray() {
        return measurementList.toArray(new Measurement[measurementList.size()]);
    }

    public Measurement[] getNewMeasurements() {
        List<Measurement> newMeasurements = new ArrayList<>();

        for(Measurement measurement : measurementList) {
            if (measurement.id != 0) continue;
            newMeasurements.add(measurement);
        }
        return newMeasurements.toArray(new Measurement[newMeasurements.size()]);
    }

    public Measurement[] getExistingMeasurements() {
        List<Measurement> existingMeasurements = new ArrayList<>();

        for(Measurement measurement : measurementList) {
            if (measurement.id == 0) continue;
            existingMeasurements.add(measurement);
        }
        return existingMeasurements.toArray(new Measurement[existingMeasurements.size()]);
    }

    private void setWidgetValues(final Measurement measurement) {

        if (measurement == null) return;

        mMeasureTv.setText(measurement.getName());
        mSupplyPriceTv.setText(String.valueOf(measurement.getSupplyPrice()));
        mSellingPriceTv.setText(String.valueOf(measurement.getSellingPrice()));
        mQuantityTv.setText(String.valueOf(measurement.getSupplyQty()));

        mEditIv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                MeasureDialog dialog = new MeasureDialog();
                dialog.setMeasurementHandler(new MeasurementDlgProcessor.MeasurementHandler() {
                    @Override
                    public void attach(Measurement measurement) {
                        reAttachedEdited(measurement);
                    }
                });

                Bundle bundle = new Bundle();
                bundle.putParcelable(MeasureDialog.MEASUREMENT_TAG, measurement);
                dialog.setArguments(bundle);
                dialog.show(mStockItemActivity.getSupportFragmentManager(), "EDIT");

            }
        });
    }

    private void reAttachedEdited(Measurement measurement) {
        int index = 0;
        for(Measurement m : measurementList) {
            if (m.id != measurement.id) continue;
            index = measurementList.indexOf(m);
            measurementList.set(index, measurement);
        }

        initTableWidgets();
        setWidgetValues(measurement);
        TableRow tableRow = createRow();
        mTableLayout.removeViewAt(index+1);
        mTableLayout.addView(tableRow, index+1);
        measurementList.set(index, measurement);
    }

    private TableRow createRow() {
        TableRow tableRow = new TableRow(mContext);
        tableRow.addView(mMeasureTv);
        tableRow.addView(mSupplyPriceTv);
        tableRow.addView(mSellingPriceTv);
        tableRow.addView(mQuantityTv);
        tableRow.addView(mEditIv);
        tableRow.addView(mDeleteIv);
        return tableRow;
    }

    private void generateRow() {
        TableRow tableRow = createRow();
        mTableLayout.addView(tableRow);
    }


}
