package com.corebyte.mob.kiipa.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.services.StockExpirationScheduler;
import com.corebyte.mob.kiipa.services.TrackStock;
import com.corebyte.mob.kiipa.util.AppUtil;
import com.corebyte.mob.kiipa.util.DateUtil;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.util.DataUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar_date)
    TextView largeDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        largeDateTv.setText(DateUtil.getDashboardDateFormat(new Date()));

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        createNotificationChannel();

        boolean notificaionstatus = AppUtil.getPreferenceSettings(getApplicationContext(),
                getString(R.string.key_alert_on_low_stock), false);
        StockExpirationScheduler.setUpAlarmServiceOnStockLow(getApplicationContext(), notificaionstatus);

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "testchannel";
            String description = "channed desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Channel001", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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
