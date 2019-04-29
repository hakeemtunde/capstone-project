package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "categories")
public class Category extends BaseModel implements Parcelable {


    public static Parcelable.Creator CREATOR = new Parcelable.Creator<Category>() {

        @Override
        public Category createFromParcel(Parcel parcel) {
            return new Category(parcel);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    private String name;
    private int status;

    public Category(String name, int status) {
        this.name = name;
        this.status = status;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Ignore
    public Category(Parcel parcel) {
        this.id = parcel.readInt();
        this.createdAt = new Date(parcel.readLong());
        this.updatedAt = new Date(parcel.readLong());
        this.name = parcel.readString();
        this.status = parcel.readInt();
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
        parcel.writeInt(this.status);
    }
}
