package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;

@Entity(tableName = "transaction_summary")
public class TransactionSummary extends BaseModel {

    double total;
    int salesOrder;

    public TransactionSummary(double ttl, int sorder) {
        total = ttl;
        salesOrder = sorder;
    }

    public double getTotal() {
        return total;
    }

    public int getSalesOrder() {
        return salesOrder;
    }
}
