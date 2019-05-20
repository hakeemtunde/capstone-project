package com.corebyte.mob.kiipa.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.CustomerRecyclerViewAdapter;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerActivity extends AppCompatActivity {

    @BindView(R.id.appToolbar)
    Toolbar toolbar;
    @BindView(R.id.customer_rv)
    RecyclerView mCustomerRv;

    CustomerCrudOperation mCustomerCrudOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mCustomerCrudOperation = new CustomerCrudOperation(getApplicationContext());
        List<Customer> customers = mCustomerCrudOperation.getAll();
        CustomerRecyclerViewAdapter adapter = new CustomerRecyclerViewAdapter(customers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCustomerRv.addItemDecoration(
                new DividerItemDecoration(mCustomerRv.getContext(), layoutManager.getOrientation()));
        mCustomerRv.setLayoutManager(layoutManager);
        mCustomerRv.setHasFixedSize(true);
        mCustomerRv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.customer_add) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
