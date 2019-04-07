package com.corebyte.mob.kiipa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.event.StockDialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockCategoryActivity extends AppCompatActivity {

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_category);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_category) {
            AddCategoryDialog addCategoryDialog = new AddCategoryDialog();
            addCategoryDialog.setDialogListener(new StockDialogListener.StockDialogListenerEvent());
            addCategoryDialog.show(getSupportFragmentManager(), getString(R.string.categoryDlgTag));
        }

        return super.onOptionsItemSelected(item);
    }
}
