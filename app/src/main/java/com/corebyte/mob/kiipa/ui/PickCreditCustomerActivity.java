package com.corebyte.mob.kiipa.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.PickCreditCustomerRecyclerAdapter;
import com.corebyte.mob.kiipa.event.PickCreditCustomerEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickCreditCustomerActivity extends AppCompatActivity {

    @BindView(R.id.appToolbar)
    Toolbar toolbar;

    @BindView(R.id.pick_credit_customer_rv)
    RecyclerView mCreditCustomerRv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_credit_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        PickCreditCustomerEvent creditCustomerEvent = new PickCreditCustomerEvent(getApplicationContext());
        PickCreditCustomerRecyclerAdapter adapter = new PickCreditCustomerRecyclerAdapter(creditCustomerEvent);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCreditCustomerRv.setLayoutManager(layoutManager);
        mCreditCustomerRv.setHasFixedSize(true);
        mCreditCustomerRv.setAdapter(adapter);

    }
}
