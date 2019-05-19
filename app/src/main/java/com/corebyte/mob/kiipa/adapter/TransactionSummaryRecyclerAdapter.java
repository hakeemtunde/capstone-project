package com.corebyte.mob.kiipa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corebyte.mob.kiipa.R;
import com.corebyte.mob.kiipa.model.TransactionSummary;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.List;

public class TransactionSummaryRecyclerAdapter extends RecyclerView.Adapter<TransactionSummaryRecyclerAdapter.ViewHolder> {

    private List<TransactionSummary> mTransactionSummaries;

    public TransactionSummaryRecyclerAdapter(List<TransactionSummary> transactionSummaries) {
        mTransactionSummaries = transactionSummaries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_transaction_summary_list, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        TransactionSummary transactionSummary = mTransactionSummaries.get(i);
        viewHolder.bind(transactionSummary);

    }

    @Override
    public int getItemCount() {
        return mTransactionSummaries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView orderTv;
        TextView dateTv;
        TextView amountTv;
//        ImageView transactionBreakdownIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderTv = itemView.findViewById(R.id.order_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
            amountTv = itemView.findViewById(R.id.amount_tv);
//            transactionBreakdownIv = itemView.findViewById(R.id.transaction_breakdown_iv);
        }

        public void bind(TransactionSummary transactionSummary) {
            orderTv.setText(String.valueOf(transactionSummary.getSalesOrder()));
            dateTv.setText(DateUtil.getDateFormat(transactionSummary.createdAt));
            amountTv.setText(String.valueOf(transactionSummary.getTotal()));
        }
    }
}
