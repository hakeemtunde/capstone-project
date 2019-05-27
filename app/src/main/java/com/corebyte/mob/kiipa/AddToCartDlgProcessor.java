package com.corebyte.mob.kiipa;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.util.AppUtil;

import java.util.List;

public class AddToCartDlgProcessor {

    private Context mContext;
    private TextView mStockNameTv;
    private TextView mPriceTv;
    private TextView mAvailableQtyTv;
    private EditText mInputQty;
    private RadioGroup mMeasurementRg;
    private Stock mStock;
    private Measurement mSelectedMeasurement;

    public AddToCartDlgProcessor(Context context, View view, Stock stock) {
        mContext = context;
        mMeasurementRg = view.findViewById(R.id.stock_measurement_rg);
        mStockNameTv = view.findViewById(R.id.stock_name_tv);
        mPriceTv = view.findViewById(R.id.stock_price_tv);
        mAvailableQtyTv = view.findViewById(R.id.stock_qty_tv);
        mInputQty = view.findViewById(R.id.quantity_ed);
        mStock = stock;

        init();
    }

    public void attachMeasurements(List<Measurement> measurements) {

        for (final Measurement measurement : measurements) {
            RadioButton radioButton = new RadioButton(mContext);
            radioButton.setText(measurement.getName());
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strPrice = AppUtil.formatPriceWithCurrencySymbol(
                            mContext, measurement.getSellingPrice());

                    mPriceTv.setText(strPrice);
                    mAvailableQtyTv.setText(String.valueOf(measurement.getAvailableQty()));

                    if (measurement.getAvailableQty() == 0) {
                        mInputQty.setEnabled(false);
                    } else {
                        if (!mInputQty.isEnabled())
                            mInputQty.setEnabled(true);
                    }


                    mSelectedMeasurement = measurement;
                }
            });
            mMeasurementRg.addView(radioButton);
        }

    }

    public Measurement getSelectedMeasurement() {
        return mSelectedMeasurement;
    }

    public int getInputQuantity() {
        int qty = 0;
        if (!TextUtils.isEmpty(mInputQty.getText().toString())) {
            try {
                qty = Integer.parseInt(mInputQty.getText().toString());
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                return 0;
            }
        }

        return qty;
    }

    private void init() {
        mStockNameTv.setText(mStock.getName());
    }
}
