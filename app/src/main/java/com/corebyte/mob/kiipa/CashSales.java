package com.corebyte.mob.kiipa;

import android.content.Context;

import com.corebyte.mob.kiipa.model.CartStock;
import com.corebyte.mob.kiipa.model.CreditorsTransaction;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.TransactionBreakdown;
import com.corebyte.mob.kiipa.model.TransactionSummary;
import com.corebyte.mob.kiipa.repo.CreditorsTransactionCrudOp;
import com.corebyte.mob.kiipa.repo.MeasurementCrudOperation;
import com.corebyte.mob.kiipa.repo.TransactionBreakdownCrudOp;
import com.corebyte.mob.kiipa.repo.TransactionSummaryCrudOp;

import java.util.List;

public class CashSales {

    private TransactionSummaryCrudOp mTransactionSummaryCrudOp;
    private TransactionBreakdownCrudOp mTransactionBreakdownCruOp;
    private MeasurementCrudOperation mMeasurementCrudOp;
    private CreditorsTransactionCrudOp mCreditorsTransactionCrudOp;

    private long transactionSummaryId;

    private CartSummary mCartSummary;

    public CashSales(Context context, CartSummary cartSummary) {
        this.mCartSummary = cartSummary;
        this.mTransactionSummaryCrudOp = new TransactionSummaryCrudOp(context);
        this.mTransactionBreakdownCruOp = new TransactionBreakdownCrudOp(context);
        this.mMeasurementCrudOp = new MeasurementCrudOperation(context);
        mCreditorsTransactionCrudOp = new CreditorsTransactionCrudOp(context);


    }

    public void checkoutCart() {
        int order = mTransactionSummaryCrudOp.getTransactionCount(null);

        TransactionSummary transactionSummary = new TransactionSummary(mCartSummary.getmTotalAmount(), order);
        transactionSummaryId = mTransactionSummaryCrudOp.create(transactionSummary);

        TransactionBreakdown[] transactionBreakdowns = new TransactionBreakdown[mCartSummary.getmCartStocks().size()];

        for (int i = 0; i < mCartSummary.getmCartStocks().size(); i++) {
            CartStock cartStock = mCartSummary.getmCartStocks().get(i);
            TransactionBreakdown txbrk = new TransactionBreakdown(
                    cartStock.getmStockId(), cartStock.getmId(), transactionSummaryId, cartStock.getmQuantity(),
                    cartStock.getmCostPerStock(), cartStock.getmTotalCost());

            transactionBreakdowns[i] = txbrk;
        }

        mTransactionBreakdownCruOp.create(transactionBreakdowns);

        //reduce products
        reduceProductQuantity();

    }

    private void reduceProductQuantity() {

        List<CartStock> cartStocks = mCartSummary.getmCartStocks();
        Measurement[] measurements = new Measurement[cartStocks.size()];

        for (int i = 0; i < cartStocks.size(); i++) {
            CartStock cartStock = cartStocks.get(i);
            Measurement measurement = mMeasurementCrudOp.getById(cartStock.getmId());
            measurement.reduceQuantity(cartStock.getmQuantity());
            measurements[i] = measurement;
        }

        mMeasurementCrudOp.update(measurements);

    }

    public void saveAsCreditSales(Customer customer) {
        checkoutCart();
        CreditorsTransaction creditorsTransaction = new CreditorsTransaction(customer.id, transactionSummaryId, 1);
        mCreditorsTransactionCrudOp.create(creditorsTransaction);
    }
}
