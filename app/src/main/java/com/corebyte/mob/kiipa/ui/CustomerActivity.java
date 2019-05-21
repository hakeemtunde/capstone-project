package com.corebyte.mob.kiipa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.CustomerRecyclerViewAdapter;
import com.corebyte.mob.kiipa.event.CustomerEventHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerActivity extends AppCompatActivity {

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;
    @BindView(R.id.customer_rv)
    public RecyclerView mCustomerRv;

    CustomerEventHandler mEventHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mEventHandler = new CustomerEventHandler(getApplicationContext());
        CustomerRecyclerViewAdapter adapter = new CustomerRecyclerViewAdapter(mEventHandler);

        mEventHandler.setAdapter(adapter);
        mEventHandler.setFragmentManager(getSupportFragmentManager());

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

            CustomerDialogActivity dialogActivity = new CustomerDialogActivity();
            dialogActivity.setEventHandler(mEventHandler);
            dialogActivity.show(getSupportFragmentManager(), "ADD_CUSTOMER");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


