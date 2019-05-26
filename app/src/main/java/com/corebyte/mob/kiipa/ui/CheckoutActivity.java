package com.corebyte.mob.kiipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.corebyte.mob.kiipa.CartSummary;
import com.corebyte.mob.kiipa.SalesCheckout;
import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.CartStock;
import com.corebyte.mob.kiipa.model.Customer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.corebyte.mob.kiipa.ui.CustomerDialogActivity.CUSTOMER_KEY;

public class CheckoutActivity extends AppCompatActivity {

    public static final String CART_STOCK_TAG = "CART_STOCKS";
    public static final String CART_TOTAL_KEY = "CART_TOTAL_AMOUNT";
    private static final String TAG = CheckoutActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 101;

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
    SalesCheckout mSalesCheckout;
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

            mSalesCheckout = new SalesCheckout(getApplicationContext(), mCartSummary);
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
        mSalesCheckout.checkoutCart();
        setResult(RESULT_OK);
        finish();
    }

    @OnClick(R.id.creditors_ib)
    public void onCreditorBtnClick() {
        Intent intent = new Intent(this, PickCreditCustomerActivity.class);
        intent.putExtra(CART_TOTAL_KEY, mCartSummary.getmTotalAmount());
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data.hasExtra(CUSTOMER_KEY)) {
            Customer customer = data.getParcelableExtra(CUSTOMER_KEY);
            mSalesCheckout.saveAsCreditSales(customer);
            setResult(RESULT_OK);
            finish();
        }

    }
}
