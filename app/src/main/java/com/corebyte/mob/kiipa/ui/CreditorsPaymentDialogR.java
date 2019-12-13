package com.corebyte.mob.kiipa.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.ui.dlg.HandleCustomerPayment;

public class CreditorsPaymentDialogR extends DialogFragment {
    private static final String TAG = CreditorsPaymentDialogR.class.getSimpleName();
    private Customer mCustomer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCustomer = (Customer) getArguments().get(CustomerActivity.CUSTOMER_KEY);
        Log.i(TAG, "CUSTOMER "+ mCustomer.toString());

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dlg_creditors_payment, null);
        dialogBuilder.setTitle("Payment");
        dialogBuilder.setView(view);

        final TextView customersNameTv = view.findViewById(R.id.customers_name_tv);
        final TextInputEditText amountEd = view.findViewById(R.id.amount_ti);
        final TextInputEditText remarkEd = view.findViewById(R.id.remark_ti);
        final RadioGroup paymentOptionRadioButton =view.findViewById(R.id.payment_option);
        paymentOptionRadioButton.check(R.id.cash_payment);

        customersNameTv.setText(mCustomer.getName());

        dialogBuilder.setPositiveButton("Payment", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String amtStr = amountEd.getText().toString();
                String remarkStr = remarkEd.getText().toString();

                if (TextUtils.isEmpty(amtStr) ) {
                    return;
                }

                HandleCustomerPayment.PaymentData data = new HandleCustomerPayment.PaymentData();
                data.customer = mCustomer;
                data.amount = Double.parseDouble(amtStr);
                data.remark = remarkStr;

                data.paymentType = paymentOptionRadioButton.getCheckedRadioButtonId()
                        == R.id.cash_payment ? "Cash" : "Bank transfer";

                HandleCustomerPayment.handlePayment(getContext(), data);
            }
        });


        return dialogBuilder.create();
    }
}
