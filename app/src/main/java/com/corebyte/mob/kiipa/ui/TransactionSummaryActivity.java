package com.corebyte.mob.kiipa.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.adapter.TransactionSummaryRecyclerAdapter;
import com.corebyte.mob.kiipa.repo.TransactionSummaryCrudOp;

public class TransactionSummaryActivity extends AppCompatActivity {

    private TransactionSummaryCrudOp mTransactionSummaryCrudOp;
    private RecyclerView mTransactionSummaryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_summary);

        mTransactionSummaryCrudOp = new TransactionSummaryCrudOp(getApplicationContext());
        TransactionSummaryRecyclerAdapter adapter = new TransactionSummaryRecyclerAdapter(
                mTransactionSummaryCrudOp.getAll());

        mTransactionSummaryRecyclerView = findViewById(R.id.transaction_summary_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mTransactionSummaryRecyclerView.setLayoutManager(layoutManager);
        mTransactionSummaryRecyclerView.addItemDecoration(
                new DividerItemDecoration(mTransactionSummaryRecyclerView.getContext(),
                        layoutManager.getOrientation()));
        mTransactionSummaryRecyclerView.setAdapter(adapter);
    }
}
