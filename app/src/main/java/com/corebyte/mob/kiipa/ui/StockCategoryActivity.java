package com.corebyte.mob.kiipa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.RecyclerAdapter;
import com.corebyte.mob.kiipa.event.ProgressBarEvent;
import com.corebyte.mob.kiipa.repo.CategoryCrudOperation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockCategoryActivity extends AppCompatActivity
        implements ProgressBarEvent {

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    @BindView(R.id.rc_category)
    public RecyclerView mCategoryRv;

    @BindView(R.id.pb_loader)
    public ProgressBar progressBar;

    private CategoryCrudOperation categoryCrudOperation;

    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_category);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mAdapter = new RecyclerAdapter(getApplicationContext(), this);
        mCategoryRv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCategoryRv.setLayoutManager(linearLayoutManager);
        mCategoryRv.setAdapter(mAdapter);

        categoryCrudOperation = new CategoryCrudOperation(getApplicationContext());
        categoryCrudOperation.loadDataToAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_category) {
            CategoryDialog categoryDialog = new CategoryDialog();
            categoryDialog.setAdapter(mAdapter);
            categoryDialog.show(getSupportFragmentManager(), getString(R.string.categoryDlgTag));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
