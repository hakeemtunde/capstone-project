package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "creditors_transactions",
 foreignKeys ={
        @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "customerId", onDelete = CASCADE),
         @ForeignKey(entity = TransactionSummary.class, parentColumns = "id", childColumns = "transactionSummaryId",
                 onDelete = CASCADE)
 })
public class CreditorsTransaction extends BaseModel{

    private long customerId;
    private long transactionSummaryId;
    private int status;

    public CreditorsTransaction(long customerId, long transactionSummaryId, int status) {
        this.customerId = customerId;
        this.transactionSummaryId = transactionSummaryId;
        this.status = status;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getTransactionSummaryId() {
        return transactionSummaryId;
    }

    public int getStatus() {
        return status;
    }
}
