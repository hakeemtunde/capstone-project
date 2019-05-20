package com.corebyte.mob.kiipa.dao;

import android.arch.persistence.room.Query;

import com.corebyte.mob.kiipa.model.Customer;

import java.util.List;

public interface CustomerDao extends BaseDao<Customer> {

    @Override
    @Query("SELECT * FROM customers")
    List<Customer> getAll();

    @Override
    @Query("SELECT * FROM customers WHERE id = :id")
    Customer findById(long id);
}
