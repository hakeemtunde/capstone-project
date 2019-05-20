package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;

@Entity(tableName = "customers")
public class Customer extends BaseModel {

    private String mName;
    private String mPhone;

    public Customer(String mName, String mPhone) {
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mName='" + mName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", id=" + id +
                '}';
    }
}
