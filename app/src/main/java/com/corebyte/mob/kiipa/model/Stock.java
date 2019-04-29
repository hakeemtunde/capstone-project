package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;

import java.util.Date;

@Entity(tableName = "stocks")
public class Stock extends BaseModel {

    private String name;

    private Date expireDate;

    private long categoryId;

    public Stock(String name, Date expireDate, long categoryId) {
        this.name = name;
        this.expireDate = expireDate;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
