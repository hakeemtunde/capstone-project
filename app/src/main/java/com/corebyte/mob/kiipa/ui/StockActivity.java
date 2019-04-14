package com.corebyte.mob.kiipa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockActivity extends AppCompatActivity {

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        toolbar.setSubtitle("Stock");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_stock) {


            return true;
        }

        return true;

    }
}
