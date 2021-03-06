package com.corebyte.mob.kiipa.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.corebyte.mob.kiipa.model.CreditorsTransaction;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CreditorsTransactionCrudOp;
import com.corebyte.mob.kiipa.viewmodel.CustomerViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.corebyte.mob.kiipa.ui.CustomerDialogActivity.CUSTOMER_KEY;
import static com.corebyte.mob.kiipa.util.AppUtil.tintIcon;

public class PickCreditCustomerActivity extends AppCompatActivity
        implements PickCreditCustomerEvent.OnClickCreditor {

    public static final String CUSTOMER_DLG_TAG = "CUSTOMER_QUICK_DLG";
    Customer mSelectedCreditor;

    @BindView(R.id.appToolbar)
    Toolbar toolbar;

    @BindView(R.id.pick_credit_customer_rv)
    RecyclerView mCreditCustomerRv;

    MenuItem mMenuItem;
    private CustomerEventHandler mCustomerEventHandler;

    CreditorsTransactionCrudOp mCreditorsTransactionsCrudOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_credit_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        double cartTotalSum = 0;
        if (getIntent() !=null && getIntent().hasExtra(CheckoutActivity.CART_TOTAL_KEY)) {
            cartTotalSum = getIntent().getDoubleExtra((CheckoutActivity.CART_TOTAL_KEY), 0);
        }

        mCustomerEventHandler = new CustomerEventHandler(getApplicationContext());

        mCreditorsTransactionsCrudOp = new CreditorsTransactionCrudOp(getApplicationContext());
        PickCreditCustomerEvent creditCustomerEvent = new PickCreditCustomerEvent(getApplicationContext());

        final PickCreditCustomerRecyclerAdapter adapter = new PickCreditCustomerRecyclerAdapter(creditCustomerEvent,
                this);
        adapter.setCartTotalSum(cartTotalSum);
        //mCustomerEventHandler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCreditCustomerRv.setLayoutManager(layoutManager);
        mCreditCustomerRv.setHasFixedSize(true);
        mCreditCustomerRv.setAdapter(adapter);

        CustomerViewModel CustomerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        CustomerViewModel.getCustomersList().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable List<Customer> customerList) {
                adapter.setAdapterData(customerList);
            }
        });

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

            Intent intent = new Intent();
            intent.putExtra(CUSTOMER_KEY, mSelectedCreditor);
            setResult(RESULT_OK, intent);
            finish();

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
        mSelectedCreditor = customer;

    }


}
