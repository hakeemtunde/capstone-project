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


    public CustomerEventHandler(Context context) {
        mCrudOperation = new CustomerCrudOperation(context);
    }

    public void setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
    }

    public void setAdapter(AdapterAction aAction) {
        mAdapterAction = aAction;
    }

    @Override
    public void create(String... params) {
        Customer customer = new Customer(params[0], params[1]);
        mCrudOperation.create(customer);
        mAdapterAction.refreshAdapter();

    }

    @Override
    public void update(Customer model) {
        mCrudOperation.update(model);
        mAdapterAction.refreshAdapter();
    }

    @Override
    public void delete(Customer model) {
        mCrudOperation.delete(model);
        mAdapterAction.refreshAdapter();
    }

    @Override
    public List<Customer> fetchAll() {
        return mCrudOperation.getAll();
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

    @Override
    public boolean isValid(String... params) {
        return true;
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

        mAdapterAction.appendModel(customer);
        mAdapterAction.refreshAdapter();

        return customer;
    }


}
