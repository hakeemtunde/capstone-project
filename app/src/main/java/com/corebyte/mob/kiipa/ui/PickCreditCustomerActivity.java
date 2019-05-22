package com.corebyte.mob.kiipa.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.PickCreditCustomerRecyclerAdapter;
import com.corebyte.mob.kiipa.event.PickCreditCustomerEvent;
import com.corebyte.mob.kiipa.model.Customer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickCreditCustomerActivity extends AppCompatActivity
        implements PickCreditCustomerEvent.OnClickCreditor {

    @BindView(R.id.appToolbar)
    Toolbar toolbar;

    @BindView(R.id.pick_credit_customer_rv)
    RecyclerView mCreditCustomerRv;

    MenuItem mMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_credit_customer);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        PickCreditCustomerEvent creditCustomerEvent = new PickCreditCustomerEvent(getApplicationContext());
        PickCreditCustomerRecyclerAdapter adapter = new PickCreditCustomerRecyclerAdapter(creditCustomerEvent,
                this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mCreditCustomerRv.setLayoutManager(layoutManager);
        mCreditCustomerRv.setHasFixedSize(true);
        mCreditCustomerRv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        mMenuItem = menu.findItem(R.id.done_it);
        mMenuItem.setIcon(tintIcon(getApplicationContext(), mMenuItem,
                R.color.colorLightPrimary));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.done_it) {

            return true;
        }
        return true;
    }

    @Override
    public void onClick(Customer customer) {
        mMenuItem.setEnabled(true);
        mMenuItem.setIcon(tintIcon(getApplicationContext(), mMenuItem, R.color.colorText));

    }

    private Drawable tintIcon(Context context, MenuItem menuItem, int resource_id) {

        Drawable normalDraw = menuItem.getIcon();
        Drawable wrap = DrawableCompat.wrap(normalDraw);
        DrawableCompat.setTint(wrap, ContextCompat.getColor(context, resource_id));
        return wrap;
    }
}
