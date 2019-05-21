package com.corebyte.mob.kiipa.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

@Entity(tableName = "customers")
public class Customer extends BaseModel implements Parcelable {

    static Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>(){

        @Override
        public Customer createFromParcel(Parcel parcel) {
            return new Customer(parcel);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    private String mName;
    private String mPhone;

    public Customer(String mName, String mPhone) {
        this.mName = mName;
        this.mPhone = mPhone;
    }

    @Ignore
    public Customer(Parcel parcel) {
        id = parcel.readLong();
        createdAt = new Date(parcel.readLong());
        updatedAt = new Date(parcel.readLong());
        mName = parcel.readString();
        mPhone = parcel.readString();
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String[] getParameters() {
        return new String[]{mName, mPhone};
    }

    public void setParameters(String... parameters) {
        mName = parameters[0];
        mPhone = parameters[1];
    }

    @Override
    public String toString() {
        return "Customer{" +
                "mName='" + mName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(createdAt.getTime());
        parcel.writeLong(updatedAt.getTime());
        parcel.writeString(mName);
        parcel.writeString(mPhone);

    }
}
