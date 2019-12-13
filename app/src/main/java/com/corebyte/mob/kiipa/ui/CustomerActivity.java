package com.corebyte.mob.kiipa.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.CustomerRecyclerViewAdapter;
import com.corebyte.mob.kiipa.event.CustomerEventHandler;
import com.corebyte.mob.kiipa.model.BaseModel;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.ui.dlg.CreditorsPaymentDlg;
import com.corebyte.mob.kiipa.ui.dlg.DialogPresenter;
import com.corebyte.mob.kiipa.viewmodel.CustomerViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomerActivity extends AppCompatActivity {

    public static final String CUSTOMER_KEY = "customer_model";
    @BindView(R.id.appToolbar)
    public Toolbar toolbar;
    @BindView(R.id.customer_rv)
    public RecyclerView mCustomerRv;

    CustomerEventHandler mEventHandler;

    CustomerViewModel mCustomerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mEventHandler = new CustomerEventHandler(getApplicationContext());
        final CustomerRecyclerViewAdapter adapter = new CustomerRecyclerViewAdapter(mEventHandler);

        //mEventHandler.setAdapter(adapter);
        mEventHandler.setFragmentManager(getSupportFragmentManager());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCustomerRv.addItemDecoration(
                new DividerItemDecoration(mCustomerRv.getContext(), layoutManager.getOrientation()));
        mCustomerRv.setLayoutManager(layoutManager);
        mCustomerRv.setHasFixedSize(true);
        mCustomerRv.setAdapter(adapter);

        mCustomerViewModel = ViewModelProviders.of(this).get(CustomerViewModel.class);
        mCustomerViewModel.getCustomersList().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(@Nullable List<Customer> customerList) {
                adapter.setAdapterData(customerList);
            }
        });


        //setup payment dialog button click
        DialogPresenter.ModelDialogCallback callback = new DialogPresenter.ModelDialogCallback<Customer>() {
            @Override
            public void click(Customer model) {
                CreditorsPaymentDialogR dialogR = new CreditorsPaymentDialogR();
                Bundle bundle = new Bundle();
                bundle.putParcelable(CUSTOMER_KEY, model);
                dialogR.setArguments(bundle);
                dialogR.show(getSupportFragmentManager(), "Payment_DlG");
            }
        };

        adapter.setDialogCallback(callback);

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
            dialogActivity.show(getSupportFragmentManager(), CustomerEventHandler.CUSTOMER_DLG_TAG);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


