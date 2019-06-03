package com.corebyte.mob.kiipa.ui;

import android.content.Intent;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.corebyte.mob.kiipa.Cart;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.StockRecyclerAdapter;
import com.corebyte.mob.kiipa.event.StockEvent;
import com.corebyte.mob.kiipa.model.CartStock;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.repo.StockCrudOperation;

import java.util.ArrayList;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StockActivity extends AppCompatActivity implements StockEvent {

    private static final String TAG = StockActivity.class.getSimpleName();

    private static final int REQUEST_CODE = 100;
    public static final int REQUEST_CODE_ADD_STOCK_ITEM = 1001;

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    @BindView(R.id.rc_stock)
    public RecyclerView recyclerView;

    @BindView(R.id.fab)
    public FloatingActionButton floatingActionButton;

    StockCrudOperation stockCrudOperation;

    Cart mCart;
    Stock mStockSelected;

    private StockRecyclerAdapter mStockAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mCart = new Cart();

        stockCrudOperation = new StockCrudOperation(getApplicationContext());
        mStockAdapter = new StockRecyclerAdapter(stockCrudOperation.getAll(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mStockAdapter);

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
            startActivityForResult(intent, REQUEST_CODE_ADD_STOCK_ITEM);
            return true;
        }

        if(item.getItemId() == R.id.menu_stock_cart) {
            lunchCartCheckoutActivity();
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

        mStockSelected = stock;

        AddToCartDialogActivity dialogActivity = new AddToCartDialogActivity();
        dialogActivity.setOnStockEventHandler(this);
        Bundle bundle = new Bundle();
        bundle.putParcelable(StockItemActivity.STOCKITEM, stock);
        dialogActivity.setArguments(bundle);
        dialogActivity.show(getSupportFragmentManager(), "ADD_TO_CART");
    }

    @Override
    public void onAddToCart(Measurement measurement, int qty) {
        if (measurement == null || qty > measurement.getAvailableQty() ) {
            Toast.makeText(getApplicationContext(), "" +
                    "Stock cannot be added. select measurement and the quantity shouldn't be more than available quantity",
                    Toast.LENGTH_LONG).show();
            return;
        }

        mCart.add(mStockSelected, measurement, qty);
        Toast.makeText(getApplicationContext(), "Add to Cart!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            Toast.makeText(getApplicationContext(), "Item(s) checkout successfully.",
                    Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_ADD_STOCK_ITEM && resultCode == RESULT_OK) {
            //Refresh adapter
            mStockAdapter.setDataAndFresh(stockCrudOperation.getAll());
        }



    }

    @OnClick(R.id.fab)
    public void onClickFloatingActionButton(View view) {
        lunchCartCheckoutActivity();
    }

    private void lunchCartCheckoutActivity() {
        Intent intent = new Intent(this, CheckoutActivity.class);
        intent.putExtra(CheckoutActivity.CART_STOCK_TAG, mCart.getCartSummary());
        startActivityForResult(intent, REQUEST_CODE);
    }
}
