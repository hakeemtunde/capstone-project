package com.corebyte.mob.kiipa.event;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;
import com.corebyte.mob.kiipa.ui.CustomerDialogActivity;

import java.util.List;

public class CustomerEventHandler implements EventHandler<Customer> {

    public static final String CUSTOMER_DLG_TAG = "CUSTOMER_DEFAULT_DLG";

    CustomerCrudOperation mCrudOperation;
    FragmentManager mFragmentManager;
    AdapterAction mAdapterAction;
    private Context mContext;


    public CustomerEventHandler(Context context) {
        mCrudOperation = new CustomerCrudOperation(context);
        mContext = context;
    }

    public void setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
    }



    @Override
    public void create(String... params) {
        Customer customer = new Customer(params[0], params[1]);
        mCrudOperation.create(customer);
    }

    @Override
    public void update(Customer model) {
        mCrudOperation.update(model);
    }

    @Override
    public void delete(Customer model) {
        mCrudOperation.delete(model);
    }


    @Override
    public void onEditButtonClicked(Customer customer) {

        if (mFragmentManager == null) throw new IllegalArgumentException();

        CustomerDialogActivity dialogActivity = new CustomerDialogActivity();
        dialogActivity.setEventHandler(this);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CustomerDialogActivity.CUSTOMER_KEY, customer);
        dialogActivity.setArguments(bundle);
        dialogActivity.show(mFragmentManager, CUSTOMER_DLG_TAG);

    }


    public void createOrUpdate(Customer customer, String... params) {
        if (customer == null) {
            create(params);
        } else {
            customer.setParameters(params);
            update(customer);
        }
    }

    public Customer createAndRefreshAdapter(String... params) {
        Customer customer = new Customer(params[0], params[1]);
        long id = mCrudOperation.create(customer);
        customer.id = id;
        return customer;
    }

    @Override
    public Context getContext() { return mContext; }

}
