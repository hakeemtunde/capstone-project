package com.corebyte.mob.kiipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.corebyte.mob.kiipa.CartSummary;
import com.corebyte.mob.kiipa.CashSales;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.CartStock;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckoutActivity extends AppCompatActivity {

    public static final String CART_STOCK_TAG = "CART_STOCKS";
    private static final String TAG = CheckoutActivity.class.getSimpleName();
    @BindView(R.id.cart_item_tv)
    TextView mCartItemTv;
    @BindView(R.id.amount_tv)
    TextView mCartTotalTv;
    @BindView(R.id.cart_item_tl)
    TableLayout mCartItemTl;
    @BindView(R.id.cash_ib)
    ImageButton cashBtn;
    @BindView(R.id.creditors_ib)
    ImageButton creditorsBtn;

    private CartSummary mCartSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(CART_STOCK_TAG)) {
            mCartSummary = intent.getParcelableExtra(CART_STOCK_TAG);
            initUi();
        }

    }

    private void initUi() {

        mCartItemTv.setText(String.valueOf(mCartSummary.getmTotalItems()));
        mCartTotalTv.setText(String.valueOf(mCartSummary.getmTotalAmount()));

        for (CartStock cartStock : mCartSummary.getmCartStocks()) {

            TableRow tableRow = new TableRow(getApplicationContext());

            ArrayList<View> views = initTableRowViews(cartStock);
            tableRow.addView(views.get(0));
            tableRow.addView(views.get(1));
            tableRow.addView(views.get(2));
            tableRow.addView(views.get(3));
            tableRow.addView(views.get(4));

            mCartItemTl.addView(tableRow);
        }

    }

    private ArrayList<View> initTableRowViews(CartStock cartStock) {
        ArrayList<View> views = new ArrayList<>();
        TextView stockName = new TextView(getApplicationContext());
        TextView measureName = new TextView(getApplicationContext());
        TextView cost = new TextView(getApplicationContext());
        TextView price = new TextView(getApplicationContext());
        ImageView delete = new ImageView(getApplicationContext());
        delete.setImageResource(R.drawable.ic_delete_black_24dp);

        stockName.setText(cartStock.getmStockName());
        measureName.setText(cartStock.getmMeasureName());

        String quantityxcost = String.valueOf(cartStock.getmQuantity())
                + " x " + String.valueOf(cartStock.getmCostPerStock());
        cost.setText(quantityxcost);

        price.setText(String.valueOf(cartStock.getmTotalCost()));

        views.add(stockName);
        views.add(measureName);
        views.add(cost);
        views.add(price);
        views.add(delete);
        return views;
    }

    @OnClick(R.id.cash_ib)
    public void onCashBtnClick() {
        CashSales cashSales = new CashSales(getApplicationContext(), mCartSummary);
        cashSales.checkoutCart();
        Toast.makeText(getApplicationContext(), "Item(s) checkout successfully.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
        
    }
}
