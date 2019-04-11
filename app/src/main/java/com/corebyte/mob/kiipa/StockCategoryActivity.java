package com.corebyte.mob.kiipa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.corebyte.mob.kiipa.adapter.RecyclerAdapter;
import com.corebyte.mob.kiipa.event.StockDialogListener;
import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.repo.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StockCategoryActivity extends AppCompatActivity {

    @BindView(R.id.appToolbar)
    public Toolbar toolbar;

    @BindView(R.id.rc_category)
    public RecyclerView mCategoryRv;

    private Repository mRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_category);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext());
        mCategoryRv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mCategoryRv.setLayoutManager(linearLayoutManager);
        mCategoryRv.setAdapter(adapter);

        mRepository = new Repository(getApplicationContext(), adapter);
        mRepository.getAll();

        //addFetchCat();

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
            addCategoryDialog.setRepository(mRepository);
            addCategoryDialog.show(getSupportFragmentManager(), getString(R.string.categoryDlgTag));
        }

        return super.onOptionsItemSelected(item);
    }
}
