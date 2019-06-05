package com.corebyte.mob.kiipa.event;

import android.content.Context;

import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;

import java.util.List;

public class PickCreditCustomerEvent {

    CustomerCrudOperation mCrudOperation;
    Context mContext;

    public PickCreditCustomerEvent(Context context) {
        mCrudOperation = new CustomerCrudOperation(context);
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public interface OnClickCreditor {
        void onClick(Customer customer);
    }
}
