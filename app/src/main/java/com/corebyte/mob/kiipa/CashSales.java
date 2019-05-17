package com.corebyte.mob.kiipa;

import android.content.Context;

import com.corebyte.mob.kiipa.model.TransactionSummary;
import com.corebyte.mob.kiipa.repo.TransactionSummaryCrudOp;

import java.util.Date;

public class CashSales {

    private TransactionSummaryCrudOp mTransactionSummaryCrudOp;

    private CartSummary mCartSummary;

    public CashSales(Context context, CartSummary cartSummary) {
        this.mCartSummary = cartSummary;
        this.mTransactionSummaryCrudOp = new TransactionSummaryCrudOp(context);

    }

    public void checkoutCart() {
        int order = mTransactionSummaryCrudOp.getTransactionCount(null);

        TransactionSummary transactionSummary = new TransactionSummary(mCartSummary.getmTotalAmount(), order);
        long txid = mTransactionSummaryCrudOp.create(transactionSummary);


    }
}
