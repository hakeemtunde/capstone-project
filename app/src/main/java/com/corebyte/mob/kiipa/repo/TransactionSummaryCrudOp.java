package com.corebyte.mob.kiipa.repo;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.corebyte.mob.kiipa.dao.AppDatabase;
import com.corebyte.mob.kiipa.dao.BaseDao;
import com.corebyte.mob.kiipa.dao.TransactionSummaryDao;
import com.corebyte.mob.kiipa.event.CrudDao;
import com.corebyte.mob.kiipa.model.TransactionSummary;
import com.corebyte.mob.kiipa.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TransactionSummaryCrudOp implements CrudDao<TransactionSummary> {

    private CrudAsyncTask<TransactionSummary> mCrudAsyn;

    public TransactionSummaryCrudOp(Context context) {
        mCrudAsyn = new CrudAsyncTask<>(context, this);
    }

    @Override
    public BaseDao getCrudDao(AppDatabase database) {
        return database.transactionSummaryDao();
    }

    @Override
    public CrudAsyncTask getAsync() {
        return mCrudAsyn;
    }

    @Override
    public long create(TransactionSummary model) {
        DateUtil.initCreateDate(model);
        return mCrudAsyn.create(model);

    }

    @Override
    public long[] create(TransactionSummary... models) {
        return new long[0];
    }

    @Override
    public void update(TransactionSummary model) {
        DateUtil.modifyUpdateDate(model);
        mCrudAsyn.update(model);
    }

    @Override
    public void update(TransactionSummary... models) {

    }

    @Override
    public void delete(TransactionSummary model) {
        mCrudAsyn.delete(model);
    }

    @Override
    public TransactionSummary getById(long id) {
        return mCrudAsyn.getById(id);
    }

    @Override
    public List<TransactionSummary> getAllRecord() {
        return mCrudAsyn.getAllRecord();
    }

    public int getTransactionCount(Date date) {

        int txcounts = 0;

        if (date == null) {
            date = new Date();
        }

        AsyncTask asyncTask = new AsyncTask<Date, Void, Integer>(){

            @Override
            protected Integer doInBackground(Date... dates) {

                int transactions = ((TransactionSummaryDao)mCrudAsyn.getDao())
                        .countTransactionByDate(dates[0]);
                return transactions;
            }
        }.execute(date);

        try {
            txcounts = (int) asyncTask.get();
        }catch (InterruptedException ie  ) {
            ie.printStackTrace();
        }catch (ExecutionException ee){
            ee.printStackTrace();
        }


        return txcounts;
    }

    public List<TransactionSummary> getTransactionsByDate(Date date) {
        List<TransactionSummary> transactionSummaries = new ArrayList<>();

        if (date == null) {
            date = new Date();
        }

        AsyncTask asyncTask = new AsyncTask<Date, Void, List<TransactionSummary>>(){

            @Override
            protected List<TransactionSummary> doInBackground(Date... dates) {

                List<TransactionSummary> transactions = ((TransactionSummaryDao)mCrudAsyn.getDao())
                        .findByDate(dates[0]);
                return transactions;
            }
        }.execute(date);

        try {
            transactionSummaries = (List<TransactionSummary>) asyncTask.get();
        }catch (InterruptedException ie  ) {
            ie.printStackTrace();
        }catch (ExecutionException ee){
            ee.printStackTrace();
        }

        return transactionSummaries;
    }
}
