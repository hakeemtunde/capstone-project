package com.corebyte.mob.kiipa.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.corebyte.mob.kiipa.dao.CustomerDao;
import com.corebyte.mob.kiipa.model.Customer;
import com.corebyte.mob.kiipa.repo.CustomerCrudOperation;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {

    private LiveData<List<Customer>> customersList;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        CustomerCrudOperation operation = new CustomerCrudOperation(application);
        customersList = ((CustomerDao)operation.getAsync().getDao()).getAll();
    }

    public LiveData<List<Customer>> getCustomersList() { return customersList; }
}
