package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "categories")

public class Category extends BaseModel{

    private String name;
    private int status;

    public Category(String name, int status) {
        this.name = name;
        this.status = status;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
