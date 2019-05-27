package com.corebyte.mob.kiipa.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;

import java.util.Map;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.settings_it) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
