package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "creditors_payment_log",
        foreignKeys = {
        @ForeignKey(entity = Customer.class,
                parentColumns = "id", childColumns = "customerId",
                onDelete = CASCADE)}, indices = @Index("customerId"))
public class CreditorsPaymentLog extends BaseModel {
    private long customerId;
    private double amountPaid;
    private double balanceAfterPayment;
    private String remark;
    private String paymentType;


    public CreditorsPaymentLog(long customerId, double amountPaid, String remark) {
        this.customerId = customerId;
        this.amountPaid = amountPaid;
        this.remark = remark;
        initDates();
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CreditorsPaymentLog{" +
                "customerId=" + customerId +
                ", amountPaid=" + amountPaid +
                ", balanceAfterPayment=" + balanceAfterPayment +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public double getBalanceAfterPayment() {
        return balanceAfterPayment;
    }

    public void setBalanceAfterPayment(double balanceAfterPayment) {
        this.balanceAfterPayment = balanceAfterPayment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
