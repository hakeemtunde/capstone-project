package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

@Entity(tableName = "stocks")
public class Stock extends BaseModel implements Parcelable {


    public static Parcelable.Creator CREATOR = new Parcelable.Creator<Stock>(){

        @Override
        public Stock createFromParcel(Parcel parcel) {
            return new Stock(parcel);
        }

        @Override
        public Stock[] newArray(int size) {
            return new Stock[size];
        }
    };

    private String name;
    private Date expireDate;
    private long categoryId;

    public Stock(String name, Date expireDate, long categoryId) {
        this.name = name;
        this.expireDate = expireDate;
        this.categoryId = categoryId;
    }

    @Ignore
    public Stock(Parcel parcel) {
        this.id = parcel.readLong();
        this.createdAt = new Date(parcel.readLong());
        this.updatedAt = new Date(parcel.readLong());
        this.name = parcel.readString();
        this.expireDate = new Date(parcel.readLong());
        this.categoryId = parcel.readLong();
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


    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.createdAt.getTime());
        parcel.writeLong(this.updatedAt.getTime());
        parcel.writeString(this.name);
        parcel.writeLong(this.expireDate.getTime());
        parcel.writeLong(this.categoryId);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        Stock stock = (Stock) o;

        return id == stock.id && categoryId == stock.getCategoryId();

//        return categoryId == stock.categoryId &&
//                Objects.equals(name, stock.name) &&
//                Objects.equals(expireDate, stock.expireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId);
    }
}
