package com.corebyte.mob.kiipa.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.corebyte.mob.kiipa.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.category_btn)
    public void lunchStockCategoryActivity() {
        Intent intent = new Intent(this, StockCategoryActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.stock_btn)
    public void lunchStockActivity() {
        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.daily_transaction_btn)
    public void lunchDailyTransactionActivity() {
        Intent intent = new Intent(this, TransactionSummaryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.customer_btn)
    public void lunchCustomerActivity() {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivity(intent);
    }



}
