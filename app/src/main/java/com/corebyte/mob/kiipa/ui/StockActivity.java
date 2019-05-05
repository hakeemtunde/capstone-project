package com.corebyte.mob.kiipa.ui;

import android.content.Intent;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.StockRecyclerAdapter;
import com.corebyte.mob.kiipa.event.StockEvent;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;

import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockActivity extends AppCompatActivity implements StockEvent {

    private static final String TAG = StockActivity.class.getSimpleName();

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    @BindView(R.id.rc_stock)
    public RecyclerView recyclerView;

    StockCrudOperation stockCrudOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        stockCrudOperation = new StockCrudOperation(getApplicationContext());
        StockRecyclerAdapter adapter = new StockRecyclerAdapter(stockCrudOperation.getAll(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_stock) {
            Intent intent = new Intent(this, StockItemActivity.class);
            startActivity(intent);
            return true;
        }

        return true;

    }


    @Override
    public void onClick(Stock stock) {
        Intent intent = new Intent(this, StockItemActivity.class);
        intent.setAction(StockItemActivity.ACTION_EDIT);
        intent.putExtra(StockItemActivity.STOCKITEM, stock);
        startActivity(intent);
    }

    @Override
    public void onCartClick(Stock stock) {
        //stockCrudOperation.delete(stock);
    }
}
