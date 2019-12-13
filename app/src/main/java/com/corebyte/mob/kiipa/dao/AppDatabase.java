package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.corebyte.mob.kiipa.model.Category;
import com.corebyte.mob.kiipa.model.CreditorsPaymentLog;
import com.corebyte.mob.kiipa.model.CreditorsTransaction;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.model.Measurement;
import com.corebyte.mob.kiipa.model.Stock;
import com.corebyte.mob.kiipa.model.TransactionBreakdown;
import com.corebyte.mob.kiipa.model.TransactionSummary;
import com.corebyte.mob.kiipa.util.DateConverter;

@Database(entities =
        {Category.class, Stock.class, Measurement.class,
                TransactionSummary.class,
                TransactionBreakdown.class,
                Customer.class,
                CreditorsTransaction.class,
                CreditorsPaymentLog.class
        },

        version = 1,
        exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "kiipa-db-dev2.1";

    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (new Object()) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return sInstance;
    }

    public abstract CategoryDao categoryDao();

    public abstract StockDao stockDao();

    public abstract MeasurementDao measurementDao();

    public abstract TransactionSummaryDao transactionSummaryDao();

    public abstract TransactionBreakdownDao transactionBreakdownDao();

    public abstract CustomerDao customerDao();

    public abstract CreditorsTransactionDao creditorsTransactionDao();

    public abstract CreditorsPaymentLogDao creditorsPaymentLogDao();
}
