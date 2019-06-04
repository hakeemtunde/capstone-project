package com.corebyte.mob.kiipa.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.PickCreditCustomerRecyclerAdapter;
import com.corebyte.mob.kiipa.event.CustomerEventHandler;
import com.corebyte.mob.kiipa.event.EventHandler;
import com.corebyte.mob.kiipa.model.Customer;

public class CustomerDialogActivity extends DialogFragment {

    public static final String CUSTOMER_KEY = "customer_key";
    private static final String TAG = CustomerDialogActivity.class.getSimpleName();
    private EventHandler mEventHandler;
    private Customer mCustomer;
    private String mDlgTitle;
    private String mPositiveBtnText;
    private boolean mIsDefault;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDlgTitle = "Add Customer";
        mPositiveBtnText = "ADD";
        mCustomer = null;
        mIsDefault = true;

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(CUSTOMER_KEY)) {
            mCustomer = bundle.getParcelable(CUSTOMER_KEY);
            mDlgTitle = "Update Customer";
            mPositiveBtnText = "Update";
        }

        Fragment fragment = getActivity().getSupportFragmentManager()
                .findFragmentByTag(CustomerEventHandler.CUSTOMER_DLG_TAG);
        if (fragment == null) { mIsDefault = false; }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setTitle(mDlgTitle);
        LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.dialog_add_customer, null);
        final EditText nameEt = view.findViewById(R.id.customer_name_et);
        final EditText phoneEt = view.findViewById(R.id.customer_phone_et);

        if (mCustomer != null) {
            nameEt.setText(mCustomer.getName());
            phoneEt.setText(mCustomer.getPhone());
        }

        dialogBuilder.setView(view);
        dialogBuilder.setPositiveButton(mPositiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                //validate
                if (TextUtils.isEmpty(nameEt.getText())) return;

                String[] params = new String[]{nameEt.getText().toString(),
                        phoneEt.getText().toString() };

                if(mIsDefault) {
                    ((CustomerEventHandler)mEventHandler).createOrUpdate(mCustomer, params);
                } else {
                    ((CustomerEventHandler)mEventHandler).createAndRefreshAdapter(params);
                }


            }
        });

        dialogBuilder.setNegativeButton(getString(R.string.cancel_string), null);

        return dialogBuilder.create();
    }

    public void setEventHandler(EventHandler action) {
        mEventHandler = action;
    }

    public void setPickCreditCustomerAdapter(PickCreditCustomerRecyclerAdapter adapter) {

    }
}
