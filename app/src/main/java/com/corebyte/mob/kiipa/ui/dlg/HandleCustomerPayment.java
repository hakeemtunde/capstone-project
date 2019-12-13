package com.corebyte.mob.kiipa.ui.dlg;

import android.content.Context;
import android.util.Log;

import com.corebyte.mob.kiipa.model.CreditorsPaymentLog;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CreditorsPaymentLogCrudOperation;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;

public class HandleCustomerPayment {
    private static final String TAG = HandleCustomerPayment.class.getSimpleName();

    private Context mContext;
    private PaymentData paymentData;

    private HandleCustomerPayment() {}

    public HandleCustomerPayment(Context context, PaymentData paymentData) {
        mContext = context;
        this.paymentData = paymentData;
    }

    public void processPayment() {

        CreditorsPaymentLog log = new CreditorsPaymentLog(
                paymentData.customer.id,
                paymentData.amount,
                paymentData.remark);

        paymentData.customer.reduceCreditBy(paymentData.amount);
        log.setBalanceAfterPayment(paymentData.customer.getOwnCredit());
        log.setPaymentType(paymentData.paymentType);
        Log.i(TAG, "PaymentLog: "+ log.toString());
        Log.i(TAG, "Current Own: "+ paymentData.customer.getOwnCredit());

        //insert
        CreditorsPaymentLogCrudOperation crudOperation
                = new CreditorsPaymentLogCrudOperation(mContext);
        crudOperation.create(log);

        //update
        CustomerCrudOperation customerCrudOperation = new CustomerCrudOperation(mContext);
        customerCrudOperation.update(paymentData.customer);

    }

    public static void handlePayment(Context context, PaymentData paymentData) {
        HandleCustomerPayment payment = new HandleCustomerPayment(context, paymentData);
        payment.processPayment();
    }

    public static class PaymentData {
        public Customer customer;
        public double amount;
        public String remark;
        public String paymentType;



    }

}
