package com.corebyte.mob.kiipa.event;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.corebyte.mob.kiipa.adapter.CustomerRecyclerViewAdapter;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;
import com.corebyte.mob.kiipa.ui.CustomerDialogActivity;

import java.util.List;

public class CustomerEventHandler implements EventHandler<Customer> {

    CustomerCrudOperation mCrudOperation;
    FragmentManager mFragmentManager;
    CustomerRecyclerViewAdapter mAdapter;

    public CustomerEventHandler(Context context) {
        mCrudOperation = new CustomerCrudOperation(context);
    }

    public void setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
    }

    public void setAdapter(CustomerRecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public void create(String... params) {
        Customer customer = new Customer(params[0], params[1]);
        mCrudOperation.create(customer);
        mAdapter.refreshAdapter();

    }

    @Override
    public void update(Customer model) {
        mCrudOperation.update(model);
        mAdapter.refreshAdapter();
    }

    @Override
    public void delete(Customer model) {
        mCrudOperation.delete(model);
        mAdapter.refreshAdapter();
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
        dialogActivity.show(mFragmentManager, "EDIT_CUSTOMER");

    }

    @Override
    public boolean isValid(String... params) {
        return true;
    }


}
