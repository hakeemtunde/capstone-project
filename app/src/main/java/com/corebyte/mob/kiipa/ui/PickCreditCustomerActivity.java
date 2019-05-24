package com.corebyte.mob.kiipa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.PickCreditCustomerRecyclerAdapter;
import com.corebyte.mob.kiipa.event.CustomerEventHandler;
import com.corebyte.mob.kiipa.event.PickCreditCustomerEvent;
import com.corebyte.mob.kiipa.model.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.corebyte.mob.kiipa.util.AppUtil.tintIcon;

public class PickCreditCustomerActivity extends AppCompatActivity
        implements PickCreditCustomerEvent.OnClickCreditor {

    public static final String CUSTOMER_DLG_TAG = "CUSTOMER_QUICK_DLG";

    @BindView(R.id.appToolbar)
    Toolbar toolbar;

    @BindView(R.id.pick_credit_customer_rv)
    RecyclerView mCreditCustomerRv;

    MenuItem mMenuItem;
    private CustomerEventHandler mCustomerEventHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_credit_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mCustomerEventHandler = new CustomerEventHandler(getApplicationContext());
        PickCreditCustomerEvent creditCustomerEvent = new PickCreditCustomerEvent(getApplicationContext());
        PickCreditCustomerRecyclerAdapter adapter = new PickCreditCustomerRecyclerAdapter(creditCustomerEvent,
                this);
        mCustomerEventHandler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCreditCustomerRv.setLayoutManager(layoutManager);
        mCreditCustomerRv.setHasFixedSize(true);
        mCreditCustomerRv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        mMenuItem = menu.findItem(R.id.done_it);
        mMenuItem.setIcon(tintIcon(getApplicationContext(), mMenuItem,
                R.color.colorLightPrimary));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.done_it) {

            return true;
        }

        if (item.getItemId() == R.id.add_creditor) {
            CustomerDialogActivity dialogActivity = new CustomerDialogActivity();
            dialogActivity.setEventHandler(mCustomerEventHandler);
            dialogActivity.show(getSupportFragmentManager(), CUSTOMER_DLG_TAG);
        }

        return true;
    }

    @Override
    public void onClick(Customer customer) {
        mMenuItem.setEnabled(true);
        mMenuItem.setIcon(tintIcon(getApplicationContext(), mMenuItem, R.color.colorText));

    }


}
