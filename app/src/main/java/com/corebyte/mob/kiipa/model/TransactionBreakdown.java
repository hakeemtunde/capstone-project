package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;

@Entity(tableName = "transaction_breakdowns")
public class TransactionBreakdown extends BaseModel {

    long stockId;
    long measurementId;
    long transactionSummaryId;
    int qty;
    double cost;
    double total;

    public TransactionBreakdown(long stockId, long measurementId, long transactionSummaryId,
                                int qty, double cost, double total) {
        this.stockId = stockId;
        this.measurementId = measurementId;
        this.transactionSummaryId = transactionSummaryId;
        this.qty = qty;
        this.cost = cost;
        this.total = total;
    }

    public long getStockId() {
        return stockId;
    }

    public long getMeasurementId() {
        return measurementId;
    }

    public long getTransactionSummaryId() {
        return transactionSummaryId;
    }

    public int getQty() {
        return qty;
    }

    public double getCost() {
        return cost;
    }

    public double getTotal() {
        return total;
    }
}
